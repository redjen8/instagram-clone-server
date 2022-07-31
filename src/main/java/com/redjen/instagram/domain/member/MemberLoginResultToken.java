package com.redjen.instagram.domain.member;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MemberLoginResultToken {
    private Long userIndex;
    private String userId;
    private String accessToken;
    private String refreshToken;
}
