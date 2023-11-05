package com.perfumepedia.PerfumePedia.MockController;


import com.perfumepedia.PerfumePedia.MockObject.MockPerfume;
import com.perfumepedia.PerfumePedia.MockService.MockPerfumeService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MockController {
    private final ObjectMapper objectMapper;

    public MockController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true); // Enable Pretty Printer
    }

    @RequestMapping("/api/search")
    public List<MockPerfume> search(
            @RequestParam(name = "lastid") Long lastId,
            @RequestParam(name = "size") Integer size){
        MockPerfumeService mockPerfumeService = new MockPerfumeService();
        System.out.println(mockPerfumeService.mockPerfumes);

        return mockPerfumeService.mockPerfumes;
    }
}
