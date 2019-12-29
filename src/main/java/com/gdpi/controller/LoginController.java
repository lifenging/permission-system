package com.gdpi.controller;

import com.gdpi.bean.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private HttpSession session;

    @RequestMapping("/login")
    public String login(String username, String password){

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        subject.login(token);

        User user = (User) subject.getPrincipal();

        session.setAttribute("loginUser",user);

        return "main";
    }

    @RequestMapping("/logout_")
    public String logOut(){
        return "login";
    }

}
