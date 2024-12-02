package com.perfumepedia.perfumepedia.domain.perfumeNote.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestPerfumeDetailReq {

    private String name;            // 향수 이름
    private String brand;           // 브랜드 이름
    private String topNote;         // 탑 노트
    private String middleNote;      // 미들 노트
    private String baseNote;        // 베이스 노트
    private String singleNote;      // 싱글 노트
    private int price;              // 가격

    @Builder
    public RequestPerfumeDetailReq(String name, String brand, String topNote, String middleNote, String baseNote, String singleNote, int price) {
        this.name = name;
        this.brand = brand;
        this.topNote = topNote;
        this.middleNote = middleNote;
        this.baseNote = baseNote;
        this.singleNote = singleNote;
        this.price = price;
    }
}
