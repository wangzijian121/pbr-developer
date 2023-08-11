package com.example.api.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


import com.example.api.service.UserServicesI;

import com.example.dao.entity.User;
import com.example.dao.mapper.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServicesImpl implements UserServicesI {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> queryAll() {

        QueryWrapper<User> wapper = new QueryWrapper<User>();
        return userMapper.selectList(wapper);
    }

}
