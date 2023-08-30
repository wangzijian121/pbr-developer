package com.zlht.pbr.algorithm.developer.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlht.pbr.algorithm.developer.dao.entity.Session;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;

/**
 * @author zi jian Wang
 */
public interface SessionMapper extends BaseMapper<Session> {

    /**
     * 查询用户token
     * @param sessionId
     * @param expireTime
     * @param now
     * @return
     */
    @Select("select *\n" +
            "from  session s\n" +
            " where  s.id = #{sessionId}\n" +
            "  and #{now} < #{expireTime}")
    Session queryUserTokenById(@Param("sessionId") String sessionId,
                               @Param("expireTime") Date expireTime,
                               @Param("now") Date now);
}

