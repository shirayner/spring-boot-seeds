package com.ray.study.springboot11springsecuritystandard.controller;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * description
 *
 * @author shira 2019/08/30 17:02
 */

@RestController
public class HelloController {

    @RequestMapping("/")
    public String home(){
        return "welcome to study spring security!";
    }

    @RequestMapping("/hello")
    public String hello() {
        return "hello world";
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/roleAuth")
    public String role() {
        return "admin auth";
    }


    @PreAuthorize("#id<10 and principal.username.equals(#username) and #user.username.equals('abc')")
    @PostAuthorize("returnObject%2==0")
    @RequestMapping("/test")
    public Integer test(Integer id, String username, User user) {
        // ...
        return id;
    }

    @PreFilter("filterObject%2==0")
    @PostFilter("filterObject%4==0")
    @RequestMapping("/test2")
    public List<Integer> test2(List<Integer> idList) {
        // ...
        return idList;
    }



}
