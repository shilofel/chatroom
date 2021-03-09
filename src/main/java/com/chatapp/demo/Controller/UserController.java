package com.chatapp.demo.Controller;

import com.chatapp.demo.model.Friend;
import com.chatapp.demo.model.User;
import com.chatapp.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    UserService userService;


    @RequestMapping("api/checkIsExist_Action")
    public boolean checkIsExist(@RequestParam("username")String username){
        return userService.checkIsExist(username);
    }
    @RequestMapping("api/register_Action")
    public void registerUser(@RequestParam Map<String,Object> map){
        userService.registerUser(map);
    }

    @RequestMapping("api/login_action")
    public boolean login(HttpServletRequest request, HttpServletResponse response){
       return userService.login(request,response);
    }

    @RequestMapping("api/searchUser_action")
    public List<User> searchUser(@RequestParam("key") String key){
        return userService.searchUser(key);
    }

    @RequestMapping("api/getFriends_action")
    public List<Friend> getFriendsById(@RequestParam("user_id") String user_id){
        return userService.getFriendsById(user_id);
    }

    @RequestMapping("api/addFriend_action")
    public void addFriend(@RequestParam Map<String,Object> map){
        userService.addFriend(map);
    }

    @RequestMapping("api/getApplyFriend_action")
    public List<Friend> getApplyFriend(@RequestParam("friend_id") String friend_id){
        return userService.getApplyFriend(friend_id);
    }

    @RequestMapping("api/acceptFriend_action")
    public void acceptFriend(@RequestParam Map<String,Object> map){
        userService.acceptFriend(map);
    }

    @RequestMapping("api/changeHeader_action")
    public void changeHeader(@RequestParam("username") String username,@RequestParam("header_img") String header_img){
        userService.changeHeader(username,header_img);
    }

}
