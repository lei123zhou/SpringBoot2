package cn.tedu.boot32;

import cn.tedu.boot32.Entity.Emp;
import cn.tedu.boot32.Entity.Item;
import cn.tedu.boot32.mapper.EmpMapper;
import cn.tedu.boot32.mapper.ItemMapper;
import org.apache.ibatis.annotations.Mapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Boot32ApplicationTests {
    @Autowired
    EmpMapper mapper;

    @Test
    void insert() {
        Emp emp = new Emp();
        emp.setName("123");
        emp.setType("123");
        mapper.insert(emp);
    }

    @Test
    void delete() {
        mapper.delete(1);
    }

    @Test
    void update() {
        Emp emp = new Emp();
        emp.setId(2);
        emp.setName("改名字");
        emp.setType("修改名字完毕");
        mapper.Update(emp);
    }

    @Test
    void select() {
        List<Emp> list = mapper.listAll();
        System.out.println(list);
    }

    //把ItemMapper加载进来
    @Autowired
    ItemMapper itemMapper;

    @Test
    void insertItem() {
        Item item = new Item();
        item.setTitle("realme手机");
        item.setPrice(2000);
        item.setCategoryId(5);
        item.setId(10);
        itemMapper.insert(item);
    }

    @Test
    void selectItem() {
        List<Item> list = itemMapper.selectAll();
        System.out.println(list);
    }
}
