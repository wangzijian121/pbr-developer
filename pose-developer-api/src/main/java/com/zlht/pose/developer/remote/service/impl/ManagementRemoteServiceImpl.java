package com.zlht.pose.developer.remote.service.impl;

import com.zlht.pose.developer.remote.management.client.ManagementClient;
import com.zlht.pose.developer.remote.management.factory.ManagementClientFactory;
import com.zlht.pose.developer.remote.management.model.Result;
import com.zlht.pose.developer.remote.service.ManagementRemoteServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
public class ManagementRemoteServiceImpl extends BaseServiceImpl implements ManagementRemoteServiceI {


    @Autowired
    private ManagementClient managementClient;


    @Override
    public Result checkConnect(String username, String password) {
        System.out.println(managementClient.checkConnect());
        return new Result();
    }

    @Override
    public Result login(String username, String password) {
        //TODO
        managementClient = ManagementClientFactory.getManagementClient();
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("username", username);
        map.add("password", password);
        ResponseEntity<Result> response = managementClient.sendRequest("developer_login", HttpMethod.POST, map);
        System.out.println(response.getBody());
        return null;
    }

}
