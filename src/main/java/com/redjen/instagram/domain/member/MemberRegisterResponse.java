package com.redjen.instagram.domain.member;

import com.redjen.instagram.domain.common.CommonResponse;
import lombok.Getter;

@Getter
public class MemberRegisterResponse extends CommonResponse {
    private final Long memberId;

    public MemberRegisterResponse(Long memberId, String message) {
        super(message);
        this.memberId = memberId;
    }
}
