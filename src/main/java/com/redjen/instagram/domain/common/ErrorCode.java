package com.redjen.instagram.domain.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    LOGIN_ID_NOT_EXIST (HttpStatus.BAD_REQUEST, "해당 아이디가 존재하지 않습니다."),
    LOGIN_PASSWORD_NOT_EQUAL (HttpStatus.BAD_REQUEST, "아이디와 비밀번호가 일치하지 않습니다."),
    INVALID_FORMAT_TOKEN (HttpStatus.BAD_REQUEST, "jwt 토큰의 형식이 유효하지 않습니다."),
    EXPIRED_ACCESS_TOKEN (HttpStatus.UNAUTHORIZED, "만료된 jwt 토큰입니다.");

    private final HttpStatus status;
    private final String message;
}
