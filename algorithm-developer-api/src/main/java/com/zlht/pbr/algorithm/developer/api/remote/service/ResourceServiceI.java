package com.zlht.pbr.algorithm.developer.api.remote.service;

import com.zlht.pbr.algorithm.developer.utils.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author zi jian Wang
 */
public interface ResourceServiceI {
    /**
     * 上传
     *
     * @param userId
     * @param file
     * @param values
     * @return
     */
    Result createResource(int userId, MultipartFile file, MultiValueMap<String, String> values);


    /**
     * 下载文件
     *
     * @param userId
     * @param uuid
     * @param values
     * @return
     */
    ResponseEntity downloadResource(int userId, String uuid, MultiValueMap<String, String> values);

    /**
     * 删除文件
     *
     * @param userId
     * @param uuid
     * @param values
     * @return
     */
    Result deleteResource(int userId, String uuid, MultiValueMap<String, String> values);

}
