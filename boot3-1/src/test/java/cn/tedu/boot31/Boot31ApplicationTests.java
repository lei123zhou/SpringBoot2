package cn.tedu.boot31;

import cn.tedu.boot31.entity.Emp;
import cn.tedu.boot31.mapper.EmpMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Boot31ApplicationTests {
    //自动装配,Spring框架会自动帮助我们对该接口进行实例化操作
    @Autowired
    EmpMapper mapper;

    @Test
    void contextLoads() {
        System.out.println("xxx");
        Emp emp = new Emp();
        emp.setName("司马懿");
        emp.setSalary(500);
        emp.setJob("军师");
        mapper.insert(emp);
        System.out.println("执行完成!");
    }

    @Test
    void select() {
        List<Emp> list = mapper.selectAll();
        System.out.println(list);
    }

    @Test
    void delete() {
        int row = mapper.delete(4);
        System.out.println("删除完成" + row);
    }

    @Test
    void update() {
        Emp emp = new Emp();
        emp.setId(7);
        emp.setName("诸葛亮");
        emp.setSalary(800);
        emp.setJob("水水水");
        mapper.Update(emp);
        System.out.println("修改完成");
    }
}
