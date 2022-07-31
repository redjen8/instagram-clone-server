package com.redjen.instagram.service;

import com.redjen.instagram.domain.common.CustomException;
import com.redjen.instagram.domain.common.ErrorCode;
import com.redjen.instagram.domain.common.LoginToken;
import com.redjen.instagram.domain.entity.Member;
import com.redjen.instagram.domain.member.MemberLoginReqParam;
import com.redjen.instagram.domain.member.MemberLoginResultToken;
import com.redjen.instagram.domain.member.MemberRegisterParam;
import com.redjen.instagram.repository.MemberRepositoryImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class MemberService {

    private final MemberRepositoryImpl memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public MemberService(MemberRepositoryImpl memberRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public Member register(MemberRegisterParam memberRegisterParam) {
        Member newMember = Member.builder()
                .id(memberRegisterParam.getUserId())
                .name(memberRegisterParam.getUserName())
                .birthDate(memberRegisterParam.getBirthDate())
                .password(passwordEncoder.encode(memberRegisterParam.getPassword()))
                .phoneNumber(memberRegisterParam.getPhoneNumber())
                .isActive(true)
                .isPublic(true)
                .isSSO(false)
                .build();

        memberRepository.register(newMember);
        return newMember;
    }

    public MemberLoginResultToken login(MemberLoginReqParam loginReqParam) {
        Optional<Member> isMemberExists = memberRepository.findOneByMemberId(loginReqParam.getUserId());
        if (isMemberExists.isEmpty()) throw new CustomException(ErrorCode.LOGIN_ID_NOT_EXIST);

        Member userMember = isMemberExists.get();
        String encodedPassword = passwordEncoder.encode(loginReqParam.getPassword());
        if (!encodedPassword.equals(userMember.getPassword())) throw new CustomException(ErrorCode.LOGIN_PASSWORD_NOT_EQUAL);

        LoginToken loginToken = jwtService.createToken(userMember.getMemberId());

        return MemberLoginResultToken.builder()
                .accessToken(loginToken.getAccessToken())
                .refreshToken(loginToken.getRefreshToken())
                .userId(userMember.getId())
                .userIndex(userMember.getMemberId())
                .build();
    }
}
