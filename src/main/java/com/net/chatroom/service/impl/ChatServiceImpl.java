package com.net.chatroom.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.net.chatroom.model.po.GroupInfo;
import com.net.chatroom.model.vo.ResponseJson;
import com.net.chatroom.service.ChatService;
import com.net.chatroom.util.ChatType;
import com.net.chatroom.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.websocket.Session;
import java.text.MessageFormat;

@Service
public class ChatServiceImpl implements ChatService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChatServiceImpl.class);



    @Override
    public void register(JSONObject param, Session session) {
        String userId = (String)param.get("userId");
        Constant.onlineUserMap.put(userId, session);
        String responseJson = new ResponseJson().success()
                .setData("type", ChatType.REGISTER)
                .toString();
        sendMessage(responseJson,session);
        LOGGER.info(MessageFormat.format("userId为 {0} 的用户登记到在线用户表，当前在线人数为：{1}"
                , userId, Constant.onlineUserMap.size()));
    }

    @Override
    public void singleSend(JSONObject param,Session session) {
        String fromUserId = (String)param.get("fromUserId");
        String toUserId = (String)param.get("toUserId");
        String content = (String)param.get("content");
        Session toSession = Constant.onlineUserMap.get(toUserId);
        if (toSession == null) {
            String responseJson = new ResponseJson()
                    .error(MessageFormat.format("userId为 {0} 的用户没有登录！", toUserId))
                    .toString();
            sendMessage(responseJson,session);
        } else {
            String responseJson = new ResponseJson().success()
                    .setData("fromUserId", fromUserId)
                    .setData("content", content)
                    .setData("type", ChatType.SINGLE_SENDING)
                    .toString();
            sendMessage(responseJson,toSession);
        }
    }

    @Override
    public void groupSend(JSONObject param,Session session) {

        String fromUserId = (String)param.get("fromUserId");
        String toGroupId = (String)param.get("toGroupId");
        String content = (String)param.get("content");


    }

    @Override
    public void FileMsgSingleSend(JSONObject param,Session session) {
        String fromUserId = (String)param.get("fromUserId");
        String toUserId = (String)param.get("toUserId");
        String originalFilename = (String)param.get("originalFilename");
        String fileSize = (String)param.get("fileSize");
        String fileUrl = (String)param.get("fileUrl");
        Session toSession = Constant.onlineUserMap.get(toUserId);
        if (toSession == null) {
            String responseJson = new ResponseJson()
                    .error(MessageFormat.format("userId为 {0} 的用户没有登录！", toUserId))
                    .toString();
            sendMessage(responseJson,toSession);
        } else {
            String responseJson = new ResponseJson().success()
                    .setData("fromUserId", fromUserId)
                    .setData("originalFilename", originalFilename)
                    .setData("fileSize", fileSize)
                    .setData("fileUrl", fileUrl)
                    .setData("type", ChatType.FILE_MSG_SINGLE_SENDING)
                    .toString();
            sendMessage(responseJson,toSession);
        }
    }

    @Override
    public void FileMsgGroupSend(JSONObject param,Session session) {
        String fromUserId = (String)param.get("fromUserId");
        String toGroupId = (String)param.get("toGroupId");
        String originalFilename = (String)param.get("originalFilename");
        String fileSize = (String)param.get("fileSize");
        String fileUrl = (String)param.get("fileUrl");

    }

    @Override
    public void typeError(Session session) {
        String responseJson = new ResponseJson()
                .error("该类型不存在！")
                .toString();
        sendMessage(responseJson,session);
    }



    private void sendMessage(String message,Session session) {
        session.getAsyncRemote().sendText(message);
    }


}
