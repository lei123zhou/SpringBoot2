package cn.tedu.boot52.mapper;

import cn.tedu.boot52.entity.Weibo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface WeiboMapper {
    @Insert("insert into weibo values(null,#{content},#{author},#{url},#{created},#{userid})")
    void insert(Weibo weibo);

    @Select("select id,content,author,url from weibo order by created desc")
    List<Weibo> selectAll();
}
