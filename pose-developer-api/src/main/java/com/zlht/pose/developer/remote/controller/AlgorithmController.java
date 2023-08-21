package com.zlht.pose.developer.remote.controller;


import com.zlht.pose.developer.dao.entity.Algorithm;
import com.zlht.pose.developer.remote.service.AlgorithmServicesI;
import com.zlht.pose.developer.remote.utils.PageInfo;
import com.zlht.pose.developer.remote.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

@RestController
@Api(tags = "开发者-算法管理", description = "开发者-算法管理")
public class AlgorithmController extends BaseController {

    private static final Logger logger = LogManager.getLogger(AlgorithmController.class);
    @Autowired
    AlgorithmServicesI algorithmServices;


    /**
     * 查询算法信息
     *
     * @return algorithm
     */
    @ApiOperation(value = "开发者-查询算法", notes = "开发者-查询算法")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "算法类型(0普通算法 1专用算法)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "currentPage", value = "页数(默认1)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "pageSize", value = "页大小(默认10)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "keyword", value = "算法名", dataTypeClass = String.class)
    })
    @GetMapping(value = "/getAlgorithm")
    @ResponseStatus(HttpStatus.OK)
    public Result<PageInfo<Algorithm>> queryAlgorithmList(@ApiIgnore @RequestAttribute(value = "session.userId") int userId,
                                                          @RequestParam(required = false, defaultValue = "-1") int type,
                                                          @RequestParam(required = false, defaultValue = "1") int currentPage,
                                                          @RequestParam(required = false, defaultValue = "10") int pageSize,
                                                          @RequestParam(required = false) String keyword) {

        Result result = checkPageParams(currentPage, pageSize);
        if (!result.checkResult()) {
            return result;
        }
        return algorithmServices.queryAlgorithmList(userId, type, currentPage, pageSize, keyword);
    }

    /**
     * 创建算法
     *
     * @return Algorithm
     */
    @ApiOperation(value = "开发者-创建算法", notes = "开发者-创建算法")
    @PostMapping(value = "/createAlgorithm")
    @ResponseStatus(HttpStatus.OK)
    public Result<Algorithm> createAlgorithm(@ApiIgnore @RequestAttribute(value = "session.userId") int userId,
                                             @RequestBody Algorithm algorithm) {
        Map<String, Object> map = algorithmServices.createAlgorithm(userId, algorithm);
        return returnDataList(map);
    }

    /**
     * 更新算法
     *
     * @return Algorithm
     */
    @ApiOperation(value = "开发者-更新算法", notes = "开发者-更新算法")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需要更新的算法的ID", required = true, dataTypeClass = int.class)
    })
    @PutMapping(value = "/updateAlgorithm")
    @ResponseStatus(HttpStatus.OK)
    public Result<Algorithm> updateAlgorithm(@ApiIgnore @RequestAttribute(value = "session.userId") int userId,
                                             @RequestParam int id,
                                             @RequestBody Algorithm algorithm) {
        Map<String, Object> map = algorithmServices.updateAlgorithm(userId, id, algorithm);
        return returnDataList(map);
    }

    /**
     * 删除算法
     *
     * @return Algorithm
     */
    @ApiOperation(value = "开发者-删除算法", notes = "开发者-删除算法")
    @DeleteMapping(value = "/deleteAlgorithm")
    @ResponseStatus(HttpStatus.OK)
    public Result<Algorithm> deleteAlgorithm(@ApiIgnore @RequestAttribute(value = "session.userId") int userId,
                                             @RequestParam int id) {
        Map<String, Object> map = algorithmServices.deleteAlgorithm(userId, id);
        return returnDataList(map);
    }
}
