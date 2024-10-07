package com.zerobase.fastlms.member.controller;

import com.zerobase.fastlms.member.entity.Member;
import com.zerobase.fastlms.member.model.MemberInput;
import com.zerobase.fastlms.member.model.ResetPasswordInput;
import com.zerobase.fastlms.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

//회원 관련 웹에서 들어오는 요청을 매핑된 경로에 따라 적절한 함수가 처리해주는 클래스이다.
@RequiredArgsConstructor
@Controller
public class MemberController {


    private final MemberService memberService;

    // 로그인 페이지로 이동한다.
    @RequestMapping("/member/login")
    public String login() {
        return "member/login";
    }

    // 비밀번호 찾기 페이지로 이동한다.
    @GetMapping("/member/find-password")
    public String findPassword(){
        return "member/find_password";
    }

    // 비밀번호 찾기 페이지에서 양식을 입력하고 비밀번호 초기화 요청을 누르면 MemberController의 findPasswordSubmit메소드가 처리한다.
    @PostMapping("/member/find-password")
    public String findPasswordSubmit(Model model, ResetPasswordInput parameter){

        boolean result = false;
        try {
            result = memberService.sendResetPassword(parameter);
        }catch (Exception e){

        }
        model.addAttribute("result", result);

        return "member/find_password_result";
    }

    // 회원가입 페이지로 이동한다.
    @GetMapping("/member/register")
    public String register() {


        return "member/register";
    }

    // 회원가입 양식을 입력하면 MemberContorller클래스의 registerSubmit메소드가 처리한다.
    @PostMapping("/member/register")
    public String registerSubmit(Model model, HttpServletResponse response
            , MemberInput parameter) {

        System.out.println(parameter.toString());

        boolean result = memberService.register(parameter);

        model.addAttribute("result", result);

        return "member/register-complete";
    }

    // 이메일 인증을 처리하는 메소드이다.
@GetMapping("/member/email-auth")
public String emailAuth(Model model, HttpServletRequest request){

        String uuid = request.getParameter("id");
        System.out.println(uuid);

        boolean result = memberService.emailAuth(uuid);
        model.addAttribute("result", result);


    return "member/email_auth";
}

// 회원정보 페이지로 이동한다.
@GetMapping("/member/info")
public String memberInfo(){

        return "member/info";
}

// 비밀번호 초기화 페이지로 이동한다.
    @GetMapping("/member/reset/password")
    public String resetPassword(Model model, HttpServletRequest request){

        String uuid = request.getParameter("id");
        model.addAttribute("uuid", uuid);


        return "member/reset_password";
    }

    // 비밀번호 초기화 페이지에 있는 양식을 다 입력하고 비밀번호 재설정 버튼을 누르면 그 이후부터 resetPasswordSubmit 메소드 에서 처리한다
    @PostMapping("/member/reset/password")
    public String resetPasswordSubmit(Model model, ResetPasswordInput parameter){

        boolean result = false;
        try {
            result = memberService.resetPassword(parameter.getId(), parameter.getPassword());
        }catch (Exception e){

        }
        model.addAttribute("result", result);

        return "member/reset_password_result";
    }


}
