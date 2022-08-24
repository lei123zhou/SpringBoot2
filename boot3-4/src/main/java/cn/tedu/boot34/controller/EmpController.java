package cn.tedu.boot34.controller;

import cn.tedu.boot34.entity.Emp;
import cn.tedu.boot34.mapper.EmpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class EmpController {
    @Autowired
    EmpMapper empMapper;

    @RequestMapping("/add")
    public String add(Emp emp) {
        empMapper.insert(emp);
        return "添加员工完成";
    }

    @RequestMapping("/delete")
    public void delete(int id, HttpServletResponse response) throws IOException {
        empMapper.delete(id);
        response.sendRedirect("/select");
    }

    @RequestMapping("/update")
    public void update(Emp emp, HttpServletResponse response) throws IOException {
        empMapper.update(emp);
        response.sendRedirect("/select");
    }

    @RequestMapping("/select")
    public String select(HttpServletResponse response) {
        List<Emp> empList = empMapper.selectAll();
        //将集合里面的数据装进html标签中 响应给客户端
        String html = "<table border='1'>";
        html += "<cption>员工列表</caption>";
        html += "<tr><th>Id</th><th>姓名</th><th>工资</th><th>工作</th></tr>";
        for (Emp emp : empList) {
            html += "<tr>";
            html += "<td>" + emp.getId() + "</td>";
            html += "<td>" + emp.getName() + "</td>";
            html += "<td>" + emp.getSalary() + "</td>";
            html += "<td>" + emp.getJob() + "</td>";
            html += "<td><a href='/delete?id=" + emp.getId() + "'>删除</a></td>";
            html += "</tr>";
        }
        html += "</table>";
        return html;
    }
}
