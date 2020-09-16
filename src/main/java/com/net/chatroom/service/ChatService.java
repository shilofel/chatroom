package com.net.chatroom.service;

import com.alibaba.fastjson.JSONObject;

import javax.websocket.Session;

public interface ChatService {
    public void register(JSONObject param,Session session);
    
    public void singleSend(JSONObject param,Session session);
    
    public void groupSend(JSONObject param,Session session);
    
    public void FileMsgSingleSend(JSONObject param,Session session);
    
    public void FileMsgGroupSend(JSONObject param,Session session);
    
//    public void remove(Session session);
    
    public void typeError(Session session);
        
}
