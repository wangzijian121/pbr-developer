package com.zlht.pose.developer.remote.management.client;

import com.zlht.pose.developer.remote.management.configuration.ManagementConfiguration;
import com.zlht.pose.developer.remote.management.factory.RestTemplateFactory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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


    private final static String REAL_IP = "X-Real-IP";

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
            socket.connect(endpoint, 1000); // 设置连接超时时间为1秒
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * 发送请求
     */
    public ResponseEntity<String> sendRequest(String suffix, HttpMethod httpMethod, MultiValueMap<String, String> map) {
        RestTemplate restTemplate = RestTemplateFactory.getRestTemplate();
        String url = requestType + managementConfiguration.getIp() + ":" + managementConfiguration.getPort() + "/" + suffix;
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(map, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, httpMethod, requestEntity, String.class);
        return responseEntity;
    }

    /**
     * load header
     */
    public void setClientHeader(String ip) {
        this.headers.set(REAL_IP, ip);
    }

}
