package com.zerobase.fastlms.member.repository;

import com.zerobase.fastlms.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface
MemberRepository extends JpaRepository<Member, String> {

    // 파라미터로 들어온 이메일 인증키가 존재하는지 찾는다 그리고 그에 맞는 Member객체를 돌려준다.
    Optional<Member> findAllByEmailAuthKey(String emailAuthKey);

    // 파라미터로 들어온 userId, userName으로 적절한 Member를 찾아서 반환한다.
    Optional<Member> findByUserIdAndUserName(String userId, String userName);

    // 파라미터로 들어온 resetPasswordKey로 적절한 Member를 찾아서 리턴한다.
    Optional<Member> findByResetPasswordKey(String resetPasswordKey);
}
