package com.zlht.pbr.algorithm.developer.api.remote.controller;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.zlht.pbr.algorithm.developer.api.base.BaseController;
import com.zlht.pbr.algorithm.developer.api.remote.service.SportServicesI;
import com.zlht.pbr.algorithm.developer.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zi jian Wang
 */
@RestController
@Api(tags = "开发者体育管理")
public class SportController extends BaseController {


    @Autowired
    private SportServicesI sportServiceI;


    /**
     * 开发者查询体育
     *
     * @return
     */
    @ApiOperation(value = "获取已存在的体育类型", notes = "获取已存在的体育类型")
    @GetMapping(value = "/developer/getSport")
    @ResponseStatus(HttpStatus.OK)
    public Result getSport(@ApiIgnore @RequestAttribute(value = "session.userId") int userId,
                             HttpServletRequest request) {

        String ip = getClientIpAddress(request);
        if (StringUtils.isEmpty(ip)) {
            return error(10125, "Cant find ip！");
        }
        String sessionId = getSessionByRequest(request);
        MultiValueMap<String, String> values = new LinkedMultiValueMap<>();
        values.add("X-Real-IP", ip);
        values.add("sessionId", sessionId);
        return sportServiceI.querySportMap(userId, values);
    }


}
