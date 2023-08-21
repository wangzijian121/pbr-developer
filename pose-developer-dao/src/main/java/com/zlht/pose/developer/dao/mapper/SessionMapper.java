package com.zlht.pose.developer.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlht.pose.developer.dao.entity.Session;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;

public interface SessionMapper extends BaseMapper<Session> {

    @Select("select *\n" +
            "from  session s\n" +
            " where  s.id = #{sessionId}\n" +
            "  and #{now} < #{expireTime}")
    Session queryUserTokenById(@Param("sessionId") String sessionId,
                          @Param("expireTime") Date expireTime,
                          @Param("now") Date now);
}

