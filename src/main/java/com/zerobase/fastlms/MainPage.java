package com.zerobase.fastlms;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// MainPage 클래스를 만든 목적
// 매핑하기 위해서
// 주소와(논리적인주소인터넷주소) 물리적인파일(프로그래밍 해야 하니까) 매핑
@RestController
public class MainPage {

    @RequestMapping("/")
    public String index(){

        return "Index Page";
    }

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }
}
