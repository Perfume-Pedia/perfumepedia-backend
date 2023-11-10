package com.perfumepedia.PerfumePedia.Dto;

import lombok.Data;

import java.util.List;

public class PerfumeDetailResult {

    private String uuid;
    private String brand_name;
    private String perfume_name;
    private List<String> top_note_names;
    private List<String> middle_note_names;
    private List<String> base_note_names;
    private List<String> single_note_names;
    private String price;
    private String url;
    private String image_path;
    private Data created_at;

    public String getUuid(){
        return uuid;
    }

    public String getBrandName(){
        return brand_name;
    }

    public String getPerfumeName(){
        return perfume_name;
    }

    public List<String> getTopNoteNames(){
        return top_note_names;
    }

    public List<String> getMiddleNoteNames(){
        return middle_note_names;
    }

    public List<String> getBaseNoteNames(){
        return base_note_names;
    }

    public List<String> getSingleNoteNames(){
        return single_note_names;
    }

    public String getPrice() {
        return price;
    }

    public String getUrl() {
        return url;
    }

    public String getImagePath() {
        return image_path;
    }

    public Data getCreatedAt() {
        return created_at;
    }

}
