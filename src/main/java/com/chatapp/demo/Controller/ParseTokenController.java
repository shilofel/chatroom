package com.chatapp.demo.Controller;

import com.chatapp.demo.model.User;
import com.chatapp.demo.Service.UserService;
import com.chatapp.demo.Utils.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ParseTokenController {
    @Autowired
    UserService userService;
    /**
     * 解析token
     * */
    @RequestMapping("api/parseToken")
    public User parseToken(@RequestParam("token") String token){
        //解析token
        String username = JWT.parseToken(token);
        //并且返回对象信息
        return userService.queryUserInfo(username);
    }


}
