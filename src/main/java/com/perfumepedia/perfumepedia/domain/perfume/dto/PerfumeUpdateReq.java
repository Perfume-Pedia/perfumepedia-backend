package com.perfumepedia.perfumepedia.domain.perfume.dto;

import com.perfumepedia.perfumepedia.domain.brand.entity.Brand;
import com.perfumepedia.perfumepedia.domain.perfume.entity.Perfume;
import lombok.Builder;


public record PerfumeUpdateReq(Long id, String name, int price, Long brandId, String brandName) {

    @Builder
    public PerfumeUpdateReq(Long id, String name, int price, Long brandId, String brandName) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.brandId = brandId;
        this.brandName = brandName;
    }

    // Entity -> DTO
    public static PerfumeUpdateReq toDto(Perfume perfume) {
        return PerfumeUpdateReq.builder()
                .id(perfume.getId())
                .name(perfume.getName())
                .price(perfume.getPrice())
                .brandId(perfume.getBrand().getId())
                .brandName(perfume.getBrand().getName())
                .build();
    }

    // DTO -> Entity
    public Perfume toEntity(Brand brand) {
        return Perfume.builder()
                .id(this.id)
                .name(this.name)
                .price(this.price)
                .brand(brand)
                .build();
    }
}