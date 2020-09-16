package com.net.chatroom.service.impl;

import com.net.chatroom.Mapper.UserMapper;
import com.net.chatroom.model.po.UserInfo;
import com.net.chatroom.model.vo.ResponseJson;
import com.net.chatroom.service.SecurityService;
import com.net.chatroom.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.text.MessageFormat;


@Service
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    private UserMapper UserMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityServiceImpl.class);
    
    
    @Override
    public ResponseJson login(String username, String password, HttpSession session) {
        UserInfo user=new UserInfo();
        user.setUsername(username);
        UserInfo userInfo = UserMapper.getUserByUsername(user);
        if (userInfo == null) {
            return new ResponseJson().error("不存在该用户名");
        }
        if (!userInfo.getPassword().equals(password)) {
            return new ResponseJson().error("密码不正确");
        }
        session.setAttribute(Constant.USER_TOKEN, userInfo.getUserId());
        System.out.println(new ResponseJson().success());
        return new ResponseJson().success();
    }

    @Override
    public ResponseJson logout(HttpSession session) {
        Object userId = session.getAttribute(Constant.USER_TOKEN);
        if (userId == null) {
            return new ResponseJson().error("请先登录！");
        }
        session.removeAttribute(Constant.USER_TOKEN);
        LOGGER.info(MessageFormat.format("userId为 {0} 的用户已注销登录!", userId));
        return new ResponseJson().success();
    }

}
