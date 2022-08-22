package cn.tedu.boot23.controller;


import cn.tedu.boot23.entity.Emp;
import cn.tedu.boot23.utils.DBUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

@RestController//添加此注解相当于在每一个方法上都添加了@ResponseBody
public class EmpController {
    @RequestMapping("/add")
    public void add(Emp emp, HttpServletResponse response) throws IOException {
        try (Connection conn = DBUtils.getCon();) {
            String sql = "insert into emp values(null,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, emp.getName());
            ps.setString(2, emp.getJob());
            ps.setInt(3, emp.getSalary());
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        response.sendRedirect("/index.html");
    }

    @RequestMapping("/select")
    public String select() {
        //准备一个集合
        ArrayList<Emp> list = new ArrayList<Emp>();
        try (Connection conn = DBUtils.getCon();) {
            String sql = "select id,name,salary,job from emp";
            Statement t = conn.createStatement();
            ResultSet rs = t.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int salary = rs.getInt(3);
                String job = rs.getString(4);
                Emp emp = new Emp();
                emp.setId(id);
                emp.setName(name);
                emp.setSalary(salary);
                emp.setJob(job);
                list.add(emp);//装进集合
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String html = "<table border='1'>";
        html += "<cption>员工列表</caption>";
        html += "<tr><th>Id</th><th>姓名</th><th>工资</th><th>工作</th></tr>";
        //遍历集合
        for (Emp e : list) {
            html += "<tr>";
            html += "<td>" + e.getId() + "</td>";
            html += "<td>" + e.getName() + "</td>";
            html += "<td>" + e.getSalary() + "</td>";
            html += "<td>" + e.getJob() + "</td>";
            html += "<td><a href='/delete?id=" + e.getId() + "'>删除</a></td>";
            html += "</tr>";
        }
        html += "</table>";
        return html;
    }

    @RequestMapping("/delete")
    public void delete(int id, HttpServletResponse response) throws IOException {
        try (Connection conn = DBUtils.getCon();) {
            System.out.println(id);
            String sql = " delete from emp where id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //重新向:通知客户端向指定的地址再次发送
        //内容执行原理:此方法执行时会给客户端返回一个302状态码和请求地址
        //当客户端接收到302时会自动向该地址发出请求
        response.sendRedirect("/select");

    }

    @RequestMapping("/update")
    public void update(Emp emp, HttpServletResponse response) throws IOException {
        try (Connection conn = DBUtils.getCon();) {
            String sql = "update emp set name=?,salary=?,job=? where id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, emp.getName());
            ps.setInt(2, emp.getSalary());
            ps.setString(3, emp.getJob());
            ps.setInt(4, emp.getId());
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        response.sendRedirect("/select");
    }
}
