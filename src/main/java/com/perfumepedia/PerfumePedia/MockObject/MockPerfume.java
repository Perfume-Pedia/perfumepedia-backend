package com.perfumepedia.PerfumePedia.MockObject;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MockPerfume {

    private String perfume_name;
    private String brand_name;
    private String price;
    private String url;
    private String image_url;
    private String brand_url;

    private List<MockNote> top_note_list;
    private List<MockNote> middle_note_list;
    private List<MockNote> base_note_list;
    private List<MockNote> single_note_lis;

    public MockPerfume() {

        top_note_list = new ArrayList<>();
        middle_note_list = new ArrayList<>();
        base_note_list = new ArrayList<>();
        single_note_lis = new ArrayList<>();

    }

    public void addTopNote(MockNote note){
        top_note_list.add(note);
    }

    public void addMiddleNote(MockNote note){
        middle_note_list.add(note);
    }

    public void addBaseNote(MockNote note){
        base_note_list.add(note);
    }

    public void addSingleNote(MockNote note){
        single_note_lis.add(note);
    }
}
