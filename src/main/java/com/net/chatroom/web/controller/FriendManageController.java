package com.net.chatroom.web.controller;

import com.net.chatroom.model.po.Friends;
import com.net.chatroom.model.po.UserInfo;
import com.net.chatroom.service.FriendManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
public class FriendManageController {

    @Autowired
    FriendManageService friendManageService;

    @RequestMapping(value="/searchUser_action" ,method= RequestMethod.POST)
    public List<UserInfo> searchUser(HttpSession session, @RequestParam("key") String key){
        System.out.println(friendManageService.searchUser(key,session));
        return friendManageService.searchUser(key,session);
    }
    @RequestMapping(value="/addFriend_action",method= RequestMethod.POST)
    public void addFriend(@RequestParam Map<String,Object> map){
         friendManageService.addFriend(map);
    }

    @RequestMapping(value="/acceptFriend_action",method= RequestMethod.POST)
    public void acceptFriend(@RequestParam Map<String,Object> map){
        friendManageService.acceptFriend(map);
    }
    @RequestMapping(value="/getFriends_action",method=RequestMethod.POST)
    public List<Friends> getFriendById(@RequestParam("user_id") String user_id){
        return friendManageService.getFriendsById(user_id);
    }
    @RequestMapping(value="/getApplyFriend_action",method=RequestMethod.POST)
    public List<Friends> getApplyFriends(@RequestParam("friend_id") String friend_id){
        return friendManageService.getApplyFriend(friend_id);
    }




}
