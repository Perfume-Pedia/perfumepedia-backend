package com.perfumepedia.perfumepedia.domain.perfume.entity;

import com.perfumepedia.perfumepedia.domain.brand.entity.Brand;
import com.perfumepedia.perfumepedia.domain.request.entity.Request;
import com.perfumepedia.perfumepedia.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Perfume extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PERFUME_ID")
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "PRICE")
    private int price;

    @ManyToOne
    @JoinColumn(name = "BRAND_ID")
    private Brand brand;

    @Builder
    public Perfume(Long id, String name, int price, Brand brand) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.brand = brand;
    }

    // 수정 메서드
    public void update(String name, int price, Brand brand) {
        this.name = name;
        this.price = price;
        this.brand = brand;
    }
}