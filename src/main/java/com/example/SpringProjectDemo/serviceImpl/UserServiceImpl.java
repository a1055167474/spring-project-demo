package com.example.SpringProjectDemo.serviceImpl;

import com.example.SpringProjectDemo.dao.UserDao;
import com.example.SpringProjectDemo.entity.User;
import com.example.SpringProjectDemo.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qinzhibin
 * @description TODO
 * @date 2021年03月23日
 */


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public List<User> getALlUser() {
        return userDao.getAllUser();
    }

    @Override
    public List<User> getAllUser(Integer page , Integer size){

        PageHelper.startPage(page,size);
        return userDao.getAllUser();
    }

}
