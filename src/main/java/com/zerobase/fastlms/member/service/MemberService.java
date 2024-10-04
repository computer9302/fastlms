package com.zerobase.fastlms.member.service;

import com.zerobase.fastlms.member.model.MemberInput;
import com.zerobase.fastlms.member.model.ResetPasswordInput;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MemberService extends UserDetailsService {

    boolean register(MemberInput parameter);

    /*
    uuid에 해당하는 계정을 활성화 함.
     */
    boolean emailAuth(String uuid);

    boolean sendResetPassword(ResetPasswordInput parameter);

    /*
    * 입력받은 uuid에 대해서 password로 초기화 함.
    * */
    boolean resetPassword(String uuid, String password);
}
