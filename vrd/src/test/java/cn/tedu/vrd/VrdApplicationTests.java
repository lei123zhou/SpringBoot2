package cn.tedu.vrd;

import cn.tedu.vrd.entity.User;
import cn.tedu.vrd.mapper.BannerMapper;
import cn.tedu.vrd.mapper.CategoryMapper;
import cn.tedu.vrd.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class VrdApplicationTests {
    @Autowired
    UserMapper userMapper;


    @Test
    void contextLoads() {
        User u = userMapper.selectUser("admin");
        System.out.println(u);
        System.out.println("执行完毕");
    }

    @Autowired
    CategoryMapper categoryMapper;

    @Test
    void test() {
        System.out.println(categoryMapper.selectAll());
    }
    @Autowired
    BannerMapper bannerMapper;
    @Test
    void test2() {
        System.out.println(bannerMapper.selectAll());
    }
}
