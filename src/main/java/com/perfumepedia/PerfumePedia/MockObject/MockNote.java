package com.perfumepedia.PerfumePedia.MockObject;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MockNote {
    private String note_name;

    public MockNote(String note_name){
        this.note_name = note_name;
    }

}
