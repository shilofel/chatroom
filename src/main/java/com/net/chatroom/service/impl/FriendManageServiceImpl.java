package com.net.chatroom.service.impl;

import com.net.chatroom.Mapper.FriendManageMapper;
import com.net.chatroom.model.po.Friends;
import com.net.chatroom.model.po.UserInfo;
import com.net.chatroom.model.vo.ResponseJson;
import com.net.chatroom.service.FriendManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Service("friendManageService")
public class FriendManageServiceImpl implements FriendManageService {

    @Autowired
    FriendManageMapper friendManageMapper;

    @Override
    public List<UserInfo> searchUser(String key, HttpSession session){
        List<UserInfo> userList= friendManageMapper.searchUser(key);
        System.out.println(userList);
        return userList;
    }
    @Override
    public void addFriend(Map<String,Object> map){
        friendManageMapper.addFriend(map);
    }
    @Override
    public void acceptFriend(Map<String,Object> map) {
        friendManageMapper.acceptFriend(map);
        friendManageMapper.updateFriend(map.get("friend_id"),map.get("user_id"));
    }
    @Override
    public List<Friends> getFriendsById(String user_id){
        return friendManageMapper.getFriendsById(user_id);
    }
    @Override
    public List<Friends> getApplyFriend(String friend_id){
        return friendManageMapper.getApplyFriend(friend_id);
    }

}
