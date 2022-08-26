package cn.tedu.vrd.mapper;

import cn.tedu.vrd.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select id,username,password from user where username=#{username}")
    User selectUser(String username);
}
