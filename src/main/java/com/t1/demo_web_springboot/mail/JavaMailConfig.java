package com.t1.demo_web_springboot.mail;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class JavaMailConfig {

    protected static Logger logger = LogManager.getLogger(JavaMailConfig.class);

    @Autowired
    private Environment env;

    @Bean
    public JavaMailSenderImpl javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        //get from Properties
        String host = env.getProperty("spring.mail.host");
        Integer port = Integer.parseInt(env.getProperty("spring.mail.port"));
        String userName = env.getProperty("spring.mail.username");
        String password = env.getProperty("spring.mail.password");
        String transportProtocol = env.getProperty("spring.mail.protocol") == null || env.getProperty("spring.mail.protocol").equals("") ? "smtp"
                : env.getProperty("spring.mail.protocol");
        String smtpAuth = env.getProperty("spring.mail.smtp.auth");
        String starttlsEnable = env.getProperty("spring.mail.smtp.starttls.enable");

        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(userName);
        mailSender.setPassword(password);
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", transportProtocol);
        props.put("mail.smtp.auth", smtpAuth);
        props.put("mail.smtp.starttls.enable", starttlsEnable);

        logger.info(
                "mail.host({}) , mail.port({}) , mail.username({}) , mail.properties.mail.transport.protocol({}) , mail.properties.mail.smtp.auth({}) , mail.properties.mail.smtp.starttls.enable({})"
                , host, port, userName, transportProtocol, smtpAuth, starttlsEnable);

        return mailSender;
    }

}
