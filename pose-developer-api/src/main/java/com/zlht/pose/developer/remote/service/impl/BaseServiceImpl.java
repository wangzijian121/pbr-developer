package com.zlht.pose.developer.remote.service.impl;


import com.zlht.pose.developer.remote.enums.Status;
import com.zlht.pose.developer.remote.service.BaseServiceI;

import java.util.Map;

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

}
