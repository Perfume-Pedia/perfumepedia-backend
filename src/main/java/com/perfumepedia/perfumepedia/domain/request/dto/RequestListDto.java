package com.perfumepedia.perfumepedia.domain.request.dto;

import com.perfumepedia.perfumepedia.domain.request.entity.RequestType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RequestListDto {

    private Long requestId;
    private Long perfumeId;
    private String perfumeName;


    @Builder
    public RequestListDto(Long requestId,Long perfumeId, String perfumeName) {
        this.requestId = requestId;
        this.perfumeId = perfumeId;
        this.perfumeName = perfumeName;
    }





}


