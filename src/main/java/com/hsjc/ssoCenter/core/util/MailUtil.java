package com.hsjc.ssoCenter.core.util;

import com.hsjc.ssoCenter.core.constant.MailConstant;
import org.apache.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.*;
import java.util.Properties;

/**
 * @author : zga
 * @date : 2015-12-03
 *
 * Email 工具类
 */
public class MailUtil {
    private static final Logger logger = Logger.getLogger(MailUtil.class);

    /**
     * @author : zga
     * @desc : 发送Email
     * @param subject
     * @param content
     * @return
     */
    public static boolean sendMail(String subject, String content, String to){
        Properties props = new Properties();
        String from = MailConstant.MAIL_FROM;// 发件人
        String host = MailConstant.MAIL_HOST;// smtp主机

        final String username = MailConstant.MAIL_USERNAME;
        final String password = MailConstant.MAIL_PASSWORD;

        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", false);

        Session session = Session.getInstance(props);
        Transport transport = null;
        try {
            transport = session.getTransport("smtp");
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from, "hsjc"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setContent(content, "text/html;charset = UTF-8");

            message.saveChanges();

            transport.connect(host, username,  password);
            transport.sendMessage(message, message.getAllRecipients());

        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            if(transport != null){
                try {
                    transport.close();
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        }
        logger.debug("Email发送完毕,发送内容为:"+content);
        return false;
    }

    public static void main(String[] args) {
        /*String host = "smtp.exmail.qq.com";
        String mail_subject = "test";
        String mail_body = "test";
        String mail_head_name = "title";
        String mail_head_value = "en";
        String mail_from = "service@yizhaoshang.com";
        String mail_to = "617542946@qq.com";
        String personalName = "yizhaoshang";
        String username = "service@yizhaoshang.com";
        String password = "yizhaoshang123";
        try{
            Properties props=new Properties();
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.auth", "true");
            Session mailSession = Session.getInstance(props);
            mailSession.setDebug(true);
            MimeMessage message = new MimeMessage(mailSession);
            message.setSubject(mail_subject);
            message.setText(mail_body);
            message.setHeader(mail_head_name, mail_head_value);
            message.setSentDate(new Date());
            InternetAddress address = new InternetAddress(mail_from, personalName);
            message.setFrom(address);
            InternetAddress toAddress = new InternetAddress(mail_to);
            message.addRecipient(Message.RecipientType.TO, toAddress);
            Transport transport = null;
            transport = mailSession.getTransport("smtp");

            message.saveChanges();
            transport.connect(host, username, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

            System.out.println("send success!");
        }catch (Exception ex){
            ex.printStackTrace();
        }*/

        sendMail("测试代码", "测试中.......", "617542946@qq.com");
    }
}
