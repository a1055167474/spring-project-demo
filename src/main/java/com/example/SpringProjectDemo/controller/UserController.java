package com.example.SpringProjectDemo.controller;

import com.example.SpringProjectDemo.entity.User;
import com.example.SpringProjectDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author qinzhibin
 * @description TODO
 * @date 2021年03月23日
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getAllUser")
    public List<User> getAllUser(){
        return userService.getALlUser();
    }


    @RequestMapping("/getUserByPage")
    public List<User> getUserByPage(@RequestParam Integer page, @RequestParam Integer size){
        return userService.getAllUser(page,size);
    }



}
