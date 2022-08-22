package com.example.boot22.cn.tedu.controller;

import com.example.boot22.Utils.DBUtils;
import com.example.boot22.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Controller
public class UserController {
    @RequestMapping("/reg")
    @ResponseBody
    public String reg(User user) {
        try (Connection conn = DBUtils.getCon();) {
            String sql = "insert into user values(null,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getNick());
            ps.setInt(4, user.getAge());
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "注册成功";
    }

    @RequestMapping("/login")
    @ResponseBody
    public String login(User user) {
        try (Connection conn = DBUtils.getCon();) {
            String sql = "select password from user where username=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                //如果rs.next存在下一个表示查到了这个账户对应的密码,下一步查验数据库里面的密码和用户输出的密码是否一样
                String password = rs.getString(1);
                if (password.equals(user.getPassword())) {
                    return "登陆成功";
                }
                return "密码错误";
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return "用户名不存在";
    }
}
