package com.fun.uncle.springboot2020.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.DeleteObjectsResult;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.auth.sts.AssumeRoleRequest;
import com.aliyuncs.auth.sts.AssumeRoleResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.List;

@Service
public class OssServiceImpl implements OssService {

    @Autowired
    OSSClientConfig ossClientConfig;

    @Override
    public AssumeRoleResponse.Credentials getStsToken() {
        String endpoint = "sts.cn-hangzhou.aliyuncs.com";
        String accessKeyId = ossClientConfig.accessKey;
        String accessKeySecret = ossClientConfig.secret;
        String roleArn = ossClientConfig.roleArn;
        String roleSessionName = ossClientConfig.roleSessionName;
        try {
            //添加endpoint（直接使用STS endpoint，前两个参数留空，无需添加region ID）
            DefaultProfile.addEndpoint("", "Sts", endpoint);
            //构造default profile（参数留空，无需添加region ID）
            IClientProfile profile = DefaultProfile.getProfile("", accessKeyId, accessKeySecret);
            // 用profile构造client
            DefaultAcsClient client = new DefaultAcsClient(profile);
            final AssumeRoleRequest request = new AssumeRoleRequest();
            request.setMethod(MethodType.POST);
            request.setRoleArn(roleArn);
            request.setRoleSessionName(roleSessionName);
            // 设置凭证有效时间
            request.setDurationSeconds(1000L);
            final AssumeRoleResponse response = client.getAcsResponse(request);
            return response.getCredentials();
        } catch (ClientException e) {
            System.out.println("Failed：");
            System.out.println("Error code: " + e.getErrCode());
            System.out.println("Error message: " + e.getErrMsg());
            System.out.println("RequestId: " + e.getRequestId());
        }
        return null;
    }

    @Override
    public String getOssPrivateUrl(String fileName) {
        // 设置URL过期时间为1小时。
        Date expiration = new Date(System.currentTimeMillis() + 360000 * 1000);
        return getOssPrivateUrl(fileName, expiration);
    }

    @Override
    public String getOssPrivateUrl(String fileName, Date expiration) {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "https://" + ossClientConfig.endpoint;
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = ossClientConfig.accessKey;
        String accessKeySecret = ossClientConfig.secret;
        String bucketName = ossClientConfig.privateBucketName;
        String objectName = fileName;

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
        URL url = ossClient.generatePresignedUrl(bucketName, objectName, expiration);

        // 关闭OSSClient。
        ossClient.shutdown();
        return url.toString();
    }

    @Override
    public boolean uploadOss(String objectName, String filePath) {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = ossClientConfig.accessKey;
        String accessKeySecret = ossClientConfig.secret;
        String bucketName = ossClientConfig.bucketName;
        // 填写Bucket名称，例如examplebucket。
        // 填写Object完整路径，完整路径中不能包含Bucket名称，例如exampledir/exampleobject.txt。
//        String objectName = "exampledir/exampleobject.txt";
        // 填写本地文件的完整路径，例如D:\\localpath\\examplefile.txt。
        // 如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
//        String filePath= "D:\\localpath\\examplefile.txt";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {
            // 创建PutObjectRequest对象。
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, new File(filePath));
            // 如果需要上传时设置存储类型和访问权限，请参考以下示例代码。
            // ObjectMetadata metadata = new ObjectMetadata();
            // metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
            // metadata.setObjectAcl(CannedAccessControlList.Private);
            // putObjectRequest.setMetadata(metadata);
            // 上传文件。
            PutObjectResult result = ossClient.putObject(putObjectRequest);
            return true;
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (com.aliyun.oss.ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return false;
    }

    @Override
    public List<String> deleteFiles(List<String> fileNames) {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = ossClientConfig.accessKey;
        String accessKeySecret = ossClientConfig.secret;
        String bucketName = ossClientConfig.privateBucketName;

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 删除文件。
        // 填写需要删除的多个文件完整路径。文件完整路径中不能包含Bucket名称。

        DeleteObjectsRequest deleteObjectsRequest = new DeleteObjectsRequest(bucketName).withKeys(fileNames);
        deleteObjectsRequest.setQuiet(true);

        DeleteObjectsResult deleteObjectsResult = ossClient.deleteObjects(deleteObjectsRequest);
        List<String> deletedObjects = deleteObjectsResult.getDeletedObjects();

        // 关闭OSSClient。
        ossClient.shutdown();

        return deletedObjects;
    }

    @Override
    public String getOssPublicUrl(String objectKey) {
        Assert.notNull(objectKey, "参数[objectKey]不能为空");
        return "https://" + ossClientConfig.bucketName + ossClientConfig.endpoint + "/" + objectKey;
    }
}
