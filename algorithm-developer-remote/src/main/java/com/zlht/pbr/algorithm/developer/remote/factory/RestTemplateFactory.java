package com.zlht.pbr.algorithm.developer.remote.factory;

import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author zi jian Wang
 */
public class RestTemplateFactory {

    public static RestTemplate getRestTemplate() {

        SimpleClientHttpRequestFactory httpRequestFactory = new SimpleClientHttpRequestFactory();
        httpRequestFactory.setConnectTimeout(5000);
        httpRequestFactory.setReadTimeout(5000);
        RestTemplate restTemplate = new RestTemplate(httpRequestFactory);

        return restTemplate;
    }
}