package com.net.chatroom.service;

import javax.servlet.http.HttpSession;

import com.net.chatroom.model.vo.ResponseJson;

public interface SecurityService {

    ResponseJson login(String username, String password, HttpSession session);
    
    ResponseJson logout(HttpSession session);
}
