package com.zlht.pbr.algorithm.developer.api.remote.service;


import com.zlht.pbr.algorithm.developer.utils.Result;
import org.springframework.util.MultiValueMap;

/**
 * @author zi jian Wang
 */
public interface SportServicesI {

    /**
     * 查询已添加的体育
     *
     * @param userId
     * @return
     */
    Result querySportMap(int userId, MultiValueMap<String, String> values);

}
