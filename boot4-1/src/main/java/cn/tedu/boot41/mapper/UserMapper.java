package cn.tedu.boot41.mapper;

import cn.tedu.boot41.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select id,username,password from user where username=#{username}")
    User selectByUserName(String username);
}
