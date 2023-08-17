package com.zlht.pose.developer.remote.service;

import com.zlht.pose.developer.remote.management.model.Result;

public interface ManagementRemoteServiceI {

    /**
     * 检查连接状态
     */
    Result checkConnect(String username, String password);

    /**
     * 登录
     */
    Result login(String username, String password);


    /**
     *获取审核状态
     */

    /**
     *获取收益信息
     */

}
