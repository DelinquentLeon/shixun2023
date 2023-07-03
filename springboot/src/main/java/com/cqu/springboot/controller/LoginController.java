package com.cqu.springboot.controller;

import com.cqu.springboot.entity.User;
import com.cqu.springboot.service.UserService;
import com.cqu.springboot.utils.result.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("login")
public class LoginController {
    @Autowired
    UserService userService;

    /**
     * 用户登录
     * @param user
     * @return
     */
    @PostMapping("login")
    public DataResult login(@RequestBody User user){
        return userService.loginUser(user);
    }

    /**
     * 退出登录
     * @param session
     * @return
     */
    @PostMapping("loginOut")
    public DataResult loginOut(HttpSession session){
        session.invalidate();
        return DataResult.succ();
    }
}
