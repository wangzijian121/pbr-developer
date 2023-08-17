package com.zlht.pose.developer.remote.management.factory;

import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;

public class RestTemplateFactory {

    public static RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        // 添加一级缓存
        restTemplate.setRequestFactory(new SimpleClientHttpRequestFactory());
        // 添加基本Http头
        LinkedList<HttpMessageConverter<?>> messageConverters = new LinkedList<>();
        StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
        messageConverters.add(stringConverter);
        restTemplate.setMessageConverters(messageConverters);
        return restTemplate;
    }
}