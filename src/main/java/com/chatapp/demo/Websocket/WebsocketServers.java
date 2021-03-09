package com.chatapp.demo.Websocket;

import com.alibaba.fastjson.JSONObject;
import jdk.nashorn.internal.runtime.regexp.JoniRegExp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.text.MessageFormat;
import java.util.concurrent.ConcurrentHashMap;


@Component
@EnableScheduling
@ServerEndpoint(value = "/Websocket/{user_id}")
public class WebsocketServers {
    //把实体类存到这个ConcurrentHashMap,static是因为所有人共用这个Map
    //public static int OnlineCount=0;可用加锁协议来并发改变这个
    private static final Logger LOGGER = LoggerFactory.getLogger(WebsocketServers.class);
    private static ConcurrentHashMap<String, Session> sessionServer = new ConcurrentHashMap<>();
    private String user_id;
    private Session session;

    /**
     * @OnOpen 通道建立成功需要执行的操作
     */

    @OnOpen
    public void onOpen(@PathParam(value = "user_id") String user_id, Session session) {

        this.session = session;

        sessionServer.put(user_id, session);//将实体类加载进去用id和session标识

        this.user_id = user_id;
        JSONObject j = new JSONObject();//JSONOBject是一个alibab的jar包

        j.put("targetID", "onopen");

        j.put("user_id", this.user_id);

        for (String key : sessionServer.keySet()) {
            sessionServer.get(key).getAsyncRemote().sendText(j.toString());
        }
    }

    @OnMessage
    public void onMessage(String message) {
        JSONObject json = JSONObject.parseObject(message);
        System.out.println("message:"+message);
        LOGGER.info(MessageFormat.format("message is",message));
        if (((String)json.get("targetID")).equals("send")) {
            for (String key : sessionServer.keySet()) {
                if (key.equals(json.get("receiver_id"))) {//单发
                    sessionServer.get(key).getAsyncRemote().sendText(message);
                    return;
                }
            }
        }

        else if (((String)json.get("targetID")).equals("AUDIO_MSG_SINGLE_SENDING")) {
            for (String key : sessionServer.keySet()) {
                if (key.equals(json.get("receiver_id"))) {//单发
                    sessionServer.get(key).getAsyncRemote().sendText(message);
                    System.out.println(message);
                    return;
                }
            }
        }
    }

    @OnClose
    public void onClose() {
        JSONObject j = new JSONObject();
        j.put("targetID", "onclosed");
        j.put("user_id", this.user_id);
        sessionServer.remove(this.user_id);//删除会话
        for (String key : sessionServer.keySet()) {//遍历所有的key，getAsyncRemote是异步的
            sessionServer.get(key).getAsyncRemote().sendText(j.toString());
        }
    }

    @OnError
    public void onError(Throwable error) {
        System.out.println("Error");
        error.printStackTrace();
    }

    @Scheduled(fixedRate = 3000)
    public void pushStatus() {
        JSONObject j = new JSONObject();

        j.put("targetID", "syspush");

        j.put("all_user_id", sessionServer.keySet());

        for (String key : sessionServer.keySet()) {
            sessionServer.get(key).getAsyncRemote().sendText(j.toString());
        }
    }
}

