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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.MessageFormat;
import com.net.chatroom.util.JWT;


@Service
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    private UserMapper UserMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityServiceImpl.class);
    
    
    @Override
    public ResponseJson login(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String isRemeber = request.getParameter("isRemember");
        UserInfo user=new UserInfo();
        user.setUsername(username);
        UserInfo userInfo = UserMapper.getUserByUsername(user);
        if (userInfo == null) {
            return new ResponseJson().error("不存在该用户名");
        }
        if (!userInfo.getPassword().equals(password)) {
            return new ResponseJson().error("密码不正确");
        }
        String token = JWT.createToken(user.getUsername());
        Cookie cookie = new Cookie("token",token);
        cookie.setPath("/");
        response.addCookie(cookie);
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
