package com.net.chatroom.web.controller;

import com.net.chatroom.model.po.UserInfo;
import com.net.chatroom.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.net.chatroom.util.JWT;

import javax.servlet.http.HttpSession;

@RestController
public class ParseTokenController {
    @Autowired
    UserInfoService userInfoService;
    //解析Token
    @RequestMapping("/parseToken")
    public UserInfo parseToken(@RequestParam("token") String token, HttpSession session){
        String username=JWT.parseToken(token);
        return userInfoService.queryUserInfo(username,session);
    }
}
