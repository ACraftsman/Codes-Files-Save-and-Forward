package com.example.cfsf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.cfsf.mapper.UserMapper;
import com.example.cfsf.pojo.User;
import com.example.cfsf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public int register(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return userMapper.insert(user) == 1 ? user.getId() : -1;
    }

    @Override
    public User findByUserName(String username) {
        return userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
    }
}
