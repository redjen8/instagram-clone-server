package com.redjen.instagram.domain.common;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
public class LoginToken {

    @NonNull
    private String accessToken;
    @NonNull
    private String refreshToken;
    @NonNull
    private Long expireSec;

}
