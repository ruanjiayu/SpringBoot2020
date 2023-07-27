package com.fun.uncle.springboot2020.config;

import com.fun.uncle.springboot2020.utils.LoggerUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 获取
 * @Author: summer
 * @CreateDate: 2023/7/27 10:47
 * @Version: 1.0.0
 */
@Component
public class GetModelPathHandler {

    private final static Logger common_info = LoggerUtil.COMMON_INFO;

    @Autowired
    @Qualifier("requestMappingHandlerMapping")
    private RequestMappingHandlerMapping mappingHandlerMapping;

    /**
     * 获取URL
     *
     * @return
     */
    public List<String> getAllAddTagAnnotationUrl() {
        Map<RequestMappingInfo, HandlerMethod> map = mappingHandlerMapping.getHandlerMethods();

        List<String> list = new ArrayList<>();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet()) {

            RequestMappingInfo info = m.getKey();
            HandlerMethod method = m.getValue();

            PatternsRequestCondition p = info.getPatternsCondition();
            if (method.getMethod().getDeclaringClass().getName().startsWith("com.fun.uncle")) {
                list.addAll(p.getPatterns());
            }

        }
        LoggerUtil.info(common_info, "本服务接口", list);
        return list;
    }

    /**
     * 获取中文和注释相关联
     * @return
     */
    public Map<String, String> getApiOperationToGetMappingMapping() {
        Map<RequestMappingInfo, HandlerMethod> map = mappingHandlerMapping.getHandlerMethods();
        Map<String, String> mapping = new HashMap<>();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet()) {
            RequestMappingInfo info = m.getKey();
            HandlerMethod method = m.getValue();
            PatternsRequestCondition p = info.getPatternsCondition();
            if (method.getMethod().getDeclaringClass().getName().startsWith("com.fun.uncle")) {
                String[] patterns = p.getPatterns().toArray(new String[0]);
                String url = patterns.length > 0 ? patterns[0] : "";
                String apiOperation = getApiOperationValue(method.getMethod());
                mapping.put(apiOperation, url);
            }
        }
        return mapping;
    }

    private String getApiOperationValue(Method method) {
        ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
        if (apiOperation != null) {
            return apiOperation.value();
        }
        return "";
    }
}
