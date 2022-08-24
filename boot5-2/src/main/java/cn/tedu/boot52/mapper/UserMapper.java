package cn.tedu.boot52.mapper;

import cn.tedu.boot52.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("insert into user values(null,#{username},#{password},#{nick},null)")
    public void insert(User user);

    @Select("select id,username,password,nick from user where username=#{username}")
    User selectByUsername(String username);

}
