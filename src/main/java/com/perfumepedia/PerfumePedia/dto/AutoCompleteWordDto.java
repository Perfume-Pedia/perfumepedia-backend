package com.perfumepedia.PerfumePedia.dto;

import java.util.ArrayList;
import java.util.List;

public class AutoCompleteWordDto {
    int item_count;
    List<AutoCompleteWord> items = new ArrayList<>();


    /**
     * Get item_count
     * @return 5이하의 int 반환
     */
    public int getItem_count() {
        return item_count;
    }

    /**
     * Get items
     * @return String keyword 를 갖는 클래스
     * <p>AutoCompleteWord List 반환
     * <p>사이즈는 5 이하
     */
    public List<AutoCompleteWord> getItems() {
        return items;
    }

    /**
     * Set item_count
     * @param item_count items의 사이즈(5이하)
     */
    public void setItem_count(int item_count) {
        this.item_count = item_count;
    }

    /**
     * Set items
     * @param items List<AutoCompleteWord>
     *
     */
    public void setItems(List<AutoCompleteWord> items){
        this.items = items;
    }

    /**
     * String keyword 를 위한 Class
     */
    class AutoCompleteWord{
        String keyword;

        public String getKeyword() {
            return keyword;
        }

    }

}
