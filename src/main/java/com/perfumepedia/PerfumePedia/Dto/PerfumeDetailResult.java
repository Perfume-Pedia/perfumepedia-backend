package com.perfumepedia.PerfumePedia.Dto;

import java.util.Date;
import java.util.List;

public class PerfumeDetailResult {//검색 결과 상세

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
    private Date created_at;

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

    public Date getCreatedAt() {
        return created_at;
    }

}
