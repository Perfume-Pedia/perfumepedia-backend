package com.perfumepedia.PerfumePedia.MockService;

import com.perfumepedia.PerfumePedia.MockDto.MockFavPerfumeDto;

import java.util.List;

public class MockFavPerfumeService {
    private MockFavPerfumeDto data;

    public MockFavPerfumeService(List<String> uuids) throws Exception{
        data = new MockFavPerfumeDto(uuids);
        data.setItems();
    }

    public MockFavPerfumeDto getData(){
        return data;
    }
}
