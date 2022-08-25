package cn.tedu.boot54.mapper;

import cn.tedu.boot54.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("insert into user values(null,#{username},#{password},#{url},null)")
    void insertUser(User user);

    @Select("select * from user where username=#{username}")
    User selectByUsername(String username);
}
