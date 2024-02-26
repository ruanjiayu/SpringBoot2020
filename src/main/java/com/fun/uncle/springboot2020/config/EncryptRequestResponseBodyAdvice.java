package com.fun.uncle.springboot2020.config;

import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fun.uncle.springboot2020.request.AesParams;
import com.fun.uncle.springboot2020.vo.CommonResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

/**
 * @Description: 请求体和返回体进行加密操作
 * @Author: summer
 * @CreateDate: 2024/2/26 16:33
 * @Version: 1.0.0
 */
@ControllerAdvice
public class EncryptRequestResponseBodyAdvice extends RequestBodyAdviceAdapter implements ResponseBodyAdvice<CommonResult> {

    private final Log log = LogFactory.getLog(EncryptRequestResponseBodyAdvice.class);
    private final byte[] key = "B6217B035CD94F78".getBytes();

    /**
     * 判断是否对请求体进行数据解密操作
     * @param methodParameter
     * @param targetType
     * @param converterType
     * @return false 为 不进行 beforeBodyRead(不进行解密)操作。 true 表示需要进行解密
     * false 情况下，就是请求体直接打到controller层
     * true 情况下，需要解密，然后解密后的数据打到controller层
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        log.info(methodParameter.getParameterType());
        // 判断controller层请求体是否是AesParams子类。我们通过实现AesParams的接口来作为子类。例如HelloRequest，UserRequest
        return AesParams.class.isAssignableFrom(methodParameter.getParameterType());
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        return new HttpInputMessage() {
            @Override
            public InputStream getBody() throws IOException {
                InputStream inputStream = inputMessage.getBody();
                byte[] bytes = new byte[inputStream.available()];
                inputStream.read(bytes);
                String jsonString = new String(bytes, StandardCharsets.UTF_8); // Convert byte[] to String
                JSONObject jsonObject = JSON.parseObject(jsonString);
                String data = jsonObject.getString("data");
                log.info("RequestBody 密文:" + data);
                data = SecureUtil.aes(key).decryptStr(data);
                log.info("RequestBody 明文:" + data);
                return new ByteArrayInputStream(data.getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                return inputMessage.getHeaders();
            }
        };
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return CommonResult.class.equals(returnType.getParameterType());
    }

    @Override
    public CommonResult beforeBodyWrite(CommonResult body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body.getData() != null) {
            String data = JSON.toJSONString(body.getData());
            log.info("ResponseBody 明文:" + data);
            data = SecureUtil.aes(key).encryptBase64(data);
            log.info("ResponseBody 密文:" + data);
            body.setData(data);
        }
        return body;
    }
}
