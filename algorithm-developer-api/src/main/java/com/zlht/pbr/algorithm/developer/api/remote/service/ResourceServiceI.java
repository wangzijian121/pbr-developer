package com.zlht.pbr.algorithm.developer.api.remote.service;

import com.zlht.pbr.algorithm.developer.dao.entity.User;
import com.zlht.pbr.algorithm.developer.utils.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface ResourceServiceI {
    /**
     * 上传
     */
    Result createResource(int userId, MultipartFile file, MultiValueMap<String, String> values);


    /**
     * 下载文件
     */
    ResponseEntity downloadResource(int userId, String uuid,MultiValueMap<String, String> values);
    /**
     * 删除文件
     */
    Result deleteResource(int userId, String uuid,MultiValueMap<String, String> values);

}
