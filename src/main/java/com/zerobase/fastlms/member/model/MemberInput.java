package com.zerobase.fastlms.member.model;

import lombok.Data;
import lombok.ToString;

@ToString
@Data  // 회원가입을 위한 데이터 저장을 하기 위한 모델이다.
public class MemberInput {

    private String userId;
    private String userName;
    private String password;
    private String phone;


}
