package com.perfumepedia.perfumepedia.domain.brand.dto;

import com.perfumepedia.perfumepedia.domain.brand.entity.Brand;
import lombok.Builder;

public record BrandUpdateReq(Long id, String name, String url) {

    @Builder
    public BrandUpdateReq(Long id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    // Entity -> DTO
    public static BrandUpdateReq toDto(Brand brand) {
        return BrandUpdateReq.builder()
                .id(brand.getId())
                .name(brand.getName())
                .url(brand.getUrl())
                .build();
    }

    // DTO -> Entity
    public Brand toEntity() {
        return Brand.builder()
                .id(this.id)
                .name(this.name)
                .url(this.url)
                .build();
    }
}