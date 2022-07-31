package com.redjen.instagram.service;

import com.redjen.instagram.domain.entity.Member;
import com.redjen.instagram.domain.member.MemberRegisterParam;
import com.redjen.instagram.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
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
}
