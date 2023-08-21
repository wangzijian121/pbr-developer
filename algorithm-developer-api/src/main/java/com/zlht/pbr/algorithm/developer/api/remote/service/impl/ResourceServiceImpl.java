package com.zlht.pbr.algorithm.developer.api.remote.service.impl;

import com.zlht.pbr.algorithm.developer.api.base.impl.BaseServiceImpl;
import com.zlht.pbr.algorithm.developer.api.remote.service.ResourceServiceI;
import com.zlht.pbr.algorithm.developer.dao.mapper.ResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public class ResourceServiceImpl extends BaseServiceImpl implements ResourceServiceI {


    private final String[] ACCEPT_TYPES = new String[]{"zip"};
    /**
     * 文件磁盘路径
     */
    @Value("${files.upload.local.path}")
    private String fileUploadPath;

    @Value("${files.upload.local.maxFileSize}")
    private long maxFileSize;

    @Autowired
    ResourceMapper resourceMapper;

    @Override
    public Map<String, Object> createResource(int userId, MultipartFile file) {
        return null;
    }


    @Override
    public ResponseEntity downloadResource(int userId, String uuid) {
        return null;
    }
}