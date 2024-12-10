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
    REQUEST_COMPLETED(HttpStatus.OK, "요청이 성공적으로 이루어졌습니다."),

    // 향수 등록 관련
    REGISTER_COMPLETED(HttpStatus.OK, "향수 등록이 성공적으로 이루어졌습니다."),
    REJECTED_COMPLETED(HttpStatus.OK, "향수 등록이 거절되었습니다."),
    DELETE_COMPLETED(HttpStatus.OK, "향수 삭제가 성공적으로 이루어졌습니다."),
    UPDATE_COMPLETED(HttpStatus.OK, "향수 수정이 성공적으로 이루어졌습니다.");



    private final HttpStatus httpStatus;
    private final String message;


}
