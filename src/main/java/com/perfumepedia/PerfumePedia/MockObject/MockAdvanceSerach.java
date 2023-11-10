package com.perfumepedia.PerfumePedia.MockObject;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MockAdvanceSerach {

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
    private String created_at;
}
