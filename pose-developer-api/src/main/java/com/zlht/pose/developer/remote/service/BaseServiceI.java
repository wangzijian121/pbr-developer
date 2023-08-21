package com.zlht.pose.developer.remote.service;


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
}
