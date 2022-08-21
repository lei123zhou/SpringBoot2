package com.example.boot21.controller;

import com.example.boot21.entity.User;
import com.example.boot21.utils.DBUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.*;

@Controller
public class UserController {
    @RequestMapping("/reg")
    @ResponseBody
    public String reg(User user) {
        System.out.println("user = " + user);
        //获取连接
        try (Connection conn = DBUtils.getCon();) {
            String sql = "insert into user values(null,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getNick());
            ps.setInt(4, user.getAge());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "注册成功";
    }

    @RequestMapping("/login")
    @ResponseBody
    public String login(User user){
        System.out.println("user = " + user);
        //获取连接
        try (Connection conn = DBUtils.getCon()){
            //通过用户名查询对应的密码
            String sql = "select password from user where username=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,user.getUsername());
            ResultSet rs = ps.executeQuery();
            if (rs.next()){//判断是否有查询到数据 进入方法说明查询到了用户
                String pw = rs.getString(1);
                //pw代表数据库里面的密码 user.getPassword()这是用户输入的
                if (pw.equals(user.getPassword())){
                    return "登录成功!";
                }
                return "密码错误!";
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "用户名不存在!";
    }
}
