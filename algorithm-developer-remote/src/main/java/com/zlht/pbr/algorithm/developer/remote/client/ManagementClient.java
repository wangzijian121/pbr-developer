package com.zlht.pbr.algorithm.developer.remote.client;

import com.zlht.pbr.algorithm.developer.remote.configuration.ManagementConfiguration;
import com.zlht.pbr.algorithm.developer.remote.factory.RestTemplateFactory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author zi jian Wang
 */
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
    private ManagementConfiguration managementConfiguration;

    /**
     * 连通状态检查
     */
    public Boolean checkConnect() {
        String ip = managementConfiguration.getIp();
        int port = managementConfiguration.getPort();
        try (Socket socket = new Socket()) {
            InetSocketAddress endpoint = new InetSocketAddress(ip, port);
            socket.connect(endpoint, 1000);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * 发送请求
     */
    public ResponseEntity<String> sendRequest(String suffix, HttpMethod httpMethod, HttpEntity requestEntity) {
        RestTemplate restTemplate = null;
        String url;
        ResponseEntity<String> responseEntity = null;
        try {
            restTemplate = RestTemplateFactory.getRestTemplate();
            url = requestType + managementConfiguration.getIp() + ":" + managementConfiguration.getPort() + "/" + suffix;
            responseEntity = restTemplate.exchange(url, httpMethod, requestEntity, String.class);
        } catch (Exception e) {
            throw new RuntimeException("请求异常！", e);
        }
        return responseEntity;
    }

    /**
     * load header
     */
    public void setClientHeader(String headerName, String headerValue) {
        this.headers.set(headerName, headerValue);
    }

}
