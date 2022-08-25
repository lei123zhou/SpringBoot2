package cn.tedu.boot53.controller;

import cn.tedu.boot53.entity.User;
import cn.tedu.boot53.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class UserController {
    @Autowired
    UserMapper userMapper;

    @RequestMapping("/reg")
    public int reg(User user) {
        User u = userMapper.selectByUsername(user.getUsername());
        if (u != null) {
            return 2;
        }
        userMapper.insert(user);
        return 1;
    }

    @RequestMapping("/login")
    public int login(User user, HttpSession session) {
        User u = userMapper.selectByUsername(user.getUsername());
        if (u != null) {
            if (u.getPassword().equals(user.getPassword())) {
                session.setAttribute("user", u);
                return 1;
            }
            return 3;
        }
        userMapper.insert(user);
        return 2;
    }

    @RequestMapping("/checklogin")
    public boolean checkLogin(HttpSession session) {
        User user = (User) session.getAttribute("user");
        return user == null ? false : true;
    }

    @RequestMapping("logout")
    public void logout(HttpSession session) {
        session.removeAttribute("user");
    }
}
