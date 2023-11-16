package com.perfumepedia.PerfumePedia.MockObject;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MockFavPerfume {

    private String uuid;
    private String perfume_name;
    private String brand_name;
    private String image_path;
    private String created_at;

    public MockFavPerfume(){
    }
}
