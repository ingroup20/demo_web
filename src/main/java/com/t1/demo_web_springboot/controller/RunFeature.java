package com.t1.demo_web_springboot.controller;

import com.t1.demo_web_springboot.mail.JavaMailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RunFeature {

    @Value("${spring.mail.from}")
    private String from;

    @Value("${spring.mail.to}")
    private String to;


    JavaMailService javaMailService;

    RunFeature(JavaMailService javaMailService) {
        this.javaMailService = javaMailService;
    }

    @PostMapping("/sendMail")
    public String sendMail(@RequestParam String content) {
        try {
            javaMailService.sendMail(to, from, content);
            return "sendMail success";
        } catch (Exception e) {
            return "sendMail failed: " + e.getMessage();
        }
    }
}
