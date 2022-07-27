package com.redjen.instagram.controller;

import com.redjen.instagram.domain.entity.Member;
import com.redjen.instagram.domain.member.MemberRegisterParam;
import com.redjen.instagram.domain.member.MemberRegisterResponse;
import com.redjen.instagram.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("/member")
@RestController
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/register")
    public ResponseEntity<MemberRegisterResponse> joinMember(@Valid @RequestBody MemberRegisterParam memberRegisterParam) {
        Member res = memberService.register(memberRegisterParam);
        MemberRegisterResponse result = new MemberRegisterResponse(res.getMemberId(), "success");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
