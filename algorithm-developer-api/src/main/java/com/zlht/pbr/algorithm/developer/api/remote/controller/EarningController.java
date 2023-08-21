package com.zlht.pbr.algorithm.developer.api.remote.controller;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.zlht.pbr.algorithm.developer.api.remote.service.EarningServiceI;
import com.zlht.pbr.algorithm.developer.api.base.BaseController;
import com.zlht.pbr.algorithm.developer.dao.entity.User;
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
import java.util.Arrays;

@RestController
@Api(tags = "开发者收益管理", description = "开发者收益管理")
public class EarningController extends BaseController {

    private static final Logger logger = LogManager.getLogger(EarningController.class);

    @Autowired
    private EarningServiceI earningServiceI;


    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    @ApiOperation(value = "开发者收益管理", notes = "开发者收益管理")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "页数(默认1)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "pageSize", value = "页大小(默认10)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "name", value = "佣金项名", dataTypeClass = String.class)
    })
    @GetMapping(value = "/developer/getEarning")
    @ResponseStatus(HttpStatus.OK)
    public Result developerLogin(@ApiIgnore @RequestAttribute(value = "session.userId") int userId,
                                 @RequestParam(required = false, defaultValue = "1") int currentPage,
                                 @RequestParam(required = false, defaultValue = "10") int pageSize,
                                 @RequestParam(required = false) String name,
                                 HttpServletRequest request) {

        String ip = getClientIpAddress(request);
        System.out.println(ip);
        if (StringUtils.isEmpty(ip)) {
            return error(10125, "Cant find ip！");
        }

        String  sessionId=request.getHeader("sessionId");
        if(sessionId==null){
            sessionId = Arrays.stream(request.getCookies())
                    .filter(cookie -> cookie.getName().equals("sessionId"))
                    .map(Cookie::getValue)
                    .findFirst().get();
        }
        MultiValueMap<String, String> values =new LinkedMultiValueMap<>();
        values.add("X-Real-IP",ip);
        values.add("sessionId",sessionId);
        return earningServiceI.getEarning(userId,currentPage,pageSize,name,values);
    }


}
