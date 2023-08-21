package com.zlht.pose.developer.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.zlht.pose.developer.dao.entity.Algorithm;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface AlgorithmMapper extends BaseMapper<Algorithm> {
    @Select("SELECT *\n" +
            "FROM (\n" +
            "    SELECT a.id,\n" +
            "           a.name,\n" +
            "           a.type,\n" +
            "           a.install_type,\n" +
            "           a.file AS file_uuid,\n" +
            "           r.full_name AS file_name,\n" +
            "           a.docs,\n" +
            "           a.example,\n" +
            "           a.create_time\n" +
            "    FROM algorithm a\n" +
            "    LEFT JOIN resources r ON r.alias = a.file\n" +
            "    WHERE a.uploader = #{userId}) res \n " +
            "where  (#{keyword} IS NULL OR res.name LIKE CONCAT('%', #{keyword}, '%')) \n " +
            "and  (#{type}  = -1 OR res.type =#{type})")
    Page<Map<String, Object>> selectAlgorithm(Page<?> page, @Param("keyword") String keyword,
                                              @Param("type") int type,
                                              @Param("userId") int userId);


    @Select("select id,name from  algorithm  group by id,name")
    List<Map<String, Object>> queryAlgorithmMap();
}

