package com.zlht.pose.developer.remote.service;


import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface ResourceServiceI {
    /**
     * 上传
     */
    Map<String, Object> createResource(int  userId, MultipartFile file);

    /**
     * 删除文件
     */
    Map<String, Object> deleteResource(int  userId, String uuid);


    /**
     * 下载文件
     */
    ResponseEntity downloadResource(int  userId, String uuid);

    /**
     * 校验资源类型
     */
    boolean checkFileType(String type);

    /**
     * 校验资源大小
     */
    boolean checkFileSize(long size);

    /**
     * 判断资源元数据 是否存在
     */
    boolean resourceExist(String fullName);

}
