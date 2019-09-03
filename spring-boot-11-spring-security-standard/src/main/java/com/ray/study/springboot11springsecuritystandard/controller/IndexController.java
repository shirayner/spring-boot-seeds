package com.ray.study.springboot11springsecuritystandard.controller;

import com.ray.study.springboot11springsecuritystandard.entity.RoleDO;
import com.ray.study.springboot11springsecuritystandard.entity.UserDO;
import com.ray.study.springboot11springsecuritystandard.repository.RoleRepository;
import com.ray.study.springboot11springsecuritystandard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * description
 *
 * @author shira 2019/09/02 15:50
 */
@Controller
public class IndexController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    @RequestMapping("/sign-up")
    public String signUp() {
        return "registry";
    }

    @RequestMapping("/sign-in")
    public String signIn() {
        return "login";
    }



    @RequestMapping("/registry")
    public String registry(UserDO user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if(user.getUsername().startsWith("admin")){
            user.setRoleList(roleRepository.findByName("ROLE_ADMIN"));
        }else{
            user.setRoleList(roleRepository.findByName("ROLE_USER"));
        }
        userRepository.save(user);
        return "login";
    }


    @ResponseBody
    @RequestMapping("/personal-center")
    public void login(HttpServletRequest request) {
        System.out.println("登录成功");
    }


}
