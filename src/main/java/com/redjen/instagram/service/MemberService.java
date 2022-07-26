package com.redjen.instagram.service;

import com.redjen.instagram.domain.common.CustomException;
import com.redjen.instagram.domain.common.ErrorCode;
import com.redjen.instagram.domain.common.LoginToken;
import com.redjen.instagram.domain.entity.Member;
import com.redjen.instagram.domain.entity.Post;
import com.redjen.instagram.domain.member.MemberGetPostListParam;
import com.redjen.instagram.domain.member.MemberLoginReqParam;
import com.redjen.instagram.domain.member.MemberLoginResultToken;
import com.redjen.instagram.domain.member.MemberRegisterParam;
import com.redjen.instagram.repository.MemberRepositoryImpl;
import com.redjen.instagram.repository.PostRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MemberService {

    private final MemberRepositoryImpl memberRepository;
    private final PostRepository postRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public MemberService(MemberRepositoryImpl memberRepository, PostRepository postRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.memberRepository = memberRepository;
        this.postRepository = postRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public Member register(MemberRegisterParam memberRegisterParam) {
        Optional<Member> isMemberExists = memberRepository.findOneByMemberId(memberRegisterParam.getUserId());
        if (isMemberExists.isPresent()) throw new CustomException(ErrorCode.REGISTER_ID_ALREADY_EXIST);

        Member newMember = Member.builder()
                .userId(memberRegisterParam.getUserId())
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
        if (!passwordEncoder.matches(loginReqParam.getPassword(), userMember.getPassword())) throw new CustomException(ErrorCode.LOGIN_PASSWORD_NOT_EQUAL);

        LoginToken loginToken = jwtService.createToken(userMember.getMemberId());

        return MemberLoginResultToken.builder()
                .accessToken(loginToken.getAccessToken())
                .refreshToken(loginToken.getRefreshToken())
                .userId(userMember.getUserId())
                .userIndex(userMember.getMemberId())
                .build();
    }

    public List<Post> getPostList(MemberGetPostListParam memberGetPostListParam) {
        Optional<Member> memberExists = memberRepository.findOneByMemberId(memberGetPostListParam.getUserId());
        memberExists.orElseThrow(() -> new CustomException(ErrorCode.POST_MEMBER_ID_NOT_EXIST));

        Optional<List<Post>> memberPostList = postRepository.findPostListByMember(memberExists.get());
        return memberPostList.orElse(Collections.emptyList());
    }
}
