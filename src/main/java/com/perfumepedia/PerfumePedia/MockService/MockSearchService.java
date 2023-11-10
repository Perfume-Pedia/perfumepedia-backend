package com.perfumepedia.PerfumePedia.MockService;

import com.perfumepedia.PerfumePedia.MockDto.MockSearchDto;

public class MockSearchService {
    private MockSearchDto data;

    public MockSearchService(String uuid, int size, String keyword) throws Exception{
        data = new MockSearchDto(uuid, size, keyword);
        data.setItems();
    }

    public MockSearchDto getData(){
        return data;
    }

}
