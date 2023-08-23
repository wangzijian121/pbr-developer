/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zlht.pbr.algorithm.developer.api.developer.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlht.pbr.algorithm.developer.api.developer.service.SessionServiceI;
import com.zlht.pbr.algorithm.developer.api.base.impl.BaseServiceImpl;
import com.zlht.pbr.algorithm.developer.dao.entity.Session;
import com.zlht.pbr.algorithm.developer.dao.entity.User;
import com.zlht.pbr.algorithm.developer.dao.mapper.SessionMapper;
import com.zlht.pbr.algorithm.developer.enums.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * session service implement
 */
@Service
public class SessionServiceImpl extends BaseServiceImpl implements SessionServiceI {

    private static final Logger logger = LoggerFactory.getLogger(SessionServiceImpl.class);

    @Autowired
    private SessionMapper sessionMapper;

    /**
     * get user session from request
     *
     * @param userId userId
     * @return session
     */
    @Override
    public Session getSessionByUserId(int userId) {
//        String sessionId = request.getHeader("sessionId");

//        return sessionMapper.selectById(sessionId);
        return null;
    }

    /**
     * create session
     *
     * @param user user
     * @param ip   ip
     * @return session string
     */
    @Override
    @Transactional
    public String createSession(int userId, String ip, String sessionId) {
        Session session = null;
        // login
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("ip", ip);
        int delete = sessionMapper.delete(queryWrapper);
        if (delete >= 0) {
            Date now = new Date();
            // assign new session
            session = new Session();
            session.setId(sessionId);
            session.setIp(ip);
            session.setUserId(userId);
            session.setLastLoginTime(now);
            sessionMapper.insert(session);
            return session.getId();
        } else {
            return null;
        }
    }

    /**
     * sign out
     * remove ip restrictions
     *
     * @param ip        no use
     * @param userId login user
     */
    @Override
    public Map<String, Object> signOut(String ip, int userId) {
        Map<String, Object> map = new HashMap<>();

        try {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("user_id",userId);
            queryWrapper.eq("ip", ip);
            Session session = sessionMapper.selectOne(queryWrapper);
            //delete session
            int delete = sessionMapper.deleteById(session.getId());
            if (delete > 0) {
                putMsg(map, Status.SUCCESS.getCode(), Status.SUCCESS.getMsg());
                return map;
            }
        } catch (Exception e) {
            logger.warn("userId : {} , ip : {} , find more one session", userId, ip);
        }
        return map;
    }
}
