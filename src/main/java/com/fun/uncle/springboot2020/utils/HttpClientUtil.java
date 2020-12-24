package com.fun.uncle.springboot2020.utils;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
*
* @Description: http工具类
* @Author: Summer
* @DateTime: 2020/12/24 4:14 下午
* @Version: 0.0.1-SNAPSHOT
*/
public class HttpClientUtil {
    /**
     * https协议的请求客户端
     */
    private static CloseableHttpClient httpsClient = null;

    /**
     * http协议的请求客户端
     */
    private static CloseableHttpClient httpClient = null;

    /**
     * http协议的请求客户端
     */
    private static CloseableHttpClient activityHttpClient = null;

    private static final Logger log = LoggerUtil.COMMON_INFO;

    private static final Lock LOCKHTTPSCLIENT = new ReentrantLock();

    private static final Lock LOCKHTTPCLIENT = new ReentrantLock();

    static {
        // 初始化客户端
        getHttpClient();
        getHttpsClient();
        getHttpClient(2000);
    }

    /**
     * 获取https的请求客户端、多服务不做任务验证
     *
     * @return
     * @Author huangbugan
     * @CreateDate 2015年8月26日
     */
    private static CloseableHttpClient getHttpsClient() {
        if (httpsClient == null) {
            try {
                LOCKHTTPSCLIENT.lock();
                if (httpsClient == null) {
                    SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                        // 信任所有
                        @Override
                        public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                            return true;
                        }
                    }).build();
                    HostnameVerifier hostnameVerifier = new HostnameVerifier() {
                        @Override
                        public boolean verify(String arg0, SSLSession arg1) {
                            // 对服务器不做验证
                            return true;
                        }
                    };
                    SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
                    RequestConfig.Builder builder = RequestConfig.custom();
                    // 单位毫秒
                    builder.setConnectTimeout(5000);
                    builder.setSocketTimeout(5000);
                    PoolingHttpClientConnectionManager clientConnectionManager = new PoolingHttpClientConnectionManager();
                    clientConnectionManager.setMaxTotal(500);
                    clientConnectionManager.setDefaultMaxPerRoute(300);
                    httpsClient = HttpClients.custom().setConnectionManager(clientConnectionManager)
                            .setDefaultRequestConfig(builder.build()).setSSLSocketFactory(sslsf).build();
                }
            } catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException e) {
                LoggerUtil.error(log, e);
            } finally {
                LOCKHTTPSCLIENT.unlock();
            }
        }
        return httpsClient;
    }

    /**
     * 获取http的客户端
     *
     * @return
     * @Author huangbugan
     * @CreateDate 2015年8月26日
     */
    private static CloseableHttpClient getHttpClient() {
        if (httpClient == null) {
            try {
                LOCKHTTPCLIENT.lock();
                if (httpClient == null) {
                    RequestConfig.Builder builder = RequestConfig.custom();
                    // 单位毫秒 默认3分钟
                    builder.setConnectTimeout(5000);
                    builder.setSocketTimeout(5000);
                    PoolingHttpClientConnectionManager clientConnectionManager = new PoolingHttpClientConnectionManager();
                    clientConnectionManager.setMaxTotal(500);
                    clientConnectionManager.setDefaultMaxPerRoute(300);
                    HttpClientBuilder clientBuilder = HttpClients.custom().setConnectionManager(clientConnectionManager)
                            .setDefaultRequestConfig(builder.build());
                    httpClient = clientBuilder.build();
                }
            } finally {
                LOCKHTTPCLIENT.unlock();
            }
        }
        return httpClient;
    }


    /**
     * 获取http的客户端
     *
     * @return
     * @Author huangbugan
     * @CreateDate 2015年8月26日
     */
    private static CloseableHttpClient getHttpClient(Integer time) {
        if (activityHttpClient == null) {
            if (Objects.isNull(time)) {
                time = 2000;
            }
            try {
                LOCKHTTPCLIENT.lock();
                if (activityHttpClient == null) {
                    RequestConfig.Builder builder = RequestConfig.custom();
                    // 单位毫秒 默认2分钟
                    builder.setConnectTimeout(time);
                    builder.setSocketTimeout(time);
                    PoolingHttpClientConnectionManager clientConnectionManager = new PoolingHttpClientConnectionManager();
                    clientConnectionManager.setMaxTotal(500);
                    clientConnectionManager.setDefaultMaxPerRoute(300);
                    HttpClientBuilder clientBuilder = HttpClients.custom().setConnectionManager(clientConnectionManager)
                            .setDefaultRequestConfig(builder.build());
                    activityHttpClient = clientBuilder.build();
                }
            } finally {
                LOCKHTTPCLIENT.unlock();
            }
        }
        return activityHttpClient;
    }

    /**
     * 执行post请求
     *
     * @param nameValuePairs 请求参数、不能为空
     * @param url            请求url 不能为空
     * @param charSet        请求字符编码 不能为空
     * @return 返回HttpResponseDTO {request :true表示http请求成功、false
     * 表示http请求失败。responseCode：表示请求响应的http代码如200，responseResult：响应数据}
     * @Author huangbugan
     * @CreateDate 2015年8月26日
     */
    public static HttpResponse executePost(final List<NameValuePair> nameValuePairs, List<Header> headers,
                                           final String url, final String charSet) {
        Assert.notNull(nameValuePairs, "nameValuePairs must not null");
        Assert.notNull(url, "url must not null");
        Assert.notNull(charSet, "charSet must not null");
        Long startTime = System.currentTimeMillis();
        HttpResponse result = new HttpResponse();
        CloseableHttpResponse response = null;
        try {
            HttpPost postMethod = new HttpPost(url);

            StringBuffer stringBuffer = new StringBuffer();
            for (NameValuePair nameValuePair : nameValuePairs) {
                stringBuffer.append(nameValuePair.getName()).append("=")
                        .append(URLEncoder.encode(nameValuePair.getValue(), "utf-8"));
            }
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(nameValuePairs, charSet);
            // urlEncodedFormEntity.setContentEncoding(charSet);
            postMethod.setEntity(urlEncodedFormEntity);
            // postMethod.setHeader(new BasicHeader("Content-Type",
            // "application/json"));
            if (!CollectionUtils.isEmpty(headers)) {
                for (Header header : headers) {
                    postMethod.addHeader(header);
                }
            }
            // 根据url 区分请求协议
            if (StringUtils.startsWithIgnoreCase(url, "https://")) {
                response = getHttpsClient().execute(postMethod);
            } else {
                response = getHttpClient().execute(postMethod);
            }
            result = executeResponse(response, charSet);
        } catch (Exception e) {
            LoggerUtil.error(log, e);
        } finally {
            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    LoggerUtil.error(log, e);
                }
            }
        }
        long endTime = System.currentTimeMillis();
        Long diffTimes = endTime - startTime;
        if (diffTimes.compareTo(5000L) > 0) {
            LoggerUtil.HTTP_REMOTE.info("请求响应时间超过5秒,url:{},diffTimes:{}", url, diffTimes);
        }
        return result;
    }

    public static HttpResponse executePostThrowException(final List<NameValuePair> nameValuePairs, List<Header> headers,
                                                         final String url, final String charSet) throws IOException {
        Assert.notNull(nameValuePairs, "nameValuePairs must not null");
        Assert.notNull(url, "url must not null");
        Assert.notNull(charSet, "charSet must not null");
        Long startTime = System.currentTimeMillis();
        HttpResponse result = new HttpResponse();
        CloseableHttpResponse response = null;
        try {
            HttpPost postMethod = new HttpPost(url);

            StringBuffer stringBuffer = new StringBuffer();
            for (NameValuePair nameValuePair : nameValuePairs) {
                stringBuffer.append(nameValuePair.getName()).append("=")
                        .append(URLEncoder.encode(nameValuePair.getValue(), "utf-8"));
            }
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(nameValuePairs, charSet);
            // urlEncodedFormEntity.setContentEncoding(charSet);
            postMethod.setEntity(urlEncodedFormEntity);
            // postMethod.setHeader(new BasicHeader("Content-Type",
            // "application/json"));
            if (!CollectionUtils.isEmpty(headers)) {
                for (Header header : headers) {
                    postMethod.addHeader(header);
                }
            }
            // 根据url 区分请求协议
            if (StringUtils.startsWithIgnoreCase(url, "https://")) {
                response = getHttpsClient().execute(postMethod);
            } else {
                response = getHttpClient().execute(postMethod);
            }
            result = executeResponse(response, charSet);
        } finally {
            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    LoggerUtil.error(log, e);
                }
            }
        }
        long endTime = System.currentTimeMillis();
        Long diffTimes = endTime - startTime;
        if (diffTimes.compareTo(5000L) > 0) {
            LoggerUtil.HTTP_REMOTE.info("请求响应时间超过5秒,url:{},diffTimes:{}", url, diffTimes);
        }
        return result;
    }

    public static HttpResponse executePost(final List<NameValuePair> nameValuePairs, final String url) {
        return executePost(nameValuePairs, null, url, "UTF-8");
    }

    public static HttpResponse executeGet(String url) {
        return executeGet(null, url, "UTF-8", null);
    }

    public static HttpResponse executeGet(List<Header> headers, String url) {
        return executeGet(headers, url, "UTF-8", null);
    }

    public static HttpResponse executeGet(String url, Integer time) {
        return executeGet(null, url, "UTF-8", time);
    }

    public static HttpResponse executeGet(List<Header> headers, final String url, final String charSet, Integer time) {
        Assert.notNull(url, "url must not null");
        Assert.notNull(charSet, "charSet must not null");
        long startTime = System.currentTimeMillis();
        HttpResponse result = new HttpResponse();
        CloseableHttpResponse response = null;
        try {
            HttpGet httpGet = new HttpGet(url);
            if (!CollectionUtils.isEmpty(headers)) {
                for (Header header : headers) {
                    httpGet.addHeader(header);
                }
            }
            // 根据url 区分请求协议
            if (StringUtils.startsWithIgnoreCase(url, "https://")) {
                response = getHttpsClient().execute(httpGet);
            } else {
                if (Objects.nonNull(time)) {
                    response = getHttpClient(time).execute(httpGet);
                } else {
                    response = getHttpClient().execute(httpGet);
                }
            }
            result = executeResponse(response, charSet);
        } catch (Exception e) {
            LoggerUtil.error(log, e);
        } finally {
            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    LoggerUtil.error(log, e);
                }
            }
        }
        long endTime = System.currentTimeMillis();
        Long diffTimes = endTime - startTime;
        if (diffTimes.compareTo(5000L) > 0) {
            LoggerUtil.HTTP_REMOTE.info("请求响应时间超过5秒,url:{},diffTimes:{}", url, diffTimes);
        }
        return result;
    }

    public static HttpResponse executeGetThrowException(String url) throws Exception {
        return executeGetThrowException(null, url, "UTF-8");
    }

    public static HttpResponse executeGetThrowException(List<Header> headers, final String url, final String charSet)
            throws Exception {
        Assert.notNull(url, "url must not null");
        Assert.notNull(charSet, "charSet must not null");
        long startTime = System.currentTimeMillis();
        HttpResponse result = new HttpResponse();
        CloseableHttpResponse response = null;
        try {
            HttpGet httpGet = new HttpGet(url);
            if (!CollectionUtils.isEmpty(headers)) {
                for (Header header : headers) {
                    httpGet.addHeader(header);
                }
            }
            // 根据url 区分请求协议
            if (StringUtils.startsWithIgnoreCase(url, "https://")) {
                response = getHttpsClient().execute(httpGet);
            } else {
                response = getHttpClient().execute(httpGet);
            }
            result = executeResponse(response, charSet);
        } finally {
            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    LoggerUtil.error(log, e);
                }
            }
        }
        long endTime = System.currentTimeMillis();
        Long diffTimes = endTime - startTime;
        if (diffTimes.compareTo(5000L) > 0) {
            LoggerUtil.HTTP_REMOTE.info("请求响应时间超过5秒,url:{},diffTimes:{}", url, diffTimes);
        }
        return result;
    }

    public static HttpResponse executePut(List<NameValuePair> nameValuePairs, List<Header> headers, final String url,
                                          final String charSet) {
        Assert.notNull(url, "url must not null");
        Assert.notNull(charSet, "charSet must not null");
        HttpResponse result = new HttpResponse();
        CloseableHttpResponse response = null;
        try {
            HttpPut httpPut = new HttpPut(url);
            if (!CollectionUtils.isEmpty(headers)) {
                for (Header header : headers) {
                    httpPut.addHeader(header);
                }
            }
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(nameValuePairs, charSet);
            httpPut.setEntity(urlEncodedFormEntity);
            // 根据url 区分请求协议
            if (StringUtils.startsWithIgnoreCase(url, "https://")) {
                response = getHttpsClient().execute(httpPut);
            } else {
                response = getHttpClient().execute(httpPut);
            }
            result = executeResponse(response, charSet);
        } catch (Exception e) {
            LoggerUtil.error(log, e);
        } finally {
            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    LoggerUtil.error(log, e);
                }
            }
        }
        return result;
    }

    public static HttpResponse executePost(final String content, List<Header> headers, final String url) {
        return executePost(content, headers, url, "UTF-8");
    }

    /**
     * 执行post请求(直接提交字符串数据)
     *
     * @param content 请求数据
     * @param url     请求url 不能为空
     * @param charSet 请求字符编码 不能为空
     * @return 返回HttpResponseDTO {request :true表示http请求成功、false
     * 表示http请求失败。responseCode：表示请求响应的http代码如200，responseResult：响应数据}
     * @Author zhanghaifeng
     * @CreateDate 2015年12月25日
     */
    public static HttpResponse executePost(final String content, List<Header> headers, final String url,
                                           final String charSet) {
        Assert.notNull(url, "url must not null");
        Assert.notNull(charSet, "charSet must not null");

        long startTime = System.currentTimeMillis();
        HttpResponse result = new HttpResponse();
        CloseableHttpResponse response = null;
        try {
            HttpPost postMethod = new HttpPost(url);
            StringEntity stringEntity = new StringEntity(content, charSet);
            stringEntity.setContentType("application/json");
            postMethod.setEntity(stringEntity);
            if (!CollectionUtils.isEmpty(headers)) {
                for (Header header : headers) {
                    postMethod.addHeader(header);
                }
            }
            // 根据url 区分请求协议
            if (StringUtils.startsWithIgnoreCase(url, "https://")) {
                response = getHttpsClient().execute(postMethod);
            } else {
                response = getHttpClient().execute(postMethod);
            }
            result = executeResponse(response, charSet);
        } catch (Exception e) {
            LoggerUtil.error(log, e);
        } finally {
            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    LoggerUtil.error(log, e);
                }
            }
        }
        long endTime = System.currentTimeMillis();
        Long diffTimes = endTime - startTime;
        if (diffTimes.compareTo(5000L) > 0) {
            LoggerUtil.HTTP_REMOTE.info("请求响应时间超过5秒,url:{},diffTimes:{}", url, diffTimes);
        }
        return result;
    }

    public static HttpResponse postHttpsRestfulAPI(String url, String json, final String charSet) {
        Assert.notNull(url, "url must not null");
        Assert.notNull(charSet, "charSet must not null");
        long startTime = System.currentTimeMillis();
        HttpResponse result = new HttpResponse();
        CloseableHttpResponse response = null;
        try {
            HttpPost postMethod = new HttpPost(url);

            postMethod.addHeader("Content-Type", "application/json");
            HttpEntity item = new ByteArrayEntity(json.getBytes());
            postMethod.setEntity(item);
            // 根据url 区分请求协议
            if (StringUtils.startsWithIgnoreCase(url, "https://")) {
                response = getHttpsClient().execute(postMethod);
            } else {
                response = getHttpClient().execute(postMethod);
            }
            result = executeResponse(response, charSet);
        } catch (Exception e) {
            LoggerUtil.error(log, e);
        } finally {
            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    LoggerUtil.error(log, e);
                }
            }
        }
        long endTime = System.currentTimeMillis();
        Long diffTimes = endTime - startTime;
        if (diffTimes.compareTo(5000L) > 0) {
            LoggerUtil.HTTP_REMOTE.info("请求响应时间超过5秒,url:{},diffTimes:{}", url, diffTimes);
        }
        return result;

    }

    public static HttpResponse executeGet(List<Header> headers, final String url, final String charSet,
                                          boolean logExcepetion) {
        Assert.notNull(url, "url must not null");
        Assert.notNull(charSet, "charSet must not null");
        HttpResponse result = new HttpResponse();
        CloseableHttpResponse response = null;
        try {
            HttpGet httpGet = new HttpGet(url);
            if (!CollectionUtils.isEmpty(headers)) {
                for (Header header : headers) {
                    httpGet.addHeader(header);
                }
            }
            // 根据url 区分请求协议
            if (StringUtils.startsWithIgnoreCase(url, "https://")) {
                response = getHttpsClient().execute(httpGet);
            } else {
                response = getHttpClient().execute(httpGet);
            }
            result = executeResponse(response, charSet);
        } catch (Exception e) {
            if (logExcepetion) {
                LoggerUtil.error(log, e);
            } else {
                LoggerUtil.COMMON_ERROR.error(e.getMessage());
            }

        } finally {
            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    LoggerUtil.error(log, e);
                }
            }
        }
        return result;
    }

    /**
     * 执行post请求(直接提交字符串数据)
     *
     * @param content 请求数据
     * @param url     请求url 不能为空
     * @param charSet 请求字符编码 不能为空
     * @return 返回HttpResponseDTO {request :true表示http请求成功、false
     * 表示http请求失败。responseCode：表示请求响应的http代码如200，responseResult：响应数据}
     * @Author zhanghaifeng
     * @CreateDate 2015年12月25日
     */
    public static HttpResponse executePost(final String content, List<Header> headers, final String url,
                                           final String charSet, boolean logException) {
        Assert.notNull(url, "url must not null");
        Assert.notNull(charSet, "charSet must not null");
        HttpResponse result = new HttpResponse();
        CloseableHttpResponse response = null;
        try {
            HttpPost postMethod = new HttpPost(url);
            StringEntity stringEntity = new StringEntity(content, charSet);
            stringEntity.setContentType("application/json");
            postMethod.setEntity(stringEntity);
            if (!CollectionUtils.isEmpty(headers)) {
                for (Header header : headers) {
                    postMethod.addHeader(header);
                }
            }
            // 根据url 区分请求协议
            if (StringUtils.startsWithIgnoreCase(url, "https://")) {
                response = getHttpsClient().execute(postMethod);
            } else {
                response = getHttpClient().execute(postMethod);
            }
            result = executeResponse(response, charSet);
        } catch (Exception e) {
            if (logException) {
                LoggerUtil.error(log, e);
            } else {
                LoggerUtil.COMMON_ERROR.error(e.getMessage());
            }

        } finally {
            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    LoggerUtil.error(log, e);
                }
            }
        }
        return result;
    }

    private static HttpResponse executeResponse(CloseableHttpResponse response, String charSet) {
        HttpResponse result = new HttpResponse();
        result.setResponseCode(response.getStatusLine().getStatusCode());
        HttpEntity responseBody = response.getEntity();
        try {
            result.setResponseResult(EntityUtils.toString(responseBody, charSet));
            EntityUtils.consume(responseBody);
            result.setRequest(true);
        } catch (ParseException | IOException e) {
            LoggerUtil.error(log, e);
        }
        return result;
    }

    public static class HttpResponse {

        /**
         * 请求是否成功
         */
        private boolean request = false;

        /**
         * 响应结果
         */
        private String responseResult;

        /**
         * http响应代码
         */
        private int responseCode;

        public boolean isRequest() {
            return request;
        }

        public void setRequest(boolean request) {
            this.request = request;
        }

        public String getResponseResult() {
            return responseResult;
        }

        public void setResponseResult(String responseResult) {
            this.responseResult = responseResult;
        }

        public int getResponseCode() {
            return responseCode;
        }

        public void setResponseCode(int responseCode) {
            this.responseCode = responseCode;
        }

    }

}
