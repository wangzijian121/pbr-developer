package com.zlht.pbr.algorithm.developer.api.remote.controller;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zlht.pbr.algorithm.developer.api.base.BaseController;
import com.zlht.pbr.algorithm.developer.api.remote.service.ReviewServicesI;
import com.zlht.pbr.algorithm.developer.model.Review;
import com.zlht.pbr.algorithm.developer.utils.PageInfo;
import com.zlht.pbr.algorithm.developer.utils.Result;
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

@RestController
@Api(tags = "开发者审核管理", description = "开发者审核管理")
public class ReviewController extends BaseController {

    private static final Logger logger = LogManager.getLogger(ReviewController.class);
    @Autowired
    ReviewServicesI reviewServicesI;


    /**
     * 开发者-提交审核
     *
     * @return Result
     */
    @ApiOperation(value = "开发者-提交审核", notes = "开发者-提交审核")
    @PostMapping(value = "/developer/commitReview")
    @ResponseStatus(HttpStatus.OK)
    @JsonIgnoreProperties(value = "id")
    public Result<Review> createReview(@ApiIgnore @RequestAttribute(value = "session.userId") int userId,
                                       @RequestBody Review review) {

        return null;
    }

    /**
     * 开发者-查询审核信息
     *
     * @return review
     */
    @ApiOperation(value = "开发者-查询审核信息", notes = "开发者-查询审核信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "页数(默认1)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "pageSize", value = "页大小(默认10)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "name", value = "算法名", dataTypeClass = String.class)
    })
    @GetMapping(value = "/developer/getReview")
    @ResponseStatus(HttpStatus.OK)
    public Result<PageInfo> queryReviewList(@ApiIgnore @RequestAttribute(value = "session.userId") int userId,
                                            @RequestParam(required = false, defaultValue = "1") int currentPage,
                                            @RequestParam(required = false, defaultValue = "10") int pageSize,
                                            @RequestParam(required = false) String name) {

        Result result = checkPageParams(currentPage, pageSize);
        if (!result.checkResult()) {
            return result;
        }
        return null;
    }
}
