package com.perfumepedia.perfumepedia.domain.perfume.dto;

import com.perfumepedia.perfumepedia.domain.brand.entity.Brand;
import com.perfumepedia.perfumepedia.domain.perfume.entity.Perfume;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class PerfumeDto {

    private Long id;
    private String name;
    private int price;
    private Long brandId;
    private String brandName;

    // 생성자
    public PerfumeDto(Long id, String name, int price, Long brandId, String brandName) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.brandId = brandId;
        this.brandName = brandName;
    }

    // Entity -> DTO
    public static PerfumeDto toDto(Perfume perfume) {
        return new PerfumeDto(
                perfume.getId(),
                perfume.getName(),
                perfume.getPrice(),
                perfume.getBrand().getId(),
                perfume.getBrand().getName()
        );
    }

    // DTO -> Entity
    public Perfume toEntity(Brand brand) {
        return new Perfume(this.id, this.name, this.price, brand);
    }

}