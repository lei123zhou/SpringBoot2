package cn.tedu.boot52.controller;

import cn.tedu.boot52.entity.User;
import cn.tedu.boot52.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.sql.ResultSet;

@RestController
public class UserController {
    @Autowired
    UserMapper userMapper;

    @RequestMapping("/reg")
    public int reg(User user) {
        //查询是否用户输入的用户名存在
        User u = userMapper.selectByUsername(user.getUsername());
        if (u != null) {
            /*u不等于null说明用户存在*/
            return 2;
        }
        /*只要他是不存在就可以实施我们的注册业务*/
        userMapper.insert(user);
        return 1;
    }

    @RequestMapping("/login")
    public int login(User user, HttpSession session) {
        User u = userMapper.selectByUsername(user.getUsername());
        if (u != null) {
            /*u不等于null说明,有这个用户的存在*/
            if (u.getPassword().equals(user.getPassword())) {
                /*u代表当前登录的对象
                 * 登录成功时 把当前登录的用户对象保存到session里面*/
                session.setAttribute("user", u);
                return 1;//密码也符合用户输入的，可以登录
            }
            return 3;//数据库里面的密码与用户输入的不一样
        }
        return 2;//用户名不存在
    }

    @RequestMapping("/checklogin")
    public boolean checkLogin(HttpSession session) {
        User user = (User) session.getAttribute("user");
        //user为null说明没有登录返回false反之为true;
        return user == null ? false : true;
    }

    @RequestMapping("/logout")
    public void logout(HttpSession session) {
        //删除session中的user对象 表示推出登录
        session.removeAttribute("user");
    }
}
