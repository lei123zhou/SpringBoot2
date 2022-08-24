package cn.tedu.boot41.controller;

import cn.tedu.boot41.entity.User;
import cn.tedu.boot41.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;

@RestController
public class UserController {
    @Autowired
    UserMapper userMapper;

    @RequestMapping("/check")
    public String reg(String username, String password) {
        System.out.println("username = " + username + ", password = " + password);
        User user = userMapper.selectByUserName(username);
        return user == null ? "用户名可用" : "用户名已经存在";
    }
}
