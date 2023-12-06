package com.perfumepedia.PerfumePedia.dto;

import java.util.Date;
import java.util.List;

public class PerfumeDetailDto {//검색 결과 상세 && 선호 향수 상세

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






    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public void setPerfume_name(String perfume_name) {
        this.perfume_name = perfume_name;
    }

    public void setTop_note_names(List<String> top_note_names) {
        this.top_note_names = top_note_names;
    }

    public void setMiddle_note_names(List<String> middle_note_names) {
        this.middle_note_names = middle_note_names;
    }

    public void setBase_note_names(List<String> base_note_names) {
        this.base_note_names = base_note_names;
    }

    public void setSingle_note_names(List<String> single_note_names) {
        this.single_note_names = single_note_names;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}
