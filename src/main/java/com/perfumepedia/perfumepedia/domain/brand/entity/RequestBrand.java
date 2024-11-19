package com.perfumepedia.perfumepedia.domain.brand.entity;

import com.perfumepedia.perfumepedia.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestBrand extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REQUEST_BRAND_ID")
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "URL")
    private String url;

    @Builder
    public RequestBrand(Long id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }
}
