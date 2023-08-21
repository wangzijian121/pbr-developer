package com.zlht.pbr.algorithm.developer.api.remote.service;

import com.zlht.pbr.algorithm.developer.api.base.BaseServiceI;
import com.zlht.pbr.algorithm.developer.utils.Result;
import com.zlht.pbr.algorithm.developer.remote.model.Earining;
import org.springframework.util.MultiValueMap;

import javax.servlet.http.HttpServletRequest;

public interface EarningServiceI {

    Result<Earining> getEarning(int userId, int currentPage, int pageSize, String name, MultiValueMap<String, String> values);
}
