package com.perfumepedia.PerfumePedia.MockObject;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MockPerfume {

    private String uuid;
    private String perfume_name;
    private String brand_name;
    private String image_path;
    private String created_at;


    public MockPerfume() {


    }


}
