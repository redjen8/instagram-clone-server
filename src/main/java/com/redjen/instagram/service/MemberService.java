package com.redjen.instagram.service;

import com.redjen.instagram.domain.entity.Member;
import com.redjen.instagram.domain.member.MemberRegisterParam;
import com.redjen.instagram.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member register(MemberRegisterParam memberRegisterParam) {
        Member newMember = Member.builder()
                .id(memberRegisterParam.getUserId())
                .name(memberRegisterParam.getUserName())
                .birthDate(memberRegisterParam.getBirthDate())
                .password(memberRegisterParam.getPassword())
                .phoneNumber(memberRegisterParam.getPhoneNumber())
                .isActive(true)
                .isPublic(true)
                .isSSO(false)
                .build();

        memberRepository.register(newMember);
        return newMember;
    }
}
