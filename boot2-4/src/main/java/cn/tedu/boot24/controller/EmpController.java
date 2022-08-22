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
            String sql = "insert into values(null,?,?,?)";
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
    public void update(Emp emp, HttpServletResponse response) throws IOException {
        try (Connection conn = DBUtils.getCon();) {
            String sql = "update emp set name=?,job=?,salarty=? where id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, emp.getName());
            ps.setString(2, emp.getJob());
            ps.setInt(3, emp.getSalary());
            ps.setInt(4, emp.getId());
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        response.sendRedirect("/select.html");
    }

    @RequestMapping("/select")
    public void select(HttpServletResponse response) throws IOException {
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
        response.sendRedirect("/select.html");
    }
}
