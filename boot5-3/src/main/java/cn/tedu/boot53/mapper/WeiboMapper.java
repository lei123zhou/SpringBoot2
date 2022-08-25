package cn.tedu.boot53.mapper;

import cn.tedu.boot53.entity.Weibo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface WeiboMapper {
    @Insert("insert into weibo values(null,#{content},#{author},#{url},#{created},#{userid})")
    void insert(Weibo weibo);

    @Select("select * from weibo")
    List<Weibo> selectAll();
}
