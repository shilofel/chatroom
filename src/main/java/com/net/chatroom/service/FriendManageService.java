package com.net.chatroom.service;

import com.net.chatroom.model.po.Friends;
import com.net.chatroom.model.po.UserInfo;
import com.net.chatroom.model.vo.ResponseJson;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface FriendManageService {

    List<UserInfo> searchUser(String key, HttpSession session);
    void addFriend(Map<String,Object> map);
    void acceptFriend(Map<String,Object> map);
    List<Friends> getFriendsById(String user_id);
    List<Friends> getApplyFriend(String friend_id);

}
