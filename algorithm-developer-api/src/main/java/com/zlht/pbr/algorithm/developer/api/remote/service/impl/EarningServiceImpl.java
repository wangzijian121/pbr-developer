package com.zlht.pbr.algorithm.developer.api.remote.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zlht.pbr.algorithm.developer.api.base.impl.BaseServiceImpl;
import com.zlht.pbr.algorithm.developer.api.remote.controller.LoginController;
import com.zlht.pbr.algorithm.developer.api.remote.service.EarningServiceI;
import com.zlht.pbr.algorithm.developer.enums.Status;
import com.zlht.pbr.algorithm.developer.remote.client.ManagementClient;
import com.zlht.pbr.algorithm.developer.remote.factory.ManagementClientFactory;
import com.zlht.pbr.algorithm.developer.remote.model.Earining;
import com.zlht.pbr.algorithm.developer.utils.PageInfo;
import com.zlht.pbr.algorithm.developer.utils.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

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
            logger.error("management disconnect!");
            result.setCode(400);
            result.setMsg("management disconnect!");
            result.setData(null);
            return result;
        }

        String suffix = UriComponentsBuilder.fromPath("developer/getCommission")
                .queryParam("currentPage", currentPage)
                .queryParam("pageSize", pageSize)
                .queryParam("name", name)
                .build()
                .toUriString();
        ResponseEntity<String> response = managementClient.sendRequest(suffix, HttpMethod.GET, null);
        try {
            result = objectMapper.readValue(response.getBody(), Result.class);
        } catch (IOException e) {
            logger.error("response 解析失败！");
            throw new RuntimeException(e);
        }
        return result;
    }
}