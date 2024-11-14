package com.perfumepedia.perfumepedia.global.response;


import com.perfumepedia.perfumepedia.global.enums.SuccessCode;

public record SuccessResponse<T>(SuccessCode successCode, T data) {
}
