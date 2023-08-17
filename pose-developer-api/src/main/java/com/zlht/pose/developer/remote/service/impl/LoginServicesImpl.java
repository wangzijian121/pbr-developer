package com.zlht.pose.developer.remote.service.impl;


import com.zlht.pose.developer.remote.service.LoginServicesI;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class LoginServicesImpl implements LoginServicesI {




    public static void main(String[] args) {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Accept", "*/*");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("password", "123456");
        params.add("username", "root");

        String uri = "http://192.168.0.104:8080/login";
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.POST, requestEntity, String.class);
        System.out.println(responseEntity);
    }
}
