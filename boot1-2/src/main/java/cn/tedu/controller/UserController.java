package cn.tedu.controller;

import cn.tedu.Entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("user")
public class UserController {
    @RequestMapping("/reg")
    @ResponseBody//添加此注解可以通过返回值的方式给客户端响应数据(Spring MVC提供)
    public String reg(User user) {
        System.out.println("My Name is " + user.getUsername() + "注册已经成功");
        return "注册已经成功";
    }
}
