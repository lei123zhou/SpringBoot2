package cn.tedu.boot24.controller;

import cn.tedu.boot24.entity.Emp;
import cn.tedu.boot24.utils.DBUtils;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

@RestController
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

    @RequestMapping("/update")
    public void update(Emp emp,HttpServletResponse response) throws IOException {
        System.out.println("emp = " + emp);
        //获取连接
        try (Connection conn = DBUtils.getCon()){
            String sql = "update emp set name=?,salary=?,job=? where id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,emp.getName());
            ps.setInt(2,emp.getSalary());
            ps.setString(3,emp.getJob());
            ps.setInt(4,emp.getId());
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //重定向到列表页面
        response.sendRedirect("/select");
    }

    @RequestMapping("/select")
    public String select() throws IOException {
        ArrayList<Emp> list = new ArrayList<Emp>();
        try (Connection conn = DBUtils.getCon();) {
            String sql = "select * from emp";
            Statement t = conn.createStatement();
            ResultSet rs = t.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String job = rs.getString(3);
                int salary = rs.getInt(4);
                Emp emp = new Emp();
                emp.setId(id);
                emp.setName(name);
                emp.setSalary(salary);
                emp.setJob(job);
                list.add(emp);
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
    public void delete(int id,HttpServletResponse response) throws IOException {
        try(Connection conn=DBUtils.getCon();){
            String sql = "delete from emp where id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/select");
    }
}
