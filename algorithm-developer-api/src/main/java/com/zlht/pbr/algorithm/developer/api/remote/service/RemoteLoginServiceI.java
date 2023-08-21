package com.zlht.pbr.algorithm.developer.api.remote.service;

import com.zlht.pbr.algorithm.developer.api.base.BaseServiceI;
import com.zlht.pbr.algorithm.developer.utils.Result;

import java.util.Map;

public interface RemoteLoginServiceI extends BaseServiceI {

    /**
     * 检查连接状态
     */
    boolean checkConnect();

    /**
     * 登录
     */
    Result<Map<String,Object>> login(String username, String password, String ip);


    /**
     *获取审核状态
     */


    /**
     *获取收益信息
     */

}
