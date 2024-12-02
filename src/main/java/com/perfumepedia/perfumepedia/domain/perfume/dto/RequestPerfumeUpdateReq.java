package com.perfumepedia.perfumepedia.domain.perfume.dto;

import com.perfumepedia.perfumepedia.domain.brand.entity.RequestBrand;
import com.perfumepedia.perfumepedia.domain.perfume.entity.RequestPerfume;
import lombok.Builder;

public record RequestPerfumeUpdateReq(Long id, String name, int price, Long requestBrandId, String requestBrandName) {

    @Builder
    public RequestPerfumeUpdateReq(Long id, String name, int price, Long requestBrandId, String requestBrandName) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.requestBrandId = requestBrandId;
        this.requestBrandName = requestBrandName;
    }

    // Entity -> DTO
    public static RequestPerfumeUpdateReq toDto(RequestPerfume requestPerfume) {
        return RequestPerfumeUpdateReq.builder()
                .id(requestPerfume.getId())
                .name(requestPerfume.getName())
                .price(requestPerfume.getPrice())
                .requestBrandId(requestPerfume.getRequestBrand().getId())
                .requestBrandName(requestPerfume.getRequestBrand().getName())
                .build();
    }

    // DTO -> Entity
    public RequestPerfume toEntity(RequestBrand requestBrand) {
        return RequestPerfume.builder()
                .id(this.id)
                .name(this.name)
                .price(this.price)
                .requestBrand(requestBrand)
                .build();
    }
}
