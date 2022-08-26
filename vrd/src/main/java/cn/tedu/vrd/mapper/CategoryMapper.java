package cn.tedu.vrd.mapper;

import cn.tedu.vrd.entity.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {
    @Insert("insert into category values(null,#{name})")
    public void insert(String name);

    @Select("select id,name from category")
    List<Category> selectAll();
}
