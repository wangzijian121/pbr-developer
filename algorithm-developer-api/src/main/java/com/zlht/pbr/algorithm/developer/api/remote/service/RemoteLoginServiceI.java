package com.zlht.pbr.algorithm.developer.api.remote.service;

import com.zlht.pbr.algorithm.developer.api.base.BaseServiceI;
import com.zlht.pbr.algorithm.developer.utils.Result;
import org.springframework.util.MultiValueMap;

import java.util.Map;

/**
 * @author zi jian Wang
 */
public interface RemoteLoginServiceI extends BaseServiceI {

    /**
     * 检查连接状态
     *
     * @return
     */
    boolean checkConnect();

    /**
     * 登录
     *
     * @param username
     * @param password
     * @param ip
     * @return
     */
    Result<Map<String, Object>> login(String username, String password, String ip);

    /**
     * 注销
     *
     * @param ip
     * @param useId
     * @param values
     * @return
     */
    Result<Map<String, Object>> logout(String ip, int useId, MultiValueMap<String, String> values);


}
