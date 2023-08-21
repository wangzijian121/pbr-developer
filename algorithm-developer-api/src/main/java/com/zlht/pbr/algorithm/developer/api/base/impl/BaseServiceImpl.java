package com.zlht.pbr.algorithm.developer.api.base.impl;


import com.zlht.pbr.algorithm.developer.enums.Status;
import com.zlht.pbr.algorithm.developer.api.base.BaseServiceI;
import com.zlht.pbr.algorithm.developer.remote.client.ManagementClient;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BaseServiceImpl<T> implements BaseServiceI<T> {

    public void putMsg(Map<String, Object> result, int code, String msg) {
        result.put("code", code);
        result.put("msg", msg);
        if (code == Status.SUCCESS.getCode()) {
            result.put("status", Status.SUCCESS);
        }
    }

    @Override
    public boolean canOperator(int userId) {
        return userId > 0;
    }

    @Override
    public HttpHeaders loadForManagementClient(ManagementClient managementClient, MultiValueMap<String, String> values) {
//        managementClient.getHeaders().addAll(values);
        for (Map.Entry<String, List<String>> entry : values.entrySet()) {
            String key = entry.getKey();
            List<String> valueList = entry.getValue();
            for (String value : valueList) {
                managementClient.getHeaders().set(key, value);
            }
        }
        return managementClient.getHeaders();
    }
}
