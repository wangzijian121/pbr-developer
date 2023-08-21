package com.zlht.pbr.algorithm.developer.api.base;


import com.zlht.pbr.algorithm.developer.dao.entity.Session;
import com.zlht.pbr.algorithm.developer.remote.client.ManagementClient;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface BaseServiceI<T> {

    void putMsg(Map<String, Object> result, int code, String msg);

    /**
     * 检查权限
     *
     * @param userId
     * @return
     */
    boolean canOperator(int userId);


    /**
     * 装配header（将本地的sessionId 装配到Client）
     *
     * @return
     */
    HttpHeaders loadForManagementClient(ManagementClient managementClient, MultiValueMap<String, String> values);
}
