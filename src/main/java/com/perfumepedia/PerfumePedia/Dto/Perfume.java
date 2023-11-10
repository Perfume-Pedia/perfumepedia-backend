package com.perfumepedia.PerfumePedia.Dto;

import lombok.Data;

public class Perfume {

    private String uuid;
    private String brand_name;
    private String perfume_name;
    private String image_path;
    private Data created_at;

    public String getUuid() {
        return uuid;
    }

    public String getBrandName() {
        return brand_name;
    }

    public String getPerfumeName() {
        return perfume_name;
    }

    public String getImagePath() {
        return image_path;
    }

    public Data getCreatedAt() {
        return created_at;
    }
}
