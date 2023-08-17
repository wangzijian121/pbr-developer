package com.zlht.pose.developer.remote.management.client;

import com.zlht.pose.developer.remote.management.configuration.ManagementConfiguration;
import com.zlht.pose.developer.remote.management.factory.RestTemplateFactory;
import com.zlht.pose.developer.remote.management.model.Result;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ManagementClient {

    private String requestType;
    private Integer timeOut;
    private HttpHeaders headers;
    private HttpHeaders proxy;

    @Autowired
    private ManagementConfiguration managementConfiguration;

    /**
     * 连通状态检查
     */
    public Boolean checkConnect() {
        String ip = managementConfiguration.getIp();
        int port = managementConfiguration.getPort();
        try (Socket socket = new Socket()) {
            InetSocketAddress endpoint = new InetSocketAddress(ip, port);
            socket.connect(endpoint, 1000); // 设置连接超时时间为1秒
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * 发送请求
     */
    public ResponseEntity<Result> sendRequest(String suffix, HttpMethod httpMethod, MultiValueMap<String, String> map) {
        RestTemplate restTemplate = RestTemplateFactory.getRestTemplate();
        String url = requestType + managementConfiguration.getIp() + ":" + managementConfiguration.getPort() + "/" + suffix;
        if (headers == null) headers = initHeader();
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(map, headers);
        System.out.println(url);
        ResponseEntity<Result> responseEntity = restTemplate.exchange(url, httpMethod, requestEntity, Result.class);
        return responseEntity;
    }


    /**
     * 默认连接信息
     */
    private HttpHeaders initHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Accept", "*/*");
        return headers;
    }
}
