package com.zlht.pbr.algorithm.developer.api.remote.controller;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.zlht.pbr.algorithm.developer.api.base.BaseController;
import com.zlht.pbr.algorithm.developer.api.remote.service.RemoteLoginServiceI;
import com.zlht.pbr.algorithm.developer.enums.Status;
import com.zlht.pbr.algorithm.developer.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author zi jian Wang
 */
@RestController
@Api(tags = "开发者管理")
public class LoginController extends BaseController {

    private static final Logger logger = LogManager.getLogger(LoginController.class);

    @Autowired
    private RemoteLoginServiceI remoteLoginServiceI;

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    @ApiOperation(value = "开发者登录", notes = "开发者登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataTypeClass = String.class)
    })
    @PostMapping(value = "/developer/login")
    @ResponseStatus(HttpStatus.OK)
    public Result developerLogin(@RequestParam String username,
                                 @RequestParam String password,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
        // user ip check
        String ip = getClientIpAddress(request);
        if (StringUtils.isEmpty(ip)) {
            return error(10125, "Cant find ip！");
        }
        if (!remoteLoginServiceI.checkConnect()) {
            logger.error("Remote ip or port is disconnect!");
            return null;
        }
        Result<Map<String, Object>> result = remoteLoginServiceI.login(username, password, ip);
        if (result.getCode() == Status.SUCCESS.getCode()) {
            Cookie cookie = new Cookie("sessionId", result.getData().get("sessionId").toString());
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
        }
        return result;
    }


    /**
     * 注销
     *
     * @return
     */
    @ApiOperation(value = "开发者登出", notes = "开发者登出")
    @PostMapping(value = "/developer/developerLogout")
    @ResponseStatus(HttpStatus.OK)
    public Result developerLogout(@ApiIgnore @RequestAttribute(value = "session.userId") int userId, HttpServletRequest request) {
        // user ip check
        String ip = getClientIpAddress(request);
        if (StringUtils.isEmpty(ip)) {
            return error(10125, "Cant find ip！");
        }
        if (!remoteLoginServiceI.checkConnect()) {
            logger.error("Remote ip or port is disconnect!");
            return null;
        }
        String sessionId = getSessionByRequest(request);
        MultiValueMap<String, String> values = new LinkedMultiValueMap<>();
        values.add("X-Real-IP", ip);
        values.add("sessionId", sessionId);
        Result<Map<String, Object>> result = remoteLoginServiceI.logout(ip, userId, values);

        return result;
    }

}
