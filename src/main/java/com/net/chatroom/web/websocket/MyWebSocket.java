package com.net.chatroom.web.websocket;

import com.alibaba.fastjson.JSONObject;
import com.net.chatroom.model.vo.ResponseJson;
import com.net.chatroom.service.ChatService;
import com.net.chatroom.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/websocket")
@Component
public class MyWebSocket {
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    private String user_Id;

    private static ChatService chatService;

    @Autowired
    public void setChatService(ChatService chatService) {
        MyWebSocket.chatService = chatService;
    }
    /**
     *
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) {
        this.setChatService(chatService);
        this.session = session;
        Constant.webSocketSet.add(this);     //加入set中
        System.out.println("有新连接加入！当前在线人数为" + Constant.webSocketSet.size());
        this.session.getAsyncRemote().sendText("恭喜您成功连接上WebSocket-->当前在线人数为："+Constant.webSocketSet.size());
    }
    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        Constant.webSocketSet.remove(this);  //从set中删除
        System.out.println("有一连接关闭！当前在线人数为" + Constant.webSocketSet.size());
    }
    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);
        JSONObject param = null;
        try {
            param = JSONObject.parseObject(message);
        } catch (Exception e) {
            sendErrorMessage(session, "JSON字符串转换出错！");
            e.printStackTrace();
        }
        if (param == null) {
            sendErrorMessage(session, "参数为空！");
            return;
        }
        String type = (String) param.get("type");
        System.out.println(type);
        System.out.println(session);
        switch (type) {
            case "REGISTER":
                chatService.register(param,session);
                break;
            case "SINGLE_SENDING":
                chatService.singleSend(param,session);
                break;
            case "GROUP_SENDING":
                chatService.groupSend(param,session);
                break;
            case "FILE_MSG_SINGLE_SENDING":
                chatService.FileMsgSingleSend(param,session);
                break;
            case "FILE_MSG_GROUP_SENDING":
                chatService.FileMsgGroupSend(param,session);
                break;
            default:
                chatService.typeError(session);
                break;
        }
    }
    /**
     * 发生错误时调用
     *
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }
    /**
     * 群发自定义消息
     * */
    public  void broadcast(String message){
        for (MyWebSocket item : Constant.webSocketSet) {
            //同步异步说明参考：http://blog.csdn.net/who_is_xiaoming/article/details/53287691
            //this.session.getBasicRemote().sendText(message);
            item.session.getAsyncRemote().sendText(message);//异步发送消息.
        }
    }

    private void sendErrorMessage(Session session, String errorMsg) {
        String responseJson = new ResponseJson()
                .error(errorMsg)
                .toString();
        session.getAsyncRemote().sendText(responseJson);
    }
}