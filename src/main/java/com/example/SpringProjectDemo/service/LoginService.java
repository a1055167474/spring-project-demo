package com.example.SpringProjectDemo.service;

import com.alibaba.fastjson.JSONObject;
import com.example.SpringProjectDemo.entity.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author qinzhibin
 * @description
 * @date 2021/3/31
 */
@Service
public interface LoginService {

    JSONObject doLogin(User user, HttpSession session, HttpServletRequest request, HttpServletResponse response);

}
