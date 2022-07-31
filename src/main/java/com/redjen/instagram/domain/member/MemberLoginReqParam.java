package com.redjen.instagram.domain.member;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
public class MemberLoginReqParam {

    @NotBlank
    private String userId;
    @NotBlank
    private String password;
}
