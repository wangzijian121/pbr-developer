package com.zlht.pbr.algorithm.developer.api.remote.service;

import com.zlht.pbr.algorithm.developer.remote.model.Earining;
import com.zlht.pbr.algorithm.developer.utils.Result;
import org.springframework.util.MultiValueMap;

/**
 * @author zi jian Wang
 */
public interface EarningServiceI {

    /**
     * 获取收益
     * @param userId
     * @param currentPage
     * @param pageSize
     * @param name
     * @param values
     * @return
     */
    Result<Earining> getEarning(int userId, int currentPage, int pageSize, String name, MultiValueMap<String, String> values);
}
