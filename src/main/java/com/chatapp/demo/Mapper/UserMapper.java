package com.chatapp.demo.Mapper;


import com.chatapp.demo.model.Friend;
import com.chatapp.demo.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    /**
     * 用户名校验是否存在
     * */
     User checkIsExist(String username);
     /**
      * 注册用户
      * */
     void registerUser(Map<String, Object> map);
    /**
     * 登陆Action
     * */
     User login(@Param("username") String username, @Param("password") String password);
    /**
     * 查找用户
     * */
    List<User> searchUser(String key);
    /**
     * 查询我的好友列表
     * */
    List<Friend> getFriendsById(String user_id);
    /**
     * 添加好友
     * */
    void addFriend(Map<String, Object> map);
    /**
     * 同意好友
     * */
    void acceptFriend(@Param("user_id") String user_id, @Param("friend_id") String friend_id);
    /**
     * 查询申请的好友
     * */
    List<Friend> getApplyFriend(String friend_id);
    /**
     * 更换头像
     * */
    void changeHeader(@Param("username") String username, @Param("header_img") String header_img);
    /**
     * 查询用户信息，用于作为基本信息
     * */
    User queryUserInfo(String username);
}
