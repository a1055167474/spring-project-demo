package com.example.SpringProjectDemo.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.example.SpringProjectDemo.common.Const;
import com.example.SpringProjectDemo.common.Response;
import com.example.SpringProjectDemo.dao.UserDao;
import com.example.SpringProjectDemo.entity.User;
import com.example.SpringProjectDemo.service.LoginService;
import com.example.SpringProjectDemo.service.UserService;
import com.example.SpringProjectDemo.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @author qinzhibin
 * @description
 * @date 2021年03月23日
 */


@Service
public class LoginServiceImpl implements LoginService {

    private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    private UserDao userDao;


    /**
     * 执行登录
     */
    public JSONObject doLogin(User user, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        // 最终返回的对象
        JSONObject res = new JSONObject();
        res.put("code", 0);
        String username = user.getUserAccount();
        String password = user.getPassword();
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            res.put("msg", "请输入手机号或密码");
            return res;
        }
        User oldUser = userDao.getUserInfoByUserAccount(username);
        if (null == oldUser) {
            res.put("msg", "该账号不存在，请检查后重试");
            return res;
        }
        // 验证密码是否正确
//        String newPassword = PasswordUtils.getMd5(password, username, user.getSalt());
        if (!password.equals(oldUser.getPassword())) {
            res.put("msg", "手机号或密码错误，请检查后重试");
            return res;
        }

        // 将登录用户信息保存到session中
        session.setAttribute(Const.SYSTEM_USER_SESSION, oldUser);
        session.setMaxInactiveInterval(1800);
        // 保存cookie，实现自动登录
        Cookie cookie_username = new Cookie("cookie_username", username);
        // 设置cookie的持久化时间，单位：秒
        cookie_username.setMaxAge(300);
        // 设置为当前项目下都携带这个cookie
        cookie_username.setPath(request.getContextPath());
        // 向客户端发送cookie
        response.addCookie(cookie_username);
        res.put("code", 1);
        res.put("msg", "登录成功");
        return res;
    }

}
