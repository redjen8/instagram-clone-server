package com.redjen.instagram.domain.common;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommonResponse {
    protected final Boolean isSuccess;
    protected final LocalDateTime timestamp;
    protected final String message;

    protected CommonResponse(String message) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.isSuccess = true;
    }
}
