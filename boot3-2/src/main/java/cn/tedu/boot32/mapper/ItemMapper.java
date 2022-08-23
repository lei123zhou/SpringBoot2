package cn.tedu.boot32.mapper;

import cn.tedu.boot32.Entity.Item;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ItemMapper {
    @Insert("insert into item values(null,#{title},#{price},+#{categoryId},#{userId})")
    void insert(Item item);

    @Select("select id,title,price,category_id,user_id from item")
    @Result(column = "category_id", property = "categoryId")
    @Result(column = "user_id", property = "userId")
    List<Item> selectAll();
}
