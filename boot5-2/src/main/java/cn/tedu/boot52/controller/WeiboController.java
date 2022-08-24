package cn.tedu.boot52.controller;

import cn.tedu.boot52.entity.User;
import cn.tedu.boot52.entity.Weibo;
import cn.tedu.boot52.mapper.WeiboMapper;
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
    public void send(Weibo weibo, MultipartFile file, HttpSession session) throws IOException {
        //从session对象中取出登录成功保存的用户对象
        User user = (User) session.getAttribute("user");
        if (user == null) {
            System.out.println("请先登录");
            return;
        }
        System.out.println("weibo = " + weibo + ", file = " + file);
        //处理文件上传
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUID.randomUUID() + suffix;
        String dirpath = "E:/upload";
        File dirFile = new File(dirpath);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        String filePath = dirpath + "/" + fileName;
        //保存上传文件 异常抛出
        file.transferTo(new File(filePath));
        //把图片路径 添加到weibo对象中
        weibo.setUrl(fileName);
        //new Date创建出来的日期表示的是当前时间
        weibo.setCreated(new Date());
        //author和userid 如何得到呢
        weibo.setUserid(user.getId());
        weibo.setAuthor(user.getNick());
        weiboMapper.insert(weibo);
    }
   @RequestMapping("/selectweibo")
    public List<Weibo> selectWeibo(){
        return weiboMapper.selectAll();
   }
}
