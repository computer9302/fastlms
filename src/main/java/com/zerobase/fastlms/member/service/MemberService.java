package com.zerobase.fastlms.member.service;

import com.zerobase.fastlms.member.model.MemberInput;
import com.zerobase.fastlms.member.model.ResetPasswordInput;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MemberService extends UserDetailsService {

    /*

    parameter를 db에 저장한다.
     */

    boolean register(MemberInput parameter);

    /*
    uuid에 해당하는 계정을 활성화 함.
     */
    boolean emailAuth(String uuid);

    /*
        입력한 이메일로 비밀번호 초기화 정보를 전송

     */
    boolean sendResetPassword(ResetPasswordInput parameter);

    /*
     입력받은 uuid에 대해서 password로 초기화 함.
    * */
    boolean resetPassword(String uuid, String password);
}
