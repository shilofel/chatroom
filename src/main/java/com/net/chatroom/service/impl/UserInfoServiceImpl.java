package com.net.chatroom.service.impl;

import com.net.chatroom.Mapper.UserMapper;
import com.net.chatroom.model.po.UserInfo;
import com.net.chatroom.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    UserMapper userMapper;

    @Override
    public UserInfo queryUserInfo(String username, HttpSession session){
        UserInfo user=new UserInfo();
        user.setUsername(username);
        System.out.println(userMapper.queryUserInfo(user));
        return userMapper.queryUserInfo(user);
    }
}
