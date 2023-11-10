package com.perfumepedia.PerfumePedia.MockService;

import com.perfumepedia.PerfumePedia.MockObject.MockPerfumeDto;

public class MockPerfumeService {
    private MockPerfumeDto data;

    public MockPerfumeService(String uuid, int size, String keyword) throws Exception{
        data = new MockPerfumeDto(uuid, size, keyword);
    }

    public MockPerfumeDto getData(){
        return data;
    }




}
