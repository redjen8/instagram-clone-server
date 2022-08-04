package com.redjen.instagram.domain.member;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MemberGetPostListParam {
    private final String userId;
}
