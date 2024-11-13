package com.perfumepedia.PerfumePedia.domain.dto;

import com.perfumepedia.PerfumePedia.domain.entity.Brand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class BrandDto {
    private Long id;
    private String name;
    private String url;

    public Brand DtoToEntity() {
        return new Brand(this.id, this.name, this.url);

    }

}
