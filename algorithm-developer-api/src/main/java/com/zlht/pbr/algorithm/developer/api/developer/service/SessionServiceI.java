package com.zlht.pbr.algorithm.developer.api.developer.service;


import com.zlht.pbr.algorithm.developer.base.BaseServiceI;
import com.zlht.pbr.algorithm.developer.dao.entity.Session;
import com.zlht.pbr.algorithm.developer.dao.entity.User;

import java.util.Map;

public interface SessionServiceI extends BaseServiceI {

    /**
     * get user session from request
     *
     * @param userId userId
     * @return session
     */
    Session getSessionByUserId(int  userId);

    /**
     * create session
     *
     * @param userId int
     * @param ip     ip
     * @return session string
     */
    String createSession(int userId, String ip, String sessionId);


    /**
     * sign out
     * remove ip restrictions
     *
     * @param ip        no use
     * @param loginUser login user
     */
    Map<String, Object> signOut(String ip, User loginUser);
}
