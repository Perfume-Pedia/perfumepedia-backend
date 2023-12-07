package com.perfumepedia.PerfumePedia.dto;

import lombok.NoArgsConstructor;

/**
 * String keyword 를 위한 Class
 */
@NoArgsConstructor
public class AutoCompleteWord{
    private String keyword;
    public String getKeyword() {
        return keyword;
    }
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}