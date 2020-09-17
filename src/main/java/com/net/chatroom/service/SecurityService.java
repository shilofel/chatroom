package com.net.chatroom.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.net.chatroom.model.vo.ResponseJson;

public interface SecurityService {

    ResponseJson login(HttpServletRequest request, HttpServletResponse response);
    
    ResponseJson logout(HttpSession session);
}
