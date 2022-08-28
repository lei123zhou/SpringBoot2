package cn.tedu.vrd.controller;

import cn.tedu.vrd.entity.User;
import cn.tedu.vrd.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
public class UserController {
    @Autowired
    UserMapper userMapper;

    @RequestMapping("/login")
    public int login(User user, String rem, HttpServletResponse response, HttpSession session) {
        User u = userMapper.selectUser(user.getUsername());
        if (u != null) {
            if (u.getPassword().equals(user.getPassword())) {
                //记住登录状态
                session.setAttribute("user", u);
                if (rem != null) {
                    //创建cookie对象
                    Cookie c1 = new Cookie("username", user.getUsername());
                    Cookie c2 = new Cookie("password", user.getPassword());
                    //设置保存时间
                    c1.setMaxAge(60 * 60 * 24 * 30);
                    //通过response对象将Cookie对象发给客户端
                    response.addCookie(c1);
                    response.addCookie(c2);
                }
                return 1;//登录成功
            }
            return 2;//密码错误
        }
        return 3;//用户名不存在
    }

    @RequestMapping("/checklogin")
    public boolean checkLogin(HttpSession session) {
        //取出登录成功时保存进session里面的user对象
        User user = (User) session.getAttribute("user");
        //有值说明登录过 返回true否则返回false
        return user == null ? false : true;
    }

    @RequestMapping("/currentuser")
    public User currentUser(HttpSession session) {
        //去除登陆时保存的User
        User user = (User) session.getAttribute("user");
        return user;
    }

    @RequestMapping("/logout")
    public void logout(HttpSession session) {
        //从session中删除user;
        session.removeAttribute("user");
    }
}
