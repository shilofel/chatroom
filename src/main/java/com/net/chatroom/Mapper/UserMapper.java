package com.net.chatroom.Mapper;

import com.net.chatroom.model.po.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user_info WHERE username=#{username}")
    UserInfo  getUserByUsername(UserInfo user);
    @Select("SELECT * FROM user_info WHERE username=#{username}")
    UserInfo checkIsExist(String username);
    @Insert("INSERT INTO user_info(username,password,nick_name) "+"VALUES(#{username},#{password},#{nickname})")
    boolean insertUser(UserInfo user);
}
