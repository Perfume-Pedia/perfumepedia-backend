package com.perfumepedia.PerfumePedia.domain.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

/**
 * 2개의 Controller를 위한 Class 입니다.
 * <p>1. 검색 결과 API - /api/search
 * <p>2. 선호 향수 API - /api/favperfume
 */
public class SearchResultDto {
    private List<PerfumeResult> items;
    private String last_item_id;

    /**
     * Get items
     * @return PerfumeResult를 갖는 List 반환
     */
    public List<PerfumeResult> getItems() {
        return items;
    }

    /**
     * Get list_item_id
     * @return 마지막 향수의 id 값 반환 (uuid가 아닌 Long id값을 String 형식으로)
     */
    public String getLast_item_id() {
        return last_item_id;
    }

    /**
     * Set last_item_id
     * @param last_item_id
     */
    public void setLast_item_id(String last_item_id){
        this.last_item_id = last_item_id;
    }

    /**
     * Set Items
     * @param items
     */
    public void setItems(List<PerfumeResult> items){
        this.items = items;
    }

    /**
     * 하나의 향수 정보를 위한 Class
     */
    @Getter @Setter
    @NoArgsConstructor
    static public class PerfumeResult {

        private String uuid;
        private String brand_name;
        private String perfume_name;
        private String image_path;
        private Date created_at;
    }

}
