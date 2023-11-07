package com.perfumepedia.PerfumePedia.MockService;

import com.perfumepedia.PerfumePedia.MockObject.MockPerfumeDto;

public class MockPerfumeService {
    private MockPerfumeDto data;

    public MockPerfumeService() {
        data = new MockPerfumeDto();
    }

    public MockPerfumeDto getData(){
        return data;
    }




}
