package com.zh;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

@SpringBootTest
class Springboot09TaskApplicationTests {

    @Autowired
    private JavaMailSenderImpl mailSender;

    @Test
    void url() {
        //必应每日一图接口
        String bing = "https://cn.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1";
        String str = "";
        try {
            URL url = new URL(bing);
            URLConnection conn = url.openConnection();
            //字符缓冲输入流
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));//转码。
            String line;
            while ((line = br.readLine()) != null){
                str += line;
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(str);
        
    }

    @Test
    void contextLoads() {

        //创建一个简单的邮件
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        //标题
        mailMessage.setSubject("测试邮件");
        //内容
        mailMessage.setText("测试邮件内容文本");

        //收件人
        mailMessage.setTo("3519422316@qq.com");
        //发件人
        mailMessage.setFrom("1425279634@qq.com");

        mailSender.send(mailMessage);
    }

    @Test
    void contextLoads02() throws MessagingException {

        //创建一个复杂的邮件
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        //组装  multipart：是否支持多文件
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true,"utf-8");

        helper.setSubject("你好我是标题");
        //开启html解析
        helper.setText("<h1 style='color:red'>你好我是文本内容</h1>",true);


        //附件
        helper.addAttachment("eye.png",new File("C:\\Users\\Beloved\\Desktop\\answer_ssm\\img\\eye.png"));
        helper.addAttachment("editor.md.zip",new File("C:\\Users\\Beloved\\Desktop\\editor.md-master.zip"));

        //收件人
        helper.setTo("1425279634@qq.com");
        //发件人
        helper.setFrom("1425279634@qq.com");

        mailSender.send(mimeMessage);
    }
}














































