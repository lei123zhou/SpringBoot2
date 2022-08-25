package cn.tedu.boot54.mapper;

import cn.tedu.boot54.entity.Weibo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface WeiboMapper {
    @Insert("insert into weibo values(null,#{content},#{author},#{url},#{created},#{userid})")
    void insertWeibo(Weibo weibo);

    @Select("select * from weibo")
    List<Weibo> selectWeibo();
}
