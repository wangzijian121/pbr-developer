package com.zlht.pose.developer.remote.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zlht.pose.developer.remote.enums.Status;
import com.zlht.pose.developer.remote.management.client.ManagementClient;
import com.zlht.pose.developer.remote.management.factory.ManagementClientFactory;
import com.zlht.pose.developer.remote.management.model.RemoteResult;
import com.zlht.pose.developer.remote.service.ManagementRemoteServiceI;
import com.zlht.pose.developer.remote.service.SessionServiceI;
import com.zlht.pose.developer.remote.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.util.Map;

@Service
public class ManagementRemoteServiceImpl extends BaseServiceImpl implements ManagementRemoteServiceI {


    @Autowired
    private ManagementClientFactory managementClientFactory;

    @Autowired
    private SessionServiceI sessionServiceI;

    @Override
    public boolean checkConnect() {
        return managementClientFactory.getManagementClient().checkConnect();
    }


    @Override
    public Result login(String username, String password, String ip) {

        ManagementClient managementClient = managementClientFactory.getManagementClient();
        managementClient.setClientHeader(ip);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("username", username);
        map.add("password", password);
        ResponseEntity<String> responseEntity = managementClient.sendRequest("developer/login", HttpMethod.POST, map);

        ObjectMapper objectMapper = new ObjectMapper();
        RemoteResult<Map<String, Object>> remoteResult = null;
        try {
            remoteResult = objectMapper.readValue(responseEntity.getBody(), RemoteResult.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Result<Map<String, Object>> result = new Result(remoteResult.getCode(), remoteResult.getMsg(), remoteResult.getData());

        //Save sessionId from  remote return
        if (result.getCode() == Status.SUCCESS.getCode()) {
            String sessionId = result.getData().get("session_id").toString();
            int userId = Integer.parseInt(result.getData().get("id").toString());
            sessionServiceI.createSession(userId, ip, sessionId);
        }
        return result;
    }

}
