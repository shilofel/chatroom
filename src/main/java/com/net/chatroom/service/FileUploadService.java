package com.net.chatroom.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.net.chatroom.model.vo.ResponseJson;

public interface FileUploadService {

    ResponseJson upload(MultipartFile file, HttpServletRequest request);
}
