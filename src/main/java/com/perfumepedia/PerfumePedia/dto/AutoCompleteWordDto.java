package com.perfumepedia.PerfumePedia.dto;

import java.util.ArrayList;
import java.util.List;

public class AutoCompleteWordDto {
    int item_count;
    List<AutoCompleteWords> autoCompleteWords = new ArrayList<>();

    public int getItem_count() {
        return item_count;
    }

    public List<AutoCompleteWords> getAutoCompleteWords() {
        return autoCompleteWords;
    }

    class AutoCompleteWords{
        String keyword;

        public String getKeyword() {
            return keyword;
        }

    }

}
