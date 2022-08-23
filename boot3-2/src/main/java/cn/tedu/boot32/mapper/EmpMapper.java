package cn.tedu.boot32.mapper;

import cn.tedu.boot32.Entity.Emp;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmpMapper {
    @Insert("insert into hero values(null,#{name},#{type})")
    void insert(Emp emp);

    @Delete("delete from hero where id=#{id}")
    void delete(int id);

    @Update("update hero set name=#{name},type=#{type} where id=#{id}")
    void Update(Emp emp);

    @Select("select id,name,type from hero")
    List<Emp> listAll();
}
