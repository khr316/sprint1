package com.project.sprint1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    // 임시 비밀번호 이메일 발송 메서드
    public void sendPasswordResetEmail(String recipientEmail, String tempPassword) {
        // SimpleMailMessage 객체 생성
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientEmail); // 수신자 이메일 설정
        message.setSubject("임시 비밀번호 발송"); // 이메일 제목 설정
        message.setText("임시 비밀번호: " + tempPassword); // 이메일 내용 설정

        try {
            javaMailSender.send(message);
            System.out.println("이메일을 성공적으로 발송했습니다.");
        } catch (MailException e) {
            e.printStackTrace();
            System.out.println("이메일 발송 중 오류가 발생했습니다: " + e.getMessage());
        }

    }
}
