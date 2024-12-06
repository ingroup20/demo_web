package com.t1.demo_web_springboot.mail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class JavaMailService {

    private final JavaMailSenderImpl javaMailSender;

    public JavaMailService(JavaMailSenderImpl javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Value("${spring.mail.from}")
    private String from;

    public void sendMail(String to, String from, Object content) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, false, "UTF-8");
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject("信件主旨");
            mimeMessageHelper.setText("<html><body>" +
                            "<h3> 主旨 &lt;測試信件&gt; </h3>" +
                            "<div>內容：" + content +"</div>"+
                            "</body></html>", true);
            javaMailSender.send(message);

        }catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException("寄信失敗！");
        }
    }
}
