package com.perfumepedia.PerfumePedia.MockController;

import com.perfumepedia.PerfumePedia.MockDto.MockAdvanceSearchDto;
import com.perfumepedia.PerfumePedia.MockService.MockAdvenceSearchService;
import com.perfumepedia.PerfumePedia.MockService.MockSearchService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@CrossOrigin("*")
public class MockAPIController {
    private final ObjectMapper objectMapper;

    public MockAPIController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true); // Enable Pretty Printer
    }


    @Tag(name = "검색 결과", description = "검색 결과를 반환하는 API")
    @Parameters({
            @Parameter(name = "lastid", description = "마지막 id값", example = "0"),
            @Parameter(name = "size", description = "향수 개수", example = "6"),
            @Parameter(name = "keyword", description = "검색어", example = "딥디크")
    })
    @GetMapping("/search")
    public MockSearchService search (
            @RequestParam(name = "lastid") String lastId,
            @RequestParam(name = "size") Integer size,
            @RequestParam(name = "keyword") String keyword) throws Exception{

        MockSearchService mockSearchService = new MockSearchService(lastId, size, keyword);
        System.out.println(mockSearchService.getData());

        return mockSearchService;
    }

    @Tag(name = "검색 결과", description = "검색 결과를 반환하는 API")
    @Parameter(name = "uuid", description = "향수 id값", example = "123")
    @GetMapping("/search/advanced")
    public MockAdvanceSearchDto searchAdvanced (@RequestParam(name = "uuid") String uuid) throws Exception{

        MockAdvenceSearchService mockAdvenceSearchService = new MockAdvenceSearchService(uuid);
        MockAdvanceSearchDto mockAdvanceSearchDto  = mockAdvenceSearchService.getData();
        System.out.println(mockAdvanceSearchDto);

        return mockAdvanceSearchDto;
    }
}
