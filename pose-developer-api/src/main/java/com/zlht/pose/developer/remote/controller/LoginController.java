package com.zlht.pose.developer.remote.controller;


import com.zlht.pose.developer.remote.service.ManagementRemoteServiceI;
import com.zlht.pose.developer.remote.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(tags = "开发者管理", description = "开发者管理")
public class LoginController extends BaseController {

    private static final Logger logger = LogManager.getLogger(LoginController.class);

    @Autowired
    private ManagementRemoteServiceI managementRemoteServiceI;

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
    @PostMapping(value = "/developer_login")
    @ResponseStatus(HttpStatus.OK)
    public Result developerLogin(@RequestParam String username,
                                 @RequestParam String password,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
        managementRemoteServiceI.checkConnect(username, password);
        managementRemoteServiceI.login(username, password);
        return null;
    }

}
