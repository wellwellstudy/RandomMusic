package com.ran.randommusic.controller;

import com.ran.randommusic.model.User;
import com.ran.randommusic.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by ran on 17/7/30.
// */
@Controller
@RequestMapping("/user")
public class UserController {

    private Logger log = Logger.getLogger(UserController.class);
    @Resource
    private UserService userService;

    @RequestMapping("/showUser")
    public String showUser(HttpServletRequest request, Model model){
        log.info("查询所有用户信息");
        List<User> userList = userService.getAllUser();
        model.addAttribute("userList",userList);
        return "showUser";
    }


    @RequestMapping("/showUserById")
    public String showUserById(HttpServletRequest request, Model model){
        log.info("使用id查询所有用户");
        long userId = Long.parseLong(request.getParameter("userId"));
        User user = userService.getUserById(userId);
        model.addAttribute("user",user);
        return "showUserById";
    }
}
