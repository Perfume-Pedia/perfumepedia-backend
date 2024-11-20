package com.perfumepedia.perfumepedia.domain.brand.dto;

import com.perfumepedia.perfumepedia.domain.brand.entity.RequestBrand;
import lombok.Builder;

public record RequestBrandUpdateReq(Long id, String name, String url) {

    @Builder
    public RequestBrandUpdateReq(Long id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    // Entity -> DTO
    public static RequestBrandUpdateReq toDto(RequestBrand requestBrand) {
        return RequestBrandUpdateReq.builder()
                .id(requestBrand.getId())
                .name(requestBrand.getName())
                .url(requestBrand.getUrl())
                .build();
    }

    // DTO -> Entity
    public RequestBrand toEntity() {
        return RequestBrand.builder()
                .id(this.id)
                .name(this.name)
                .url(this.url)
                .build();
    }
}
