package com.perfumepedia.perfumepedia.global.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessCode implements ResponseCode {

    // BASE API
    SUCCESS(HttpStatus.OK, "Retrieval completed successfully."),

    // 검색 관련
    SEARCH_COMPLETED(HttpStatus.OK, "검색이 성공적으로 이루어졌습니다."),

    // 요청 관련
    REQUEST_COMPLETED(HttpStatus.OK, "요청이 성공적으로 이루어졌습니다.");



    private final HttpStatus httpStatus;
    private final String message;


}
