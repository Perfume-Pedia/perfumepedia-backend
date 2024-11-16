package com.perfumepedia.perfumepedia.domain.perfume.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestPerfumeDto {
    private Long id;
    private String name;
    private int price;
    private Long requestBrandId;
    private String requestBrandName;

    // 생성자
    public RequestPerfumeDto(Long id, String name, int price, Long requestBrandId, String requestBrandName) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.requestBrandId = requestBrandId;
        this.requestBrandName = requestBrandName;
    }

}
