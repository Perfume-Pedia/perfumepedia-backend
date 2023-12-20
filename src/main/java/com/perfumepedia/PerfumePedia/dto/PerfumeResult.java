package com.perfumepedia.PerfumePedia.dto;

import com.perfumepedia.PerfumePedia.domain.Perfume;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

/**
 * 하나의 향수 정보를 위한 Class
 */
@Getter
@Setter
public class PerfumeResult {

    private String uuid;
    private String brand_name;
    private String perfume_name;
    private String image_path;
    private Date created_at;

    public PerfumeResult(Perfume perfume){
        if (perfume != null) {
            // set uuid
            setUuid(perfume.getId().toString());
            // set brand name
            setBrand_name(perfume.getBrand().getName());
            // set perfume name
            setPerfume_name(perfume.getName());
            // set image path
            setImage_path(perfume.getImage().toString());
            // set created at
            setCreated_at(perfume.getDbDate().getCreatedAt());
        }
    }
}
