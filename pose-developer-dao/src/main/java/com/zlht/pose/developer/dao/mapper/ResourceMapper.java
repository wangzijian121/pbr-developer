package com.zlht.pose.developer.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlht.pose.developer.dao.entity.Resource;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ResourceMapper extends BaseMapper<Resource> {

    @Select("select *  from  resources " +
            "where" +
            " alias =#{uuid}  " +
            "limit 1 ")
    Resource resourceExist(@Param("uuid") String uuid);
}
