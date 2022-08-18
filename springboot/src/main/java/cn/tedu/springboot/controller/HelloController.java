package cn.tedu.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller//告诉编译器当前类为一个控制器
public class HelloController {
    @RequestMapping("/hello")
    public void hello(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取传递过来的参数
        String info = request.getParameter("info");
//request表示请求对象
        //response表示响应对象
        System.out.println("服务器接收到了请求");
        //设置响应类型
        response.setContentType("text/html;charset=utf-8");
        //获取输出对象
        PrintWriter pw = response.getWriter();
        //给客户端输出数据
        pw.print("恭喜你测试成功 info=" + info);
        //关闭资源
        pw.close();
    }
}
