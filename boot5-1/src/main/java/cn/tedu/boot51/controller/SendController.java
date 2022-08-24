package cn.tedu.boot51.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class SendController {
    @RequestMapping("/send")
    public void send(MultipartFile file) throws IOException {
        //file代表上传的文件对象
        //得到上传的文件名
        String fileName = file.getOriginalFilename();
        System.out.println("文件名:" + fileName);
        //得到文件的后缀名 从最后一个 . 出现的位置 截取到最后
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        //得到唯一的文件名 UUID.randomUUID()得到一个唯一标识符
        fileName = UUID.randomUUID() + suffix;
        System.out.println(fileName);
        //准备磁盘文件路径
        String dirPath = "E:/upload";
        File dirFile = new File(dirPath);
        //判断如果文件夹不存在 创建
        if (!dirFile.exists()) {
            dirFile.mkdirs();//创建文件夹
        }
        //把上传的文件保存到这个路径下  异常抛出
        String filePath = dirPath + "/" + fileName;
        file.transferTo(new File(filePath));
        System.out.println(filePath);
    }
}
