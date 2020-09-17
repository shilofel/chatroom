package com.net.chatroom.model.po;

import java.util.List;

public class UserInfo {

    private String userId;
    private String username;
    private String password;
    private String nickname;
    private String head_img;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


    private List<UserInfo> friendList;

    public String getHead_img() {
        return head_img;
    }

    public void setHead_img(String head_img) {
        this.head_img = head_img;
    }

    private List<GroupInfo>groupList;
    
    public UserInfo() {
        super();
    }

    public UserInfo(String userId, String username, String password, String nickname, String head_img, List<UserInfo> friendList, List<GroupInfo> groupList) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.head_img=head_img;
        this.friendList = friendList;
        this.groupList = groupList;
    }

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }




    public List<UserInfo> getFriendList() {
        return friendList;
    }

    public void setFriendList(List<UserInfo> friendList) {
        this.friendList = friendList;
    }

    public List<GroupInfo> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<GroupInfo> groupList) {
        this.groupList = groupList;
    }


}
