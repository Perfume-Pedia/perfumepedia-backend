package com.perfumepedia.PerfumePedia.controller;

import com.perfumepedia.PerfumePedia.dto.ResponseData;
import com.perfumepedia.PerfumePedia.service.PerfumeDetailService;
import com.perfumepedia.PerfumePedia.service.SearchResultService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class SearchResultController {

    private final SearchResultService searchResultService;
    private final PerfumeDetailService perfumeDetailService;

    @Autowired
    public SearchResultController(SearchResultService searchResultService,PerfumeDetailService perfumeDetailService) {
        this.searchResultService = searchResultService;
        this.perfumeDetailService = perfumeDetailService;
    }



    //향수 검색시 실행하는 함수
    @Tag(name = "검색 결과", description = "향수 목록을 반환하는 API")
    @Parameters({
            @Parameter(name = "lastId", description = "마지막 id값", example = "0"),
            @Parameter(name = "size", description = "향수 개수", example = "6"),
            @Parameter(name = "keyword", description = "검색어", example = "딥디크")
    })
    @GetMapping("search")
    public ResponseData getSearchResult(
            @RequestParam(value = "lastId",required = true) Long lastId,
            @RequestParam(value = "size",required = true) int size,
            @RequestParam(value = "keyword",required = true) String keyword
    ){
        return searchResultService.searchByKeyword(lastId, size, keyword);
    }

    //검색한 향수에서 향수 상세 정보 출력하는 함수
    @Tag(name = "검색 결과", description = "향수의 상세 정보를 반환하는 API")
    @Parameter(name = "uuid", description = "향수 id값", example = "123")
    @GetMapping("search/advanced")
    public ResponseData getPerfumeDetail(
            @RequestParam(value = "uuid",required = true) Long uuid
    ){
        return perfumeDetailService.searchPerfumeWithDetail(uuid);
    }

    //선호 향수(마이페이지) 출력 함수
    @Tag(name = "선호 향수", description = "입력한 uuids에 따라 선호 향수 목록을 반환하는 API")
    @Parameter(name = "uuids", description = "향수 id들의 값", example = "123,700,400")
    @GetMapping("favperfume")
    public ResponseData getFavPerfumeResult(
            @RequestParam(name = "uuids",required = true) List<Long> uuids
    ){
        return searchResultService.searchByPerfumeId(uuids);
    }

    //선호 향수에서 향수 상세 정보 출력하는 함수
    @Tag(name = "선호 향수", description = "선호 향수의 상세 정보를 반환하는 API")
    @Parameter(name = "uuid", description = "향수 id값", example = "123")
    @GetMapping("favperfume/advanced")
    public ResponseData getFavPerfumeDetail(
            @RequestParam(value = "uuid",required = true) Long uuid
    ){
        return perfumeDetailService.searchPerfumeWithDetail(uuid);
    }


}
