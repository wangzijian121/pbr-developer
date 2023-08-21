package com.zlht.pbr.algorithm.developer.api.remote.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zlht.pbr.algorithm.developer.api.base.impl.BaseServiceImpl;
import com.zlht.pbr.algorithm.developer.api.remote.service.ReviewServicesI;
import com.zlht.pbr.algorithm.developer.enums.Status;
import com.zlht.pbr.algorithm.developer.model.Review;
import com.zlht.pbr.algorithm.developer.remote.client.ManagementClient;
import com.zlht.pbr.algorithm.developer.remote.factory.ManagementClientFactory;
import com.zlht.pbr.algorithm.developer.utils.PageInfo;
import com.zlht.pbr.algorithm.developer.utils.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

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
        loadForManagementClient(managementClient, values);

        String requestBody = null;
        try {
            requestBody = objectMapper.writeValueAsString(review);
        } catch (JsonProcessingException e) {
            logger.error("Json parser Expection!");
            e.printStackTrace();
        }

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, managementClient.getHeaders());
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
    public Result<PageInfo> queryReviewList(int userId, int currentPage, int pageSize, String keyword) {
        return null;
    }

}
