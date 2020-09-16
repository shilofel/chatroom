package com.net.chatroom.service;

import com.net.chatroom.model.po.UserInfo;
import com.net.chatroom.model.vo.ResponseJson;

import javax.servlet.http.HttpSession;
import java.util.Map;

public interface RegisterService {


    ResponseJson addUser(UserInfo user,HttpSession session);
}
