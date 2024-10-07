package com.zerobase.fastlms.components;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.web.embedded.TomcatVirtualThreadsWebServerFactoryCustomizer;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

// 제로베이스에 처음 접속했을때 해당 이메일로 정해진 제목, 내용의 메일이 보내진다.
@RequiredArgsConstructor
@Component
public class MailComponents {

    private final JavaMailSender javaMailSender;


    // controller에서 보내진 파라미터를 가지고 실제 메일을 보내는 함수.
    public boolean sendMail(String mail, String subject, String text){

        boolean result = false;

        MimeMessagePreparator msg = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                mimeMessageHelper.setTo(mail);
                mimeMessageHelper.setSubject((subject));
                mimeMessageHelper.setText(text, true);
            }
        };
        try {
            javaMailSender.send(msg);
            result = true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return result;
    }

}
