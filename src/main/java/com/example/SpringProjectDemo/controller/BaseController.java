package com.example.SpringProjectDemo.controller;

import com.example.SpringProjectDemo.common.Const;
import com.example.SpringProjectDemo.entity.SystemUser;
import org.apache.commons.lang3.StringUtils;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.springframework.beans.BeanUtils;
import org.springframework.session.ExpiringSession;
import org.springframework.session.SessionRepository;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import sun.net.httpserver.HttpsServerImpl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.Objects;

/**
 * @author qinzhibin
 * @description
 * @date 2021/3/31
 */
@RestController
public class BaseController implements RestController {

    //@Resource(name = "sessionRepository")
    private SessionRepository<ExpiringSession> sessionRepository;

    protected SystemUser getSystemUser(){

        ExpiringSession es = getSession(Const.SESSION_HEADER);
        if(es != null){
            SystemUser systemUser = new SystemUser();
            Map<String,Object> attr = ((AttributePrincipal) es.getAttribute(Const.SYSTEM_USER)).getAttributes();
            BeanUtils.copyProperties(systemUser,attr);
            return systemUser;
        }

        return null;
    }

    protected ExpiringSession getSession(String name){
        String sessionId = this.getSessionHeader(name);
        if(!StringUtils.isBlank(sessionId)){
            ExpiringSession es = sessionRepository.getSession(sessionId);
            return es;
        }
        return null;
    }

    protected String getSessionHeader(String name){
        return this.getRequest().getHeader(name);
    }

    protected HttpServletRequest getRequest(){
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

    protected boolean checkLogin(HttpServletRequest request){
        HttpSession session = request.getSession();
        Object obj = session.getAttribute(Const.SYSTEM_USER_SESSION);

        return true;
    }

    @Override
    public String value() {
        return null;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}
