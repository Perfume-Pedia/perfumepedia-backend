package com.perfumepedia.PerfumePedia.dataForm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;


import java.util.List;

@Getter @Setter
public class CollectionForm {
    private String brand;
    private String brand_url;
    private String name;
    private String perfume_url;
    private String image;
    private String price;
    private String discontinue;
    private List<String> top_nt;
    private List<String> mid_nt;
    private List<String> base_nt;
    private List<String> single_nt;
    private String update_at;


    @Override
    public String toString() {
        return "CollectionForm{" +
                "brand='" + brand + '\'' +
                ", brand_url='" + brand_url + '\'' +
                ", name='" + name + '\'' +
                ", perfume_url='" + perfume_url + '\'' +
                ", image='" + image + '\'' +
                ", price='" + price + '\'' +
                ", Discontinue='" + discontinue + '\'' +
                ", top_nt=" + top_nt +
                ", mid_nt=" + mid_nt +
                ", base_nt=" + base_nt +
                ", single_nt=" + single_nt +
                ", data=" + update_at +
                '}';
    }
}
