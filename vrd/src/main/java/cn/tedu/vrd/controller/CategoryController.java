package cn.tedu.vrd.controller;

import cn.tedu.vrd.entity.Category;
import cn.tedu.vrd.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    CategoryMapper categoryMapper;

    @RequestMapping("/category/select")
    public List<Category> select() {
        return categoryMapper.selectAll();
    }

}
