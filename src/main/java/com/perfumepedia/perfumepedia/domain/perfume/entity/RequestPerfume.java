package com.perfumepedia.perfumepedia.domain.perfume.entity;

import com.perfumepedia.perfumepedia.domain.brand.entity.RequestBrand;
import com.perfumepedia.perfumepedia.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestPerfume extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REQUEST_PERFUME_ID")
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "PRICE")
    private int price;

    @ManyToOne
    @JoinColumn(name = "REQUEST_BRAND_ID")
    private RequestBrand requestBrand;

    @Builder
    public RequestPerfume(Long id, String name, int price, RequestBrand requestBrand) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.requestBrand = requestBrand;
    }
}
