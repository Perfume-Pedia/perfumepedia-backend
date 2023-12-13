package com.perfumepedia.PerfumePedia.controller;

import com.perfumepedia.PerfumePedia.dto.PerfumeDetailDto;
import com.perfumepedia.PerfumePedia.dto.ResponseData;
import com.perfumepedia.PerfumePedia.dto.SearchResultDto;
import com.perfumepedia.PerfumePedia.service.PerfumeDetailService;
import com.perfumepedia.PerfumePedia.service.SearchResultService;
import lombok.RequiredArgsConstructor;
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
    @GetMapping("search")
    public ResponseData getSearchResult(
            @RequestParam(value = "lastId",required = true) Long lastId,
            @RequestParam(value = "size",required = true) int size,
            @RequestParam(value = "keyword",required = true) String keyword
    ){
        return searchResultService.searchByKeyword(lastId, size, keyword);
    }

    //검색한 향수에서 향수 상세 정보 출력하는 함수
    @GetMapping("search/advanced")
    public ResponseData getPerfumeDetail(
            @RequestParam(value = "uuid",required = true) Long uuid
    ){
        return perfumeDetailService.searchPerfumeWithDetail(uuid);
    }

    //선호 향수(마이페이지) 출력 함수
    @GetMapping("favperfume")
    public ResponseData getFavPerfumeResult(
            @RequestParam(name = "uuids",required = true) List<Long> uuids
    ){
        return searchResultService.searchByPerfumeId(uuids);
    }

    //선호 향수에서 향수 상세 정보 출력하는 함수
    @GetMapping("favperfume/advanced")
    public ResponseData getFavPerfumeDetail(
            @RequestParam(value = "uuid",required = true) Long uuid
    ){
        return perfumeDetailService.searchPerfumeWithDetail(uuid);
    }


}
