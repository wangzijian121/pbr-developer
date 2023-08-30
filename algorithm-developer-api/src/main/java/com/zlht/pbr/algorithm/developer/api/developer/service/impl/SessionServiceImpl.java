package com.zlht.pbr.algorithm.developer.api.developer.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlht.pbr.algorithm.developer.api.base.impl.BaseServiceImpl;
import com.zlht.pbr.algorithm.developer.api.developer.service.SessionServiceI;
import com.zlht.pbr.algorithm.developer.dao.entity.Session;
import com.zlht.pbr.algorithm.developer.dao.mapper.SessionMapper;
import com.zlht.pbr.algorithm.developer.enums.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zi jian Wang
 */
@Service
public class SessionServiceImpl extends BaseServiceImpl implements SessionServiceI {

    private static final Logger logger = LoggerFactory.getLogger(SessionServiceImpl.class);

    @Autowired
    private SessionMapper sessionMapper;


    /**
     * create session
     *
     * @param userId userId
     * @param ip     ip
     * @return session string
     */
    @Override
    public String createSession(int userId, String ip, String sessionId) {
        Session session = null;
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("ip", ip);
        try {
            sessionMapper.delete(queryWrapper);
            Date now = new Date();
            session = new Session();
            session.setId(sessionId);
            session.setIp(ip);
            session.setUserId(userId);
            session.setLastLoginTime(now);
            sessionMapper.insert(session);
        } catch (Exception e) {
            String errMsg = "创建Session失败";
            logger.error("updateAlgorithm() method .message={}, sessionId={}", errMsg, sessionId, e);
        }
        return session.getId();
    }

    /**
     * sign out
     * remove ip restrictions
     *
     * @param ip     no use
     * @param userId login user
     */
    @Override
    public Map<String, Object> signOut(String ip, int userId) {
        Map<String, Object> map = new HashMap<>(3);

        try {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("user_id", userId);
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
