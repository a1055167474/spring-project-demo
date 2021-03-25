package com.example.SpringProjectDemo.service;

import com.example.SpringProjectDemo.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    //获取所有用户信息
    List<User> getALlUser();

    //分页获取所有用户信息
    List<User> getAllUser(Integer page,Integer size);

}
