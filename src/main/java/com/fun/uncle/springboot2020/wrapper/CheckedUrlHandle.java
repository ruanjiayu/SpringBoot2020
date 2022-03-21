package com.fun.uncle.springboot2020.wrapper;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description: check Exception
 * @Author: Summer
 * @DateTime: 2022/3/21 11:54 上午
 * @Version: 0.0.1-SNAPSHOT
 */
public class CheckedUrlHandle {

    public List<URL> getURLs() {
        return Stream.of("http://www.baidu.com", "https://www.google.com")
                .map(CheckedWrappableFunctionMapper.wrap(this::createURL))
                .collect(Collectors.toList());
    }

    private URL createURL(String url) throws MalformedURLException {
        return new URL(url);
    }
}
