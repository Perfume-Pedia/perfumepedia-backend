package com.perfumepedia.perfumepedia.domain.brand.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class BrandAndPerfumeCountDto {

    private Long brandCount;
    private Long perfumeCount;

    @Builder
    public BrandAndPerfumeCountDto(Long brandCount, Long perfumeCount) {
        this.brandCount = brandCount;
        this.perfumeCount = perfumeCount;
    }

}
