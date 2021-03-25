package com.example.SpringProjectDemo.controller;

import com.example.SpringProjectDemo.service.RedisService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author qinzhibin
 * @description Redis控制类
 * @date 2021/3/25
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Resource
    private RedisService redisService;

    /**
     * @Description: 向redis中存储string类型的value
     * @Param:
     * @Author: qinzhibin
     * @Date: 2021/3/25
     */
    @PostMapping("/setRedis")
    public Boolean setRedis(String name) {
        return redisService.set("name", name);
    }


    /**
     * @Description: redis 取出key为name的value
     * @Param:
     * @Author: qinzhibin
     * @Date: 2021/3/25
     */
    @GetMapping("/getRedis")
    public String getRedis() {
        return redisService.get("name");
    }

}
