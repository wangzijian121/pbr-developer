package com.zlht.pose.developer.remote.interceptor;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlht.pose.developer.dao.entity.Session;
import com.zlht.pose.developer.dao.mapper.SessionMapper;
import com.zlht.pose.developer.dao.mapper.UserMapper;
import com.zlht.pose.developer.remote.controller.BaseController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class LoginHandlerInterceptor implements HandlerInterceptor {

    private static final Logger logger = LogManager.getLogger(LoginHandlerInterceptor.class);
    @Value("${session.timeout}")
    private int sessionTimeout;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SessionMapper sessionMapper;

    /**
     * 使用拦截器对登录进行校验
     *
     * @param request
     * @param response
     * @param handler
     * @return
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Session session = null;
        String sessionId = request.getHeader("sessionId");
        String ip = BaseController.getClientIpAddress(request);

        //sessionId in cookie
        if (StringUtils.isEmpty(sessionId)) {
            Cookie[] cookie = request.getCookies();
            if (cookie == null) {
                response.setStatus(401);
                return false;
            }
            for (Cookie c : cookie) {
                if (("sessionId").equals(c.getName()) && !StringUtils.isEmpty(c.getValue())) {
                    session = getSession(request, response, c.getValue(), ip);
                    break;
                }
                return false;
            }
        } else {
            // sessionId in header
            session = getSession(request, response, sessionId, ip);
        }
        if (session == null) {
            logger.error("session is null ");
            return false;
        }
        request.setAttribute("session.userId", session.getUserId());
        return true;
    }

    private Session getSession(HttpServletRequest request, HttpServletResponse response, String sessionId, String ip) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", sessionId);
        queryWrapper.eq("ip", ip);
        Session session = sessionMapper.selectOne(queryWrapper);
        if (session == null) {
            response.setStatus(401);
            logger.info("未找到session,请登录后重试！" + sessionId + ",ip:" + ip);
            request.setAttribute("session.userId", null);
            return null;
        } else {
            Date expireTime = new Date();
            expireTime.setTime(session.getLastLoginTime().getTime() + sessionTimeout);
            Session checkSession = sessionMapper.queryUserTokenById(session.getId(), expireTime, new Date());
            if (checkSession == null) {
                response.setStatus(401);
                logger.info("用户session已过期!");
                request.setAttribute("session.userId", null);
                return null;
            }
        }
        return session;
    }
}