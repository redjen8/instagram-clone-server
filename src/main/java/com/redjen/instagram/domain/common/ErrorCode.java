package com.redjen.instagram.domain.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    LOGIN_ID_NOT_EXIST (HttpStatus.BAD_REQUEST, "해당 아이디가 존재하지 않습니다.");

    private final HttpStatus status;
    private final String message;
}
