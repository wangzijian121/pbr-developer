package com.zlht.pose.developer.remote.management.factory;

import com.zlht.pose.developer.remote.management.client.ManagementClient;


public class ManagementClientFactory {

    public static    ManagementClient getManagementClient() {
        ManagementClient  managementClient = ManagementClient.builder()
                .headers(null)
                .requestType("http://")
                .proxy(null)
                .timeOut(1000)
                .build();
        return managementClient;
    }
}
