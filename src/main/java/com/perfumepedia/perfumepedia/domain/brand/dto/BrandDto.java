package com.perfumepedia.perfumepedia.domain.brand.dto;

import com.perfumepedia.perfumepedia.domain.brand.entity.Brand;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class BrandDto {
    private Long id;
    private String name;
    private String url;

    // 생성자
    public BrandDto(Long id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    // Entity -> DTO
    public static BrandDto toDto(Brand brand) {
        return new BrandDto(
                brand.getId(),
                brand.getName(),
                brand.getUrl()
        );
    }

    // DTO -> Entity
    public Brand toEntity() {
        return new Brand(
                this.id,
                this.name,
                this.url
        );
    }

}