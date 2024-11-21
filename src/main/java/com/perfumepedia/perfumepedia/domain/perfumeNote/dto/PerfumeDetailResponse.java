package com.perfumepedia.perfumepedia.domain.perfumeNote.dto;

import com.perfumepedia.perfumepedia.domain.perfume.entity.Perfume;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor
public record PerfumeDetailResponse(

        Long perfumeId,
        String name,
        String brandName,
        Map<String, List<String>> notes,
        int price
) {
    // Entity -> DTO
    public static PerfumeDetailResponse toDto(Perfume perfume, Map<String, List<String>> notes) {
        return new PerfumeDetailResponse(
                perfume.getId(),
                perfume.getName(),
                perfume.getBrand().getName(),
                notes,
                perfume.getPrice()
        );
    }
}
