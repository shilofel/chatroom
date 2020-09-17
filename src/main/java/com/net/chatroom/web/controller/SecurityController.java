package com.net.chatroom.web.controller;

import com.net.chatroom.model.vo.ResponseJson;
import com.net.chatroom.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class SecurityController {

    @Autowired
    SecurityService securityService;


    
    @RequestMapping(value = "/logining", method = RequestMethod.POST)
    @ResponseBody
    public ResponseJson login(HttpServletRequest request, HttpServletResponse response) {
        return securityService.login(request,response);
    }
    
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public ResponseJson logout(HttpSession session) {
        return securityService.logout(session);
    }
}
