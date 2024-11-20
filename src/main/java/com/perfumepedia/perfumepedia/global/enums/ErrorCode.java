package com.perfumepedia.perfumepedia.global.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode implements ResponseCode {

    // Common Error Code
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "An internal server error occurred."),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "Invalid request."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "Request not found"),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "HTTP method not allowed."),
    ACCESS_DENIED(HttpStatus.FORBIDDEN, "Unauthorized request."),

    // 향수 검색 관련 Error Code
    PERFUME_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 향수가 업서용.");



    private final HttpStatus httpStatus;
    private final String message;
}
