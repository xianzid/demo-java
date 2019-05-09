package com.example.demo.controller;

import com.example.demo.model.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@RestController
public class UserController {

    @RequestMapping("/hello")
    public String index(){
        return "Hello World !";
    }

    @RequestMapping("/user")
    @Cacheable(value="user-value")
    public User getUser(){
        User user = new User("Ming","123");
        return user;
    }

    @RequestMapping("/uid")
    String uid(HttpSession session){
        UUID uuid = (UUID) session.getAttribute("uid");
        if (null == uuid){
            uuid = UUID.randomUUID();
        }
        session.setAttribute("uid", uuid);
        return session.getId();
    }
}
