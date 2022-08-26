package cn.tedu.boot54.controller;

import cn.tedu.boot54.entity.User;
import cn.tedu.boot54.entity.Weibo;
import cn.tedu.boot54.mapper.WeiboMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
public class WeiboController {
    @Autowired
    WeiboMapper weiboMapper;

    @RequestMapping("/send")
    public void send(Weibo weibo, HttpSession session, MultipartFile file) throws IOException {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return;
        }
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUID.randomUUID() + suffix;
        String dirPath = "E:/upload";
        File dirFile = new File(dirPath);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        String filePath = dirPath + "/" + fileName;
        file.transferTo(new File(filePath));
        weibo.setId(user.getId());
        weibo.setAuthor(user.getNick());
        weibo.setCreated(new Date());
        weibo.setUrl(fileName);
        weiboMapper.insertWeibo(weibo);
    }

    @RequestMapping("/selectweibo")
    public List<Weibo> selectAll() {
        return weiboMapper.selectWeibo();
    }
}
