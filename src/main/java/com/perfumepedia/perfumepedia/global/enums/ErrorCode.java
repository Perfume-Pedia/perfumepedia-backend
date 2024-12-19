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
    PERFUME_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 향수가 업서용."),
    NOTE_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 노트가 없습니다."),
    REQUEST_NOT_FOUND(HttpStatus.NOT_FOUND, "요청이 존재하지 않습니다."),
    NOTES_FORMAT_INVALID(HttpStatus.BAD_REQUEST, "노트의 형식이 잘못되었습니다. 싱글 노트와 탑/미들/베이스 노트 중 하나만 제공되어야 합니다."),

    // DTO 관련
    INVALID_REQUEST_TYPE(HttpStatus.BAD_REQUEST, "요청 타입이 잘못되었습니다."),
    REQUEST_TYPE_MISMATCH(HttpStatus.BAD_REQUEST, "요청 타입이 일치하지 않습니다.");



    private final HttpStatus httpStatus;
    private final String message;
}
