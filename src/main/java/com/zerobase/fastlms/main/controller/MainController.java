package com.zerobase.fastlms.main.controller;


import com.zerobase.fastlms.components.MailComponents;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.io.PrintWriter;
// 웹에서 들어오는 요청을 받아주는 컨트롤러 클래스
@RequiredArgsConstructor
@Controller
public class MainController {

    private final MailComponents mailComponents;

    // 홈페이지로 접속햇을때 email로 subject, text로 구성된 메일이 보내지게끔 하는 함수이다.
    @RequestMapping("/")
    public String index(){

        String email="pronova@naver.com";
        String subject ="안녕하세요. 제로베이스입니다.";
        String text = "<p>안녕하세요.</p><p>반갑습니다.</p>";

        mailComponents.sendMail(email, subject, text);

        return "index";
    }

    // /hello 경로로 들어왔을때 요청을 처리해주는 함수이다.
    @RequestMapping("/hello")
    public void hello(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter = response.getWriter();

        String msg = "<html>" +
                "<head>" +
                "<meta charset=\"UTF-8\">" +
        " </head>" +
                "<body>" +
                "<p>hello</p> <p>fastlms website!!!</p>" +
                "<p> 안녕하세요!!! ===></p>" +
                "</body>" +
                "</html>";


        printWriter.write(msg);


    }
}
