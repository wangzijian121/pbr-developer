package com.zlht.pbr.algorithm.developer.remote.factory;

import com.zlht.pbr.algorithm.developer.remote.client.ManagementClient;
import com.zlht.pbr.algorithm.developer.remote.configuration.ManagementConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class ManagementClientFactory {
    private final static String PROTOCOL = "http://";
    private final static int TIMEOUT = 10000;

    @Autowired
    private ManagementClient managementClient;

    @Autowired
    private ManagementConfiguration managementConfiguration;

    public ManagementClient getManagementClient() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Accept", "*/*");
        managementClient = ManagementClient.builder()
                .managementConfiguration(managementConfiguration)
                .headers(headers)
                .requestType(PROTOCOL)
                .proxy(null)
                .timeOut(TIMEOUT)
                .build();
        return managementClient;
    }
}
