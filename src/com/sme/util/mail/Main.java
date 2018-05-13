package com.sme.util.mail;

import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2016/6/3.
 * User : yaoping
 * Date:2016/6/3
 * Time:11:25
 * Version : 1.0
 */
public class Main {
    public static void main(String[] args) throws UnsupportedEncodingException {
        //这个类主要是设置邮件
        MailSenderInfo mailInfo = new MailSenderInfo();
        //服务器端口
//        mailInfo.setMailServerHost("smtp.126.com");
        mailInfo.setMailServerHost("220.181.15.111");
        //或者是通过qq邮箱发送
//        mailInfo.setMailServerHost("smtp.qq.com");
        mailInfo.setMailServerPort("25");
        mailInfo.setValidate(true);
        //您的邮箱用户名
        mailInfo.setUserName("yp104@126.com");
        //您的邮箱密码
        mailInfo.setPassword("123454321asdf1");
        //发送邮件源地址
        mailInfo.setFromAddress("yp104@126.com");
        //发送邮件目的地址
        mailInfo.setToAddress("yp104@126.com");
        //主题
        mailInfo.setSubject("汇教验证码");
        //内容
        mailInfo.setContent("您的汇教验证码为：12314516，请在当天完成激活。");
        //这个类主要来发送邮件
        SimpleMailSender sms = new SimpleMailSender();
        sms.sendTextMail(mailInfo);//发送文体格式
//        sms.sendHtmlMail(mailInfo);//发送html格式
//        sms.sendAttachMail(mailInfo);//发送带附件格式
    }
}
