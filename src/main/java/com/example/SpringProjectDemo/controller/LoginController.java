package com.example.SpringProjectDemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.SpringProjectDemo.common.Const;
import com.example.SpringProjectDemo.common.Response;
import com.example.SpringProjectDemo.config.RocketMq.content.UserContent;
import com.example.SpringProjectDemo.entity.User;
import com.example.SpringProjectDemo.exception.BaseException;
import com.example.SpringProjectDemo.service.LoginService;
import com.example.SpringProjectDemo.service.UserService;
import com.example.SpringProjectDemo.utils.ResultUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author qinzhibin
 * @description 登录控制类
 * @date 2021/3/30
 */
@RestController
@RequestMapping("/login")
public class LoginController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private LoginService loginService;

    /**
     * @Description: 用户登录
     * @Param:
     * @Author: qinzhibin
     * @Date: 2021/3/30
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiOperation(value = "/login",notes = "用户登录", httpMethod = "POST", produces = "application/json", consumes = "application/json")
    @ApiResponses({@ApiResponse(code = 200, message="操作成功", response = Response.class), @ApiResponse(code = 500, message = "操作失败", response = Response.class)})
    public Response<?> login(@RequestBody User user){
        try{

            if(user.getUserAccount() == null || user.getPassword() == null){
                return ResultUtils.ResultErrorUtil("未获取到登录名或密码");
            }
            User u  = userService.getUser(user);
            if(u == null) {
                return ResultUtils.ResultErrorUtil("登录失败，未查询到该用户");
            }
            logger.info("--------------------用户登录成功，登录用户名为" + user.getUserAccount() + "------------");
            return ResultUtils.ResultSuccessUtilMessage(null, "登录成功");

        }catch (Exception e){
            return ResultUtils.ResultErrorUtil("新增用户异常");
        }
    }


    /**
     * 执行登录
     */
    @PostMapping("/login1")
    @ResponseBody
    public Response<?> login(@RequestBody User user, HttpSession session, HttpServletRequest request, HttpServletResponse response) {

        if(StringUtils.isBlank(user.getUserAccount()) || StringUtils.isBlank(user.getPassword())){
            return ResultUtils.ResultErrorUtil("未获取到登录名或密码");
        }
        JSONObject j = loginService.doLogin(user, session, request, response);
        return ResultUtils.ResultSuccessUtilMessage(j,"登录成功");
    }

    @RequestMapping(value = "/getSession")
    public Response<?> getSession() {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        System.out.println(request.getSession().getAttribute("userInfo"));

        return ResultUtils.ResultSuccessUtilMessage(null, "退出登录成功");
    }


    /**
     * 退出登录
     */
    @RequestMapping(value = "/logout")
    public Response<?> logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        // 删除session里面的用户信息
        session.removeAttribute(Const.SYSTEM_USER_SESSION);
        // 保存cookie，实现自动登录
        Cookie cookie_username = new Cookie("cookie_username", "");
        // 设置cookie的持久化时间，0
        cookie_username.setMaxAge(0);
        // 设置为当前项目下都携带这个cookie
        cookie_username.setPath(request.getContextPath());
        // 向客户端发送cookie
        response.addCookie(cookie_username);
        return ResultUtils.ResultSuccessUtilMessage(null, "退出登录成功");
    }

}
