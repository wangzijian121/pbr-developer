package com.zlht.pbr.algorithm.developer.api.remote.controller;


import com.zlht.pbr.algorithm.developer.api.base.BaseController;
import com.zlht.pbr.algorithm.developer.api.remote.service.ResourceServiceI;
import com.zlht.pbr.algorithm.developer.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@Api(tags = "开发者-文件管理", description = "开发者-文件管理")
public class FileController extends BaseController {

    private static final Logger logger = LogManager.getLogger(FileController.class);
    @Autowired
    ResourceServiceI resourceServiceI;

    @ApiOperation(value = "上传资源", notes = "上传资源")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "文件", paramType = "form", value = "文件", dataType = "file", required = true)
    })
    @PostMapping(value = "/developer/upload", consumes = "multipart/*", headers = "content-type=multipart/form-data")
    public Result upload(@ApiIgnore @RequestAttribute(value = "session.userId") int userId,
                         @RequestPart("file") MultipartFile file, HttpServletRequest request) {
        MultiValueMap<String, String> values = new LinkedMultiValueMap<>();
        values.add("X-Real-IP", getClientIpAddress(request));
        values.add("sessionId", getSessionByRequest(request));
        return resourceServiceI.createResource(userId, file, values);
    }

    @ApiOperation(value = "开发者-下载文件")
    @ApiImplicitParam(name = "uuid", value = "资源的uuid", paramType = "query", required = true, dataType = "String")
    @GetMapping("/developer/download")
    public ResponseEntity download(@ApiIgnore @RequestAttribute(value = "session.userId") int userId,
                                   @RequestParam String uuid, HttpServletRequest request) {
        MultiValueMap<String, String> values = new LinkedMultiValueMap<>();
        values.add("X-Real-IP", getClientIpAddress(request));
        values.add("sessionId", getSessionByRequest(request));
        return resourceServiceI.downloadResource(userId, uuid, values);
    }

    @ApiOperation(value = "开发者-删除文件")
    @ApiImplicitParam(name = "uuid", value = "资源的uuid", paramType = "query", required = true, dataType = "String")
    @DeleteMapping("/developer/delete")
    public Result deleteFile(@ApiIgnore @RequestAttribute(value = "session.userId") int userId,
                             @RequestParam String uuid, HttpServletRequest request) {
        MultiValueMap<String, String> values = new LinkedMultiValueMap<>();
        values.add("X-Real-IP", getClientIpAddress(request));
        values.add("sessionId", getSessionByRequest(request));
        return resourceServiceI.deleteResource(userId, uuid, values);
    }
}


