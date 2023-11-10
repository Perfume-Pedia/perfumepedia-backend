package com.perfumepedia.PerfumePedia.Dto;


import java.util.ArrayList;
import java.util.List;

public class SearchResultList {
    private Data data;
    private String last_item_id;

    public SearchResultList(){
        data = new Data();
    }

    public String getLast_item_id() {
        return last_item_id;
    }

    public class Data{
        private List<Perfume> items=new ArrayList<>();

        public List<Perfume> getPerfumeList() {
            return items;
        }

    }
}
