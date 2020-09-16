package com.net.chatroom.web.controller;


import com.net.chatroom.model.po.UserInfo;
import com.net.chatroom.model.vo.ResponseJson;
import com.net.chatroom.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
public class RegisterController {
    @Autowired
    RegisterService registerService;


    @RequestMapping(value="/register_Action" ,method=RequestMethod.POST)
    public ResponseJson registerUser(HttpSession session,@RequestParam("username") String username,
                                     @RequestParam("password") String password,@RequestParam("nickname") String nickname){


        UserInfo user=new UserInfo();
        user.setUsername(username);
        user.setPassword(password);
        user.setNickname(nickname);
        System.out.println(registerService.addUser(user,session));
        return registerService.addUser(user,session);

    }
}
