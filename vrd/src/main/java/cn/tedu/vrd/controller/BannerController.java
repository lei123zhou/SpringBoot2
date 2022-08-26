package cn.tedu.vrd.controller;

import cn.tedu.vrd.entity.Banner;
import cn.tedu.vrd.mapper.BannerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BannerController {
    @Autowired
    BannerMapper bannerMapper;

    @RequestMapping("/banner/select")
    public List<Banner> select() {
        return bannerMapper.selectAll();
    }
}
