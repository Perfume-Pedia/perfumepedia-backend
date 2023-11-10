package com.perfumepedia.PerfumePedia.datainsert;

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
    private List<String> top_nt;
    private List<String> mid_nt;
    private List<String> base_nt;
    private List<String> single_nt;


}
