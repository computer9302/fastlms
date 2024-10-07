package com.zerobase.fastlms.member.exception;

// 회원 이메일 인증에 실패했을때 발생하는 예외이다.
public class MemberNotEmailAuthException extends RuntimeException {
    public MemberNotEmailAuthException(String error) {
        super(error);
    }
}
