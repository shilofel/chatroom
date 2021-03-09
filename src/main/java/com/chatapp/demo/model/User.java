package com.chatapp.demo.model;

public class User {
    private String username;
    private String nick_name;
    private String header_img;

    public User() {super();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getHeader_img() {
        return header_img;
    }

    public void setHeader_img(String header_img) {
        this.header_img = header_img;
    }
    public String toString(){
        return "{username:'"+username+"',nick_name:'"+nick_name+"',header_img:'"+header_img+"'}";
    }
}