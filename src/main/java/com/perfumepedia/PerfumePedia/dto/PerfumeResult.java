package com.perfumepedia.PerfumePedia.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

/**
 * 하나의 향수 정보를 위한 Class
 */
@Getter
@Setter
@NoArgsConstructor
public class PerfumeResult {

    private String uuid;
    private String brand_name;
    private String perfume_name;
    private String image_path;
    private Date created_at;
}
