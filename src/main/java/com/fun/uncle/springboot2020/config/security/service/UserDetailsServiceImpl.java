/*
 *  Copyright 2019-2020 Zheng Jie
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.fun.uncle.springboot2020.config.security.service;

import com.fun.uncle.springboot2020.config.security.service.dto.JwtUserDto;
import com.fun.uncle.springboot2020.domain.UserInfo;
import com.fun.uncle.springboot2020.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Zheng Jie
 * @date 2018-11-22
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 用户信息缓存
     *
     * @see {@link UserCacheClean}
     */

    final static Map<String, Future<JwtUserDto>> USER_DTO_CACHE = new ConcurrentHashMap<>();
    public static ExecutorService executor = newThreadPool();

    @Override
    public JwtUserDto loadUserByUsername(String username) {
        JwtUserDto jwtUserDto = null;
        Future<JwtUserDto> future = USER_DTO_CACHE.get(username);

        if (future==null) {
            Callable<JwtUserDto> call=()->getJwtBySearchDb(username);
            FutureTask<JwtUserDto> ft=new FutureTask<>(call);
            future=USER_DTO_CACHE.putIfAbsent(username,ft);
            if(future==null){
                future=ft;
                executor.submit(ft);
            }
            try{
                return future.get();
            }catch(CancellationException e){
                USER_DTO_CACHE.remove(username);
            }catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e.getMessage());
            }
        }else{
            try {
                jwtUserDto=future.get();
            }catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return jwtUserDto;

    }

    private JwtUserDto getJwtBySearchDb(String username) {
        UserInfo user = userInfoService.getByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("");
        } else {
            // 构建对应的权限
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("admin"));
            return new JwtUserDto(user,  authorities);
        }

    }

    public static ExecutorService newThreadPool() {
        ThreadFactory namedThreadFactory = new ThreadFactory() {
            final AtomicInteger sequence = new AtomicInteger(1);

            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                int seq = this.sequence.getAndIncrement();
                thread.setName("future-task-thread" + (seq > 1 ? "-" + seq : ""));
                if (!thread.isDaemon()) {
                    thread.setDaemon(true);
                }

                return thread;
            }
        };
        return new ThreadPoolExecutor(10, 200,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024),
                namedThreadFactory,
                new ThreadPoolExecutor.AbortPolicy());
    }
}
