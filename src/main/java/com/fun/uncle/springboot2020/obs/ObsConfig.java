package com.fun.uncle.springboot2020.obs;

import com.huaweicloud.sdk.core.auth.GlobalCredentials;
import com.huaweicloud.sdk.core.auth.ICredential;
import com.huaweicloud.sdk.iam.v3.IamClient;
import com.huaweicloud.sdk.iam.v3.model.CreateTemporaryAccessKeyByTokenRequest;
import com.huaweicloud.sdk.iam.v3.model.CreateTemporaryAccessKeyByTokenRequestBody;
import com.huaweicloud.sdk.iam.v3.model.IdentityToken;
import com.huaweicloud.sdk.iam.v3.model.ServicePolicy;
import com.huaweicloud.sdk.iam.v3.model.ServiceStatement;
import com.huaweicloud.sdk.iam.v3.model.TokenAuth;
import com.huaweicloud.sdk.iam.v3.model.TokenAuthIdentity;
import com.huaweicloud.sdk.iam.v3.region.IamRegion;
import com.obs.services.ObsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: summer
 * @CreateDate: 2023/6/5 19:41
 * @Version: 1.0.0
 */
@Configuration
public class ObsConfig {

    @Autowired
    private ObsProperties obsProperties;


    @Bean(name = "obsClient")
    public ObsClient obsClient() {
        return new ObsClient(obsProperties.getAk(), obsProperties.getSk(), obsProperties.getEndPoint());
    }

    @Bean(name = "iamClient")
    public IamClient iamClient() {
        ICredential auth = new GlobalCredentials()
                .withAk(obsProperties.getAk())
                .withSk(obsProperties.getSk());

        return IamClient.newBuilder()
                .withCredential(auth)
                .withRegion(IamRegion.valueOf("ap-southeast-1"))
                .build();
    }


    @Bean("temporaryAccessKeyByTokenRequest")
    public CreateTemporaryAccessKeyByTokenRequest request() {
        CreateTemporaryAccessKeyByTokenRequest request = new CreateTemporaryAccessKeyByTokenRequest();
        CreateTemporaryAccessKeyByTokenRequestBody body = new CreateTemporaryAccessKeyByTokenRequestBody();
        List<String> listStatementResource = new ArrayList<>();
//        listStatementResource.add("OBS:*:*:object:dspa-api/public/*");
        listStatementResource.add("OBS:*:*:object:*");

        List<String> listStatementAction = new ArrayList<>();
        listStatementAction.add("obs:object:GetObject");
        listStatementAction.add("obs:bucket:ListBucket");
        listStatementAction.add("obs:object:ListMultipartUploadParts");
        listStatementAction.add("obs:object:PutObject");

        List<ServiceStatement> listPolicyStatement = new ArrayList<>();
        listPolicyStatement.add(
                new ServiceStatement()
                        .withAction(listStatementAction)
                        .withEffect(ServiceStatement.EffectEnum.fromValue("Allow"))
                        .withResource(listStatementResource)
        );
        ServicePolicy policyIdentity = new ServicePolicy();
        policyIdentity.withVersion("1.1")
                .withStatement(listPolicyStatement);

        IdentityToken tokenIdentity = new IdentityToken();
        tokenIdentity.withDurationSeconds(86400);
        List<TokenAuthIdentity.MethodsEnum> listIdentityMethods = new ArrayList<>();
        listIdentityMethods.add(TokenAuthIdentity.MethodsEnum.fromValue("token"));
        TokenAuthIdentity identityAuth = new TokenAuthIdentity();
        identityAuth.withMethods(listIdentityMethods)
                .withToken(tokenIdentity)
                .withPolicy(policyIdentity);
        TokenAuth authbody = new TokenAuth();
        authbody.withIdentity(identityAuth);
        body.withAuth(authbody);
        request.withBody(body);
        return request;
    }


}
