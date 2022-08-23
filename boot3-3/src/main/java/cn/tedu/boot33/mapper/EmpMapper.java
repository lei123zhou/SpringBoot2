package cn.tedu.boot33.mapper;

import cn.tedu.boot33.entity.Emp;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmpMapper {
    @Insert("insert into emp values(null,#{name},#{salary},#{job})")
    void insert(Emp emp);

    @Select("select id,name,salary,job from emp")
    List<Emp> selectAllEmps();

    @Delete("delete from emp where id=#{id}")
    void delete(int id);

    @Update("update emp set name=#{name},salary=#{salary},job=#{job} where id=#{id}")
    void update(Emp emp);


}
