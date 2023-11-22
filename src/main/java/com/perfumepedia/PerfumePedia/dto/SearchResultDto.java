package com.perfumepedia.PerfumePedia.dto;


import java.util.Date;
import java.util.List;

public class SearchResultDto {//검색 결과 && 선호 향수
    private List<PerfumeResult> perfumeList;
    private String last_item_id;

    public List<PerfumeResult> getPerfumeList() {
        return perfumeList;
    }

    public String getLast_item_id() {
        return last_item_id;
    }

    public class PerfumeResult {

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

}
