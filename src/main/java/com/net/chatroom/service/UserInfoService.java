package com.net.chatroom.service;

import com.net.chatroom.model.vo.ResponseJson;

public interface UserInfoService {

    ResponseJson getByUserId(String userId);
}
