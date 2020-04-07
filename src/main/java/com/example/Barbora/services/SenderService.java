package com.example.Barbora.services;

import java.util.List;

import com.example.Barbora.models.BarboraTimeModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SenderService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${mail.send.to}")
    private String mailTo;

    public void Send(List<BarboraTimeModel> list) {
        StringBuilder text = new StringBuilder();
        list.forEach(time -> {
            text.append("Day = " + time.day + "\n");
            text.append("Time = " + time.time + "\n");
            text.append("Price = " + String.format("%.2f",time.price) + "€" + "\n");
            text.append("\n");
        });
        try {
            sendEmail(text.toString());
        }
        catch (Exception e) {
            log.error("Failed to send email", e);
        }
    }

    private void sendEmail(String text) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(mailTo);

        msg.setSubject("BARBORA naujas laikas");
        msg.setText(text);

        log.info("Sending email...");

        javaMailSender.send(msg);

    }

}
