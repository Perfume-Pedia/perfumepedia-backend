package com.perfumepedia.PerfumePedia.Dto;

import java.util.Date;

public class Perfume {

    private String uuid;
    private String brand_name;
    private String perfume_name;
    private String image_path;
    private Date created_at;

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

    public Date getCreatedAt() {
        return created_at;
    }
}
