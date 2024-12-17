package com.perfumepedia.perfumepedia.domain.perfumeNote.dto;

import com.perfumepedia.perfumepedia.domain.perfume.entity.Perfume;

import java.util.List;
import java.util.Map;


public record PerfumeSearchDetail(

        Long perfumeId,
        String name,
        String brandName,
        Map<String, List<String>> notes,
        int price
) {

    public static PerfumeSearchDetail toDto(Perfume perfume, Map<String, List<String>> notes) {
        return new PerfumeSearchDetail(
                perfume.getId(),
                perfume.getName(),
                perfume.getBrand().getName(),
                notes,
                perfume.getPrice()
        );
    }

}