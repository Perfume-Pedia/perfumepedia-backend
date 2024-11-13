package com.perfumepedia.PerfumePedia.global.response;


import com.perfumepedia.PerfumePedia.global.enums.SuccessCode;

public record SuccessResponse<T>(SuccessCode successCode, T data) {
}
