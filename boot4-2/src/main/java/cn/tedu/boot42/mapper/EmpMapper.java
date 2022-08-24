package cn.tedu.boot42.mapper;

import cn.tedu.boot42.entity.Emp;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmpMapper {
    @Insert("insert into emp values(null,#{name},#{job},#{salary})")
    void insertEmp(Emp emp);

    @Delete("delete from emp where id=#{id}")
    void deleteEmp(int id);

    @Update("update emp set name=#{name},job=#{job},salary=#{salary} where id=#{id}")
    void updateEmp(Emp emp);

    @Select("select id,name,job,salary from emp")
    List<Emp> selectAllEmp();

    @Select("select id,name,salary,job from emp where id=#{id}")
    Emp selectById(int id);
}
