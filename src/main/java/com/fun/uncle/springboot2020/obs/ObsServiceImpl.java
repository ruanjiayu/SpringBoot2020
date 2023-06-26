package com.fun.uncle.springboot2020.obs;

import com.fun.uncle.springboot2020.constant.enums.DateTimeFormatterEnum;
import com.fun.uncle.springboot2020.dto.ObsDTO;
import com.fun.uncle.springboot2020.utils.LocalDateTimeUtil;
import com.fun.uncle.springboot2020.utils.LoggerUtil;
import com.fun.uncle.springboot2020.utils.UUIDUtil;
import com.huaweicloud.sdk.core.exception.ConnectionException;
import com.huaweicloud.sdk.core.exception.RequestTimeoutException;
import com.huaweicloud.sdk.core.exception.ServiceResponseException;
import com.huaweicloud.sdk.iam.v3.IamClient;
import com.huaweicloud.sdk.iam.v3.model.CreateTemporaryAccessKeyByTokenRequest;
import com.huaweicloud.sdk.iam.v3.model.CreateTemporaryAccessKeyByTokenResponse;
import com.huaweicloud.sdk.iam.v3.model.Credential;
import com.obs.services.ObsClient;
import com.obs.services.model.HttpMethodEnum;
import com.obs.services.model.TemporarySignatureRequest;
import com.obs.services.model.TemporarySignatureResponse;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: summer
 * @CreateDate: 2023/6/5 19:53
 * @Version: 1.0.0
 */
@Service
public class ObsServiceImpl implements ObsService {

    private static final Logger logger = LoggerUtil.COMMON_INFO;

    @Autowired
    private ObsClient obsClient;

    @Autowired
    private ObsProperties obsProperties;

    @Autowired
    private IamClient iamClient;

    @Autowired
    private CreateTemporaryAccessKeyByTokenRequest temporaryAccessKeyByTokenRequest;

    @Autowired
    private final static String HTTP = "http";

    @Override
    public ObsDTO createObjByTempSignUrl(String fileName, Integer uid, boolean sourceByAdmin) {

        // URL有效期，3600秒
        long expireSeconds = 3600L;

        Map<String, String> headers = new HashMap<>();
        String contentType = "text/plain";
        headers.put("Content-Type", contentType);

        uid = Objects.isNull(uid) ? 0 : uid;

        StringBuilder sb = new StringBuilder();
        sb.append(LocalDateTimeUtil.format(LocalDateTime.now(), DateTimeFormatterEnum.FORMAT_Y_MD_BIAS));
        sb.append("/");
        if (sourceByAdmin) {
            sb.append("admin/");
        }
        sb.append(uid);
        sb.append("/");
        sb.append(UUIDUtil.get() + "_" + fileName);


        String filePath = sb.toString();

        TemporarySignatureRequest request = new TemporarySignatureRequest(HttpMethodEnum.PUT, expireSeconds);
        request.setBucketName(obsProperties.getBucketName());
        request.setObjectKey(filePath);
        request.setHeaders(headers);

        TemporarySignatureResponse response = obsClient.createTemporarySignature(request);

        ObsDTO result = new ObsDTO();
        result.setTempSignUrl(response.getSignedUrl());
        result.setFilePath(filePath);

        return result;
    }

    @Override
    public String getPrivateUrl(String filePath) {

        // URL有效期，3600秒
        long expireSeconds = 72 * 3600L;

        TemporarySignatureRequest request = new TemporarySignatureRequest(HttpMethodEnum.GET, expireSeconds);
        request.setBucketName(obsProperties.getBucketName());
        request.setObjectKey(filePath);

        TemporarySignatureResponse response = obsClient.createTemporarySignature(request);
        return response.getSignedUrl();
    }

    @Override
    public String getPublicUrl(String filePath) {
        return "https://" + obsProperties.getBucketName() + obsProperties.getEndPoint() + "/" + filePath;
    }

    @Override
    public String getFinalUrl(String url) {
        if (StringUtils.isBlank(url)) {
            return url;
        }

        if (url.startsWith(HTTP)) {
            return url;
        }

        return this.getPrivateUrl(url);
    }

    @Override
    public List<String> findFinalUrl(List<String> urls) {
        if (CollectionUtils.isEmpty(urls)) {
            return Collections.emptyList();
        }

        return urls.stream().map(this::getFinalUrl).collect(Collectors.toList());
    }



    @Override
    public Credential getSts() {

        try {
            CreateTemporaryAccessKeyByTokenResponse response = iamClient.createTemporaryAccessKeyByToken(temporaryAccessKeyByTokenRequest);
            return response.getCredential();
        } catch (ConnectionException | RequestTimeoutException | ServiceResponseException e) {
            LoggerUtil.error(logger, e, "OBS 出现异常");
        }
        return null;
    }
}
