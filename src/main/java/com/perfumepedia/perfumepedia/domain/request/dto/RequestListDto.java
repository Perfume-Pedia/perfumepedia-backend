package com.perfumepedia.perfumepedia.domain.request.dto;

import com.perfumepedia.perfumepedia.domain.request.entity.RequestType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RequestListDto {

    private Long perfumeId;
    private String perfumeName;


    @Builder
    public RequestListDto(Long perfumeId, String perfumeName) {
        this.perfumeId = perfumeId;
        this.perfumeName = perfumeName;
    }





}


