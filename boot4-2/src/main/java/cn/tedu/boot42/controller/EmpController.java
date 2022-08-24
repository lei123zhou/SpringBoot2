package cn.tedu.boot42.controller;

import cn.tedu.boot42.entity.Emp;
import cn.tedu.boot42.mapper.EmpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmpController {
    @Autowired(required = false)
    EmpMapper empMapper;

    @RequestMapping("/add")
    public void add(Emp emp) {
        empMapper.insertEmp(emp);
        System.out.println("添加完成");
    }

    @RequestMapping("/select")
    public List<Emp> select() {
        //直接把数据库里面查到的数据 直接响应给客户端
        return empMapper.selectAllEmp();
        /*此时客户端最终响应的数据也是一个字符串,SpringMVC框架当看到返回值为List集合
         * 或对象形式,会自动将其转成JSON格式的字符串*/
    }

    @RequestMapping("/delete")
    public void delete(int id) {
        empMapper.deleteEmp(id);
        System.out.println("删除完成");
    }

    @RequestMapping("/selectbyid")
    public Emp selectById(int id) {
//通过员工ID查询
        return empMapper.selectById(id);
    }

    @RequestMapping("/update")
    public void update(Emp emp) {
        empMapper.updateEmp(emp);
    }
}
