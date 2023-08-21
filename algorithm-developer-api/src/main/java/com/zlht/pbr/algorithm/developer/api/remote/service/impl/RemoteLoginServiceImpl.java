package com.zlht.pbr.algorithm.developer.api.remote.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zlht.pbr.algorithm.developer.base.impl.BaseServiceImpl;
import com.zlht.pbr.algorithm.developer.api.developer.service.SessionServiceI;
import com.zlht.pbr.algorithm.developer.enums.Status;
import com.zlht.pbr.algorithm.developer.api.remote.service.RemoteLoginServiceI;
import com.zlht.pbr.algorithm.developer.remote.client.ManagementClient;
import com.zlht.pbr.algorithm.developer.remote.factory.ManagementClientFactory;
import com.zlht.pbr.algorithm.developer.remote.model.RemoteResult;
import com.zlht.pbr.algorithm.developer.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.util.Map;

@Service
public class RemoteLoginServiceImpl extends BaseServiceImpl implements RemoteLoginServiceI {


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
