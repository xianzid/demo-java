package com.example.demo.service;

import com.example.demo.model.User;

public class UserServiceImpl implements UserService{

    @Override
    public int save(User user) {
        if (null == user){
            return -1;
        }

        return 0;
    }
}
