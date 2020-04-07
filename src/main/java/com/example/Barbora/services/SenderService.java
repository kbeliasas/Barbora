package com.example.Barbora.services;

import java.util.List;

import com.example.Barbora.models.BarboraTimeModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
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
            text.append("Price = " + String.format("%.2f",time.price) + "\n");
            text.append("\n");
        });
        System.out.println(text);

        try {
            sendEmail(text.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendEmail(String text) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(mailTo);

        msg.setSubject("BARBORA naujas laikas");
        msg.setText(text);

        System.out.println("Sending email...");

        javaMailSender.send(msg);

    }

}
