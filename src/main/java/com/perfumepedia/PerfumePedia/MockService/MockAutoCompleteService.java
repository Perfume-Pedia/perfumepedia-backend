package com.perfumepedia.PerfumePedia.MockService;

import com.perfumepedia.PerfumePedia.MockDto.MockAutoCompleteDto;

public class MockAutoCompleteService {
    private MockAutoCompleteDto data;

    public MockAutoCompleteService(String keyword){
        data = new MockAutoCompleteDto(keyword);
    }

    public MockAutoCompleteDto getData(){
        return data;
    }
}
