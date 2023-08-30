package com.zlht.pbr.algorithm.developer.api.base;


import com.zlht.pbr.algorithm.developer.remote.client.ManagementClient;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;

import java.util.Map;

/**
 * @author zi jian Wang
 */
public interface BaseServiceI<T> {

    /**
     * 发送消息
     * @param result
     * @param code
     * @param msg
     */
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
     * @param managementClient
     * @param values
     * @return
     */
    HttpHeaders loadForManagementClient(ManagementClient managementClient, MultiValueMap<String, String> values);
}
