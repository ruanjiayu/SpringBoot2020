package com.fun.uncle.springboot2020.controller;

import com.fun.uncle.springboot2020.request.IMRequest;
import com.fun.uncle.springboot2020.utils.LoggerUtil;
import com.google.common.collect.Lists;
import io.github.doocs.im.ImClient;
import io.github.doocs.im.constant.ActionStatus;
import io.github.doocs.im.constant.TagProfile;
import io.github.doocs.im.model.request.PortraitGetRequest;
import io.github.doocs.im.model.response.AccountCheckResult;
import io.github.doocs.im.model.response.PortraitGetResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * @Description:
 * @Author: summer
 * @CreateDate: 2022/10/12 20:10
 * @Version: 1.0.0
 */
@Api(tags = "im")
@RestController
@RequestMapping(value = "im")
public class IMController {

    private final static Logger common_info = LoggerUtil.COMMON_INFO;

    @Autowired
    private ImClient imClient;

    @ApiOperation("用户数据属性")
    @GetMapping(value = "/portrait/get")
    public PortraitGetResult portraitGet(String userId) throws IOException {
        List<String> tagList = Collections.singletonList(TagProfile.IM_NICK);
        List<String> toAccount = Collections.singletonList(userId);
        PortraitGetRequest request = PortraitGetRequest.builder()
                .tagList(tagList)
                .toAccount(toAccount)
                .build();

        return imClient.profile.portraitGet(request);
    }

    @ApiOperation("测试下回调")
    @PostMapping(value = "/hello")
    public AccountCheckResult hello(IMRequest request,
                                    @RequestBody Object obj){
        LoggerUtil.info(common_info, "测试下回调", obj);
        AccountCheckResult result = new AccountCheckResult();
        result.setResultItems(Lists.newArrayList());
        result.setActionStatus(ActionStatus.OK);
        result.setErrorInfo("");
        result.setErrorCode(0);
        return result;
    }

}
