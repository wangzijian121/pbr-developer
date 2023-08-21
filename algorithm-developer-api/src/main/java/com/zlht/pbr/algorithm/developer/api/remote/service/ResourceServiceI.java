package com.zlht.pbr.algorithm.developer.api.remote.service;

import com.zlht.pbr.algorithm.developer.dao.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface ResourceServiceI {
    /**
     * 上传
     */
    Map<String, Object> createResource(int userId, MultipartFile file);


    /**
     * 下载文件
     */
    ResponseEntity downloadResource(int userId, String uuid);


}
