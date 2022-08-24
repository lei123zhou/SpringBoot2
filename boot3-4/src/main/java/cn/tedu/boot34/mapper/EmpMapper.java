package cn.tedu.boot34.mapper;

import cn.tedu.boot34.entity.Emp;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmpMapper {
    @Insert(("insert into emp values(null,#{name},#{job},#{salary})"))
    void insert(Emp emp);

    @Delete("delete from emp where id=#{id}")
    void delete(int id);

    @Update("update emp set name=#{name},job=#{job},salary=#{salary} where id=#{id}")
    void update(Emp emp);

    @Select("select id,name,job,salary from emp")
    List<Emp> selectAll();

}
