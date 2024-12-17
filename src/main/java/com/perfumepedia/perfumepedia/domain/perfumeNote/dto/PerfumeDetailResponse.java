package com.perfumepedia.perfumepedia.domain.perfumeNote.dto;

import com.perfumepedia.perfumepedia.domain.perfume.entity.Perfume;
import com.perfumepedia.perfumepedia.domain.perfume.entity.RequestPerfume;


import java.util.List;
import java.util.Map;

public record PerfumeDetailResponse(

        Long requestId,
        Long perfumeId,
        String name,
        String brandName,
        Map<String, List<String>> notes,
        int price
) {
    // Entity -> DTO (기존 향수)
    public static PerfumeDetailResponse toDto(Perfume perfume, Map<String, List<String>> notes, Long requestId) {
        return new PerfumeDetailResponse(
                requestId,
                perfume.getId(),
                perfume.getName(),
                perfume.getBrand().getName(),
                notes,
                perfume.getPrice()
        );
    }

    // Entity -> DTO (신규 향수)
    public static PerfumeDetailResponse fromEntity(RequestPerfume requestPerfume, Map<String, List<String>> notes, Long requestId) {
        return new PerfumeDetailResponse(
                requestId,
                requestPerfume.getId(),
                requestPerfume.getName(),
                requestPerfume.getRequestBrand().getName(),
                notes,
                requestPerfume.getPrice()
        );
    }
}
