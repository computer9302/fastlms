package com.zerobase.fastlms.member.model;

import lombok.Data;
import lombok.ToString;

@ToString
@Data  // 패스워드 초기화 설정에 필요한 데이터 저장을 위한 모델
public class ResetPasswordInput {
    private String userId;
    private String userName;

    private String id;
    private String password;
}

