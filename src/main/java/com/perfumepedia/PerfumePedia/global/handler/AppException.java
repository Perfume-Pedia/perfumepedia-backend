package com.perfumepedia.PerfumePedia.global.handler;

import lombok.Getter;
import com.perfumepedia.PerfumePedia.global.enums.ErrorCode;

@Getter
public class AppException extends RuntimeException {
    private final ErrorCode errorCode;
    private final String message;

    public AppException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.message = errorCode.getMessage();
    }
}
