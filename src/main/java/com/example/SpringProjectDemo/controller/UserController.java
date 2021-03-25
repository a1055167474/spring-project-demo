package com.example.SpringProjectDemo.controller;

import com.example.SpringProjectDemo.entity.User;
import com.example.SpringProjectDemo.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

/**
 * @author qinzhibin
 * @description TODO
 * @date 2021年03月23日
 */

@RestController
@RequestMapping("/user")
public class UserController {

    //todo 添加LOGGER工具
    //private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getAllUser",method = RequestMethod.GET)
    public List<User> getAllUser(){
        return userService.getALlUser();
    }


    @RequestMapping(value = "/getUserByPage",method = RequestMethod.GET)
    public List<User> getUserByPage(@RequestParam Integer page, @RequestParam Integer size){
        return userService.getAllUser(page,size);
    }



}
