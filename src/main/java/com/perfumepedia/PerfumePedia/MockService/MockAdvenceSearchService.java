package com.perfumepedia.PerfumePedia.MockService;

import com.perfumepedia.PerfumePedia.MockDto.MockAdvanceSearchDto;

public class MockAdvenceSearchService {
    private MockAdvanceSearchDto data;

    public MockAdvenceSearchService(String uuid) throws Exception{
        data = new MockAdvanceSearchDto(uuid);
        data.setData();
    }

    public MockAdvanceSearchDto getData(){
        return data;
    }
}
