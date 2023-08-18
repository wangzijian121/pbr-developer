package com.zlht.pose.developer.remote.service;

import com.zlht.pose.developer.remote.utils.Result;

import java.util.Map;

public interface ManagementRemoteServiceI {

    /**
     * 检查连接状态
     */
    boolean checkConnect();

    /**
     * 登录
     */
    Result<Map<String,Object>> login(String username, String password,String ip);


    /**
     *获取审核状态
     */

    /**
     *获取收益信息
     */

}
