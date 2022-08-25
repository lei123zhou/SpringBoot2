package cn.tedu.boot54.controller;

import cn.tedu.boot54.entity.User;
import cn.tedu.boot54.mapper.UserMapper;
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
            //u!=null说明用户名不存在可以注册
            return 2;
        }
        return 1;
    }

    @RequestMapping("/login")
    public int login(User user, HttpSession session) {
        User u = userMapper.selectByUsername(user.getUsername());
        if (u != null) {
            //u!=null说明用户名存在继续验证密码
            if (u.getPassword().equals(user.getPassword())) {
                session.setAttribute("user", u);
                return 1;
            }
            return 3;
        }
        return 2;
    }

    @RequestMapping("logout")
    public void logout(HttpSession session) {
        session.removeAttribute("user");
    }

    @RequestMapping("/checklogin")
    public boolean checkLogin(HttpSession session) {
        User u = (User) session.getAttribute("user");
        return u == null ? false : true;
    }
}
