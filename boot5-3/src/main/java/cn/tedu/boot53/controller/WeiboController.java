package cn.tedu.boot53.controller;

import cn.tedu.boot53.entity.User;
import cn.tedu.boot53.entity.Weibo;
import cn.tedu.boot53.mapper.WeiboMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
public class WeiboController {
    @Autowired
    WeiboMapper weiboMapper;

    @RequestMapping("/send")
    public void send(Weibo weibo, MultipartFile file, HttpSession session) throws IOException {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return;
        }
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUID.randomUUID() + suffix;
        String dirpath = "E:/upload";
        File dirFile = new File(dirpath);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        String filePath = dirpath + "/" + fileName;
        file.transferTo(new File(filePath));
        weibo.setUserid(user.getId());
        weibo.setAuthor(user.getNick());
        weibo.setUrl(fileName);
        weiboMapper.insert(weibo);
    }

    @RequestMapping("/selectweibo")
    public List<Weibo> selectAll() {
        return weiboMapper.selectAll();
    }
}
