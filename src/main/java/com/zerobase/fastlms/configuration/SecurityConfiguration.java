package com.zerobase.fastlms.configuration;

import com.zerobase.fastlms.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.filter.OrderedFormContentFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// 웹 애플리케이션에 관해서 Security구성을 설정하는 클래스
@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfiguration{

    private final MemberService memberService;

    // 패스워드가 평문이 아닌 암호화되게 하기 윈한 함수
    @Bean
    PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 특정 동작(로그인)이 실패했을때 에러메세지와 적절한 후속조치를 하기 위한 함수
    @Bean
    UserAuthenticationFailureHandler getFailureHandler(){
        return new UserAuthenticationFailureHandler();
    }

    // 승인이 없어도 접근할 수 있는 경로를 설정하는 함수
    // 로그인 성공시 접근하게 되는 경로와 실패시 메세지와 후속 조치를 설정한다.
    // 로그아웃 성공시 접근하게 되는 경로와 실패시 메세지와 후속 조치를 설정한다.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/"
                                ,"/member/register"
                                ,"/member/email-auth"
                                ,"/member/find-password"
                                , "/member/reset/password"
                        ).permitAll()
                        .anyRequest().authenticated())
                .securityMatcher("/**");

        http.formLogin(form -> form
                .loginPage("/member/login")
                .failureHandler(getFailureHandler())
                .permitAll());

        http.logout(logout -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
        );

        return http.build();

                
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception{

        auth.userDetailsService(memberService)
                .passwordEncoder(getPasswordEncoder());
    }
}
