package com.zlht.pose.developer.remote.management.factory;

import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class RestTemplateFactory {

    public static RestTemplate getRestTemplate() {

        SimpleClientHttpRequestFactory httpRequestFactory = new SimpleClientHttpRequestFactory();
        httpRequestFactory.setConnectTimeout(5000);
        httpRequestFactory.setReadTimeout(5000);
        RestTemplate restTemplate = new RestTemplate(httpRequestFactory);

        return restTemplate;
    }
}