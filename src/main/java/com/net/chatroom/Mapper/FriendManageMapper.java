package com.net.chatroom.Mapper;

import com.net.chatroom.model.po.Friends;
import com.net.chatroom.model.po.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface FriendManageMapper {

    @Select("SELECT username,nick_name,header_img FROM user_info WHERE username=#{key} OR nick_name LIKE '%${key}%' ")
    List<UserInfo> searchUser(String key);
    @Insert("INSERT INTO friends(user_id,friend_id,ctime,isaccept,reason,type) "+"VALUE(#{user_id},#{friend_id},#{ctime},0,#{reason},#{type})")
    void addFriend(Map<String,Object> map);
    @Insert("INSERT INTO friends(user_id,friend_id,ctime,isaccept,reason,type)"+"VALUE(#{user_id},#{friend_id},#{ctime},1,#{reason},#{type})")
    void acceptFriend(Map<String,Object> map);
    @Update("UPDATE friends SET isaccept=1 WHERE user_id=#{friend_id} AND friend_id=#{user_id}")
    void updateFriend(Object firend_id,Object user_id);
    @Select("SELECT friends.friend_id,friends.ctime,nick_name AS friend_nick_name, header_img AS friend_header_img " +
            "FROM friends LEFT JOIN user_info ON frined_id=username WHERE user_id=#{user_id} AND isaccept=1")
    List<Friends> getFriendsById(String user_id);

    @Select("SELECT user_id,friends.ctime,reason,isaccept,header_img AS user_header_img,nick_name AS user_nick_name FROM friends\n" +
            "        LEFT JOIN user_info ON username=user_id\n" +
            "        WHERE friend_id=#{friend_id} AND type=0")
    List<Friends> getApplyFriend(String friend_id);





}
