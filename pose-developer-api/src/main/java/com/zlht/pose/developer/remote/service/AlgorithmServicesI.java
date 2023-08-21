package com.zlht.pose.developer.remote.service;


import com.zlht.pose.developer.dao.entity.Algorithm;
import com.zlht.pose.developer.remote.utils.PageInfo;
import com.zlht.pose.developer.remote.utils.Result;

import java.util.Map;

public interface AlgorithmServicesI {

    /**
     * 查询算法
     *
     * @param type
     * @param currentPage
     * @param pageSize
     * @return
     */
    Result<PageInfo<Algorithm>> queryAlgorithmList(int  userId, int type, int currentPage, int pageSize, String algorithmName);

    /**
     * 获取算法映射
     *
     * @param userId
     * @return
     */
    Result queryAlgorithmMap(int userId);

    /**
     * 创建算法
     *
     * @param algorithm
     * @return
     */

    Map<String, Object> createAlgorithm(int userId, Algorithm algorithm);

    /**
     * 更新算法
     *
     * @param id
     * @param algorithm
     * @return
     */
    Map<String, Object> updateAlgorithm(int userId, int id, Algorithm algorithm);

    /**
     * 删除算法
     *
     * @param id
     * @return
     */
    Map<String, Object> deleteAlgorithm(int userId, int id);

    /**
     * 通过ID判断是否存在（删除更新判断）
     *
     * @param id
     * @return
     */
    boolean checkAlgorithmExistById(int id);
}
