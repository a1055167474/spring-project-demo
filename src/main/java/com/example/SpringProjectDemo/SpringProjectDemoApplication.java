package com.example.SpringProjectDemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@MapperScan("com.example.SpringProjectDemo.dao")
//@EnableRedisHttpSession
public class SpringProjectDemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringProjectDemoApplication.class, args);
    }

}
