package com.example.cfsf.service;

import com.example.cfsf.pojo.User;

public interface UserService {
    int register(String username, String password);

    User findByUserName(String username);
}
