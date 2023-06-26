package com.fun.uncle.springboot2020.config.obs.sts;

import com.huaweicloud.sdk.core.auth.GlobalCredentials;
import com.huaweicloud.sdk.core.auth.ICredential;
import com.huaweicloud.sdk.core.exception.ConnectionException;
import com.huaweicloud.sdk.core.exception.RequestTimeoutException;
import com.huaweicloud.sdk.core.exception.ServiceResponseException;
import com.huaweicloud.sdk.iam.v3.IamClient;
import com.huaweicloud.sdk.iam.v3.model.CreateTemporaryAccessKeyByTokenRequest;
import com.huaweicloud.sdk.iam.v3.model.CreateTemporaryAccessKeyByTokenRequestBody;
import com.huaweicloud.sdk.iam.v3.model.CreateTemporaryAccessKeyByTokenResponse;
import com.huaweicloud.sdk.iam.v3.model.IdentityToken;
import com.huaweicloud.sdk.iam.v3.model.ServicePolicy;
import com.huaweicloud.sdk.iam.v3.model.ServiceStatement;
import com.huaweicloud.sdk.iam.v3.model.TokenAuth;
import com.huaweicloud.sdk.iam.v3.model.TokenAuthIdentity;
import com.huaweicloud.sdk.iam.v3.region.IamRegion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: summer
 * @CreateDate: 2023/6/6 17:29
 * @Version: 1.0.0
 */
public class ObsTempAk {

    public static void main(String[] args) {
        String ak = "#永久的AK#";
        String sk = "#永久的SK#";

        ICredential auth = new GlobalCredentials()
                .withAk(ak)
                .withSk(sk);

        IamClient client = IamClient.newBuilder()
                .withCredential(auth)
                .withRegion(IamRegion.valueOf("cn-east-3"))
                .build();
        CreateTemporaryAccessKeyByTokenRequest request = new CreateTemporaryAccessKeyByTokenRequest();
        CreateTemporaryAccessKeyByTokenRequestBody body = new CreateTemporaryAccessKeyByTokenRequestBody();
        List<String> listStatementResource = new ArrayList<>();
        listStatementResource.add("OBS:*:*:object:*");
        List<String> listConditionCondition = new ArrayList<>();
        listConditionCondition.add("DomainNameExample");
        Map<String, List<String>> listConditionCondition1 = new HashMap<>();
        listConditionCondition1.put("g:DomainName", listConditionCondition);
        Map<String, Map<String, List<String>>> listStatementCondition = new HashMap<>();
        listStatementCondition.put("StringEquals", listConditionCondition1);
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
//                        .withCondition(listStatementCondition)
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
        try {
            CreateTemporaryAccessKeyByTokenResponse response = client.createTemporaryAccessKeyByToken(request);
            System.out.println(response.toString());
        } catch (ConnectionException e) {
            e.printStackTrace();
        } catch (RequestTimeoutException e) {
            e.printStackTrace();
        } catch (ServiceResponseException e) {
            e.printStackTrace();
            System.out.println(e.getHttpStatusCode());
            System.out.println(e.getRequestId());
            System.out.println(e.getErrorCode());
            System.out.println(e.getErrorMsg());
        }
    }
}
