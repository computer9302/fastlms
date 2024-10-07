package com.zerobase.fastlms.configuration;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import java.io.IOException;
import java.net.URLEncoder;
// 로그인에 실패했을시 콘솔과 웹화면에 메세지를 띄워주는 클래스
public class UserAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        String msg = "로그인에 실패하였습니다.";

        if (exception instanceof InternalAuthenticationServiceException){
            msg = exception.getMessage();
        }

        setUseForward(false);
        String redirectUrl = "/member/login?error=true&errorMessage=" + URLEncoder.encode(msg, "UTF-8");
        response.sendRedirect(redirectUrl);

        System.out.println("로그인에 실패하였습니다.");

        super.onAuthenticationFailure(request, response, exception);
    }
}
