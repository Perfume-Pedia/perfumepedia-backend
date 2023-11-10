package com.perfumepedia.PerfumePedia.Dto;


import java.util.ArrayList;
import java.util.List;

public class SearchResultList {
    private List<Perfume> perfumeList=new ArrayList<>();

    private String last_item_id;

    public List<Perfume> getPerfumeList() {
        return perfumeList;
    }

    public String getLast_item_id() {
        return last_item_id;
    }
}
