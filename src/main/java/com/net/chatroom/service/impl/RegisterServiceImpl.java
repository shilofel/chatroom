package com.net.chatroom.service.impl;

import com.net.chatroom.Mapper.UserMapper;
import com.net.chatroom.model.po.UserInfo;
import com.net.chatroom.model.vo.ResponseJson;
import com.net.chatroom.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Map;


@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResponseJson addUser(UserInfo user,HttpSession session){

        UserInfo userInfo=userMapper.getUserByUsername(user);
        System.out.println(userInfo);
        if(userInfo!=null){
            return new ResponseJson().error("用户名已存在");
        }else {
            boolean sign = userMapper.insertUser(user);
            if (sign == false) {
                return new ResponseJson().error("注册失败");
            } else {
                System.out.println(new ResponseJson().success());
                return new ResponseJson().success();
            }
        }

    }


}
