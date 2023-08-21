package com.zlht.pbr.algorithm.developer.api.remote.service.impl;

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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;

@Service
public class ReviewServiceImpl extends BaseServiceImpl<Review> implements ReviewServicesI {

    private static final Logger logger = LogManager.getLogger(ReviewServiceImpl.class);

    @Autowired
    private ManagementClientFactory managementClientFactory;

    @Override
    public Map<String, Object> commitReview(int userId, Review review, MultiValueMap<String, String> values) {

        //auth
        Map<String, Object> map = new HashMap<>();
        if (!canOperator(userId)) {
            putMsg(map, Status.USER_NO_OPERATION_PERM.getCode(), Status.USER_NO_OPERATION_PERM.getMsg());
            return map;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        ManagementClient managementClient = managementClientFactory.getManagementClient();
        loadForManagementClient(managementClient,values);

        MultiValueMap<String, Object> multiValueMap= new LinkedMultiValueMap<>();

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(multiValueMap, managementClient.getHeaders());
        ResponseEntity<String> responseEntity = managementClient.sendRequest("developer/commitReview", HttpMethod.POST, requestEntity);

        return null;
    }

    @Override
    public Result<PageInfo> queryReviewList(int userId, int currentPage, int pageSize, String keyword) {
        return null;
    }

}
