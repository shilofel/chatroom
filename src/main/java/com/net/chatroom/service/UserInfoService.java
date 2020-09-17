package com.net.chatroom.service;

import com.net.chatroom.model.po.UserInfo;
import com.net.chatroom.model.vo.ResponseJson;

import javax.servlet.http.HttpSession;

public interface UserInfoService {

    //ResponseJson getByUserId(String userId);
    UserInfo queryUserInfo(String username, HttpSession session);

}
