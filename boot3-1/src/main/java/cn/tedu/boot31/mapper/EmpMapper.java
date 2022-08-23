package cn.tedu.boot31.mapper;

import cn.tedu.boot31.entity.Emp;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper//告诉编译器当前接口是一个映射接口为Mybatis框架服务
public interface EmpMapper {
    /* #{变量}会自动从方法中的参数列表中找到对应的数据,如果找不到
     * 会自动查看已有对象里面是否包含同名属性*/
    @Insert("insert into emp values(null,#{name},#{salary},#{job})")
    void insert(Emp emp);

    //MyBatis框架会自动根据上面两行的代码生成方法的实现(里面写的就是JDB的代码)
    @Select("select id,name,salary,job from emp")
    List<Emp> selectAll();

    @Delete("delete from emp where id=#{id}")
    int delete(int id);

    @Update("update emp set name=#{name},job=#{job},salary=#{salary} where id=#{id}")
    void Update(Emp emp);

}
