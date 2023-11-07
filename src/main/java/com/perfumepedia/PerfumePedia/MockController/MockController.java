package com.perfumepedia.PerfumePedia.MockController;

import com.perfumepedia.PerfumePedia.MockService.MockPerfumeService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@CrossOrigin("*")
public class MockController {
    private final ObjectMapper objectMapper;

    public MockController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true); // Enable Pretty Printer
    }

    @Tag(name = "검색 결과", description = "검색 결과를 반환하는 API")
    @Parameters({
            @Parameter(name = "lastid", description = "마지막 id값", example = "0"),
            @Parameter(name = "size", description = "향수 개수", example = "6"),
    })
    @ApiResponse(responseCode = "404", description = "해당 ID의 유저가 존재하지 않습니다.")
    @GetMapping("/search")
    public MockPerfumeService search(
            @RequestParam(name = "lastid") Long lastId,
            @RequestParam(name = "size") Integer size){
        MockPerfumeService mockPerfumeService = new MockPerfumeService();
        System.out.println(mockPerfumeService.getData());

        return mockPerfumeService;

    }
}
