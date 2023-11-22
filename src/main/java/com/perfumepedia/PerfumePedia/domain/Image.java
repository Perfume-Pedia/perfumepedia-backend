package com.perfumepedia.PerfumePedia.domain;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
@Getter
public class Image {

    private String name;
    private String path;

    // 생성자
    public Image(String name, String path){
        this.name = name;
        this.path = path;
    }
}
