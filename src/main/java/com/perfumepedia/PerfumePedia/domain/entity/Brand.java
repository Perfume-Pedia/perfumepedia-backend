package com.perfumepedia.PerfumePedia.domain.entity;

import com.perfumepedia.PerfumePedia.domain.dto.BrandDto;
import com.perfumepedia.PerfumePedia.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Brand extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BRAND_ID")
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "URL")
    private String url;

    public BrandDto EntityToDto() {
        return new BrandDto(this.id, this.name,this.url);
    }

}
