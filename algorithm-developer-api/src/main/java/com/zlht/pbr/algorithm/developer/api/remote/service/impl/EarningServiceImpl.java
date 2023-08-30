package com.zlht.pbr.algorithm.developer.api.remote.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zlht.pbr.algorithm.developer.api.base.impl.BaseServiceImpl;
import com.zlht.pbr.algorithm.developer.api.remote.service.EarningServiceI;
import com.zlht.pbr.algorithm.developer.enums.Status;
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

/**
 * @author zi jian Wang
 */
@Service
public class EarningServiceImpl extends BaseServiceImpl implements EarningServiceI {

    private static final Logger logger = LogManager.getLogger(EarningServiceImpl.class);

    @Autowired
    private ManagementClientFactory managementClientFactory;

    @Override
    public Result getEarning(int userId, int currentPage, int pageSize, String name, MultiValueMap<String, String> values) {


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
            String errMsg = "管理端无法连接！";
            logger.error("updateAlgorithm() method .message={}, ", errMsg);
            logger.error("");
            result.setCode(400);
            result.setMsg(errMsg);
            result.setData(null);
            return result;
        }

        String suffix = UriComponentsBuilder.fromPath("developer/getCommission")
                .queryParam("currentPage", currentPage)
                .queryParam("pageSize", pageSize)
                .queryParam("name", name)
                .build()
                .toUriString();
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(null, managementClient.getHeaders());
        ResponseEntity<String> response = managementClient.sendRequest(suffix, HttpMethod.GET, requestEntity);
        try {
            result = objectMapper.readValue(response.getBody(), Result.class);
        } catch (IOException e) {
            String errMsg = "response 解析失败！";
            logger.error("updateAlgorithm() method .message={}", errMsg, e);
            throw new RuntimeException(e);
        }
        return result;
    }
}
