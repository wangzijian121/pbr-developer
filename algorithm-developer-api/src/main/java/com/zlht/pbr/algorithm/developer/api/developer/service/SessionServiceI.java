package com.zlht.pbr.algorithm.developer.api.developer.service;


import com.zlht.pbr.algorithm.developer.api.base.BaseServiceI;

import java.util.Map;

/**
 * @author zi jian Wang
 */
public interface SessionServiceI extends BaseServiceI {

    /**
     * create session
     *
     * @param userId
     * @param ip
     * @param sessionId
     * @return
     */
    String createSession(int userId, String ip, String sessionId);


    /**
     * sign out
     *
     * @param ip
     * @param userId
     * @return
     */
    Map<String, Object> signOut(String ip, int userId);
}
