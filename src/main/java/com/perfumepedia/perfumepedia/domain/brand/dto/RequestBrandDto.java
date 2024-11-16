package com.perfumepedia.perfumepedia.domain.brand.dto;

import com.perfumepedia.perfumepedia.domain.brand.entity.RequestBrand;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestBrandDto {
    private Long id;
    private String name;
    private String url;

    // 생성자
    public RequestBrandDto(Long id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    // Entity -> DTO
    public static RequestBrandDto toDto(RequestBrand brand) {
        return new RequestBrandDto(
                brand.getId(),
                brand.getName(),
                brand.getUrl()
        );
    }

    // DTO -> Entity
    public RequestBrand toEntity() {
        return new RequestBrand(
                this.id,
                this.name,
                this.url
        );
    }
}
