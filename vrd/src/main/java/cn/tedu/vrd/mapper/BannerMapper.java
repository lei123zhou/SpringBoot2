package cn.tedu.vrd.mapper;

import cn.tedu.vrd.entity.Banner;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BannerMapper {
    @Select("select id,url from banner")
    List<Banner> selectAll();
}
