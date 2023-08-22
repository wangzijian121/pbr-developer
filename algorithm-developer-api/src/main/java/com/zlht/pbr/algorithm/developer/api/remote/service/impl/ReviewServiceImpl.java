package com.zlht.pbr.algorithm.developer.api.remote.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zlht.pbr.algorithm.developer.api.base.impl.BaseServiceImpl;
import com.zlht.pbr.algorithm.developer.api.remote.service.ReviewServicesI;
import com.zlht.pbr.algorithm.developer.enums.Status;
import com.zlht.pbr.algorithm.developer.model.Review;
import com.zlht.pbr.algorithm.developer.remote.client.ManagementClient;
import com.zlht.pbr.algorithm.developer.remote.factory.ManagementClientFactory;
import com.zlht.pbr.algorithm.developer.utils.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Service
public class ReviewServiceImpl extends BaseServiceImpl<Review> implements ReviewServicesI {

    private static final Logger logger = LogManager.getLogger(ReviewServiceImpl.class);

    @Autowired
    private ManagementClientFactory managementClientFactory;

    @Override
    public Result commitReview(int userId, Review review, MultiValueMap<String, String> values) {

        Result result = null;
        if (!canOperator(userId)) {
            result.setMsg(Status.USER_NO_OPERATION_PERM.getMsg());
            result.setCode(Status.USER_NO_OPERATION_PERM.getCode());
            return result;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        ManagementClient managementClient = managementClientFactory.getManagementClient();
        values.add("Content-Type", "application/json");
        loadForManagementClient(managementClient, values);

        if (!managementClient.checkConnect()) {
            logger.error("management disconnect!");
            result.setCode(400);
            result.setMsg("management disconnect!");
            result.setData(null);
            return result;
        }

        String reviewStr;
        try {
            reviewStr = objectMapper.writeValueAsString(review);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        HttpEntity<String> requestEntity = new HttpEntity<>(reviewStr, managementClient.getHeaders());
        ResponseEntity<String> responseEntity = managementClient.sendRequest("developer/commitReview", HttpMethod.POST, requestEntity);

        try {
            result = objectMapper.readValue(responseEntity.getBody(), Result.class);
        } catch (IOException e) {
            logger.error("response 解析失败！");
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public Result queryReviewList(int userId, int currentPage, int pageSize, String keyword, String  type,MultiValueMap<String, String> values) {

        //get请求
        Result result = null;
        if (!canOperator(userId)) {
            result.setMsg(Status.USER_NO_OPERATION_PERM.getMsg());
            result.setCode(Status.USER_NO_OPERATION_PERM.getCode());
            return result;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        ManagementClient managementClient = managementClientFactory.getManagementClient();
        loadForManagementClient(managementClient, values);

        if (!managementClient.checkConnect()) {
            logger.error("management disconnect!");
            result.setCode(400);
            result.setMsg("management disconnect!");
            result.setData(null);
            return result;
        }
        String suffix = UriComponentsBuilder.fromPath("developer/getReview")
                .queryParam("currentPage", currentPage)
                .queryParam("pageSize", pageSize)
                .queryParam("name", keyword)
                .queryParam("type", type)
                .build()
                .toUriString();
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(null, managementClient.getHeaders());
        ResponseEntity<String> response = managementClient.sendRequest(suffix, HttpMethod.GET, requestEntity);
        try {
            result = objectMapper.readValue(response.getBody(), Result.class);
        } catch (IOException e) {
            logger.error("response 解析失败！");
            throw new RuntimeException(e);
        }
        return result;
    }

}
