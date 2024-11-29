package com.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.school.serviceInterface.EmailServiceInterface;

@Service
public class EmailService implements EmailServiceInterface {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmail(String to, String subject, String body, String cc) throws Exception {
        try {
        	if (to == null || to.isEmpty()) {
        		throw new Exception("To is empty");
        	}
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            if(cc != null && !cc.isEmpty()) {
            	message.setCc(cc);
            }
            message.setSubject(subject);
            message.setText(body);
            javaMailSender.send(message);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
