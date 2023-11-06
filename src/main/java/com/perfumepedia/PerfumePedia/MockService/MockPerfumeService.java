package com.perfumepedia.PerfumePedia.MockService;

import com.perfumepedia.PerfumePedia.MockObject.MockDto;
import com.perfumepedia.PerfumePedia.MockObject.MockNote;
import com.perfumepedia.PerfumePedia.MockObject.MockPerfume;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class MockPerfumeService {
    private MockDto data;

    public MockPerfumeService() {
        data = new MockDto();
    }

    public MockDto getData(){
        return data;
    }




}
