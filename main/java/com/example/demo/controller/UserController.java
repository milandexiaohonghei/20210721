package com.example.demo.controller;

import com.example.demo.config.AppFinal;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.ArticleInfo;
import com.example.demo.model.User;
import com.example.demo.tool.HtmlText;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

//@RestController
@Controller
@RequestMapping("/login")
@Slf4j
public class UserController {

    //private Logger logger =  LoggerFactory.getLogger(UserController.class);
    @Resource
    private UserMapper userMapper;

    @Value("${myimgpath}")
    private String imgpath;


    @RequestMapping("/getalist")
    @ResponseBody
    public Object getFullUser(int uid) {
        User user = userMapper.getFullUser(uid);
        List<ArticleInfo> list = user.getAlist();
        for (ArticleInfo i:list) {
            // 去除html标签
            String desc = HtmlText.Html2Text(i.getContent());
            // 截取字符串
            if (desc.length() > 64) {
                desc = desc.substring(0, 64) + "...";
            }
            i.setContent(desc);

        }
        user.setAlist(list);
        return user;
    }

    @RequestMapping("/login2")
    @ResponseBody
    public Object login(User user, HttpServletRequest request){
        User user2 = userMapper.getUserByNameAndPassword(user.getUsername(),
                user.getPassword());
        if (user2 == null) {
            user2 = user;
        } else {
            // 登录成功，设置
            HttpSession session = request.getSession();
            // 存放到 Session
            session.setAttribute(AppFinal.USERINFO_SESSIONKEY, user2);
        }
        return user2;
    }

    @RequestMapping("/reg")
    public String regin(String username, String password, @RequestPart MultipartFile file) throws IOException {

        // 1.动态获取当前项目的路径
        //String path = ClassUtils.getDefaultClassLoader().getResource("static").getPath();
        String path = imgpath;
        path +=AppFinal.IMAGE_PATH;
        log.warn("path:" + path);
        String fileType = file.getOriginalFilename();
        fileType = fileType.substring(fileType.lastIndexOf("."));
        String filename = UUID.randomUUID().toString() + fileType;

        // 2.文件名（全局唯一id【uuid】）
        file.transferTo(new File(path + filename));
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setPhoto(AppFinal.IMAGE_PATH + filename);
        int result = userMapper.addUser(user);
        if(result > 0){
            return "redirect:/reg_success.html";
        }else{
            return "redirect:/reg_err.html";
        }

    }

    @RequestMapping("/test")
    @ResponseBody
    public Object test() {
        //int i = 10 / 0;
        String username = null;
        System.out.println(username.hashCode());
        return "Hello";
    }

}
