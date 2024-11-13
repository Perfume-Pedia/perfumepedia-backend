package com.perfumepedia.PerfumePedia.domain.dto;

public record ResponseData<T>(T data) {
    /**
     * 생성 및 초기화 진행
     *
     * @param data T 객체
     */
    public ResponseData {
    }
}
