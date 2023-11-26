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
@RequiredArgsConstructor
@RequestMapping("/api/")
public class SearchResultController {

    private SearchResultService searchResultService;

    @Autowired
    public SearchResultController(SearchResultService searchResultService) {
        this.searchResultService=searchResultService;
    }


    private PerfumeDetailService perfumeDetailService;

    @Autowired
    public SearchResultController(PerfumeDetailService perfumeDetailService){
        this.perfumeDetailService=perfumeDetailService;
    }


    //향수 검색시 실행하는 함수
    @GetMapping("search")
    public ResponseData getSearchResultDto(
            @RequestParam(value = "keyword") String keyword
    ){
        return searchResultService.searchByKeyword(keyword);
    }

//    //검색한 향수에서 향수 상세 정보 출력하는 함수
//    @GetMapping("search/advanced")
//    public ResponseData getPerfumeDetailDto(
//            @RequestParam(value = "perfumeId") Long perfumeId
//    ){
//        return PerfumeDetailService.searchPerfumeWithDetail(Long perfumeId);
//    }
//
//    //선호 향수(마이페이지) 출력 함수
//    @GetMapping("favperfume")
//    public ResponseData getFavPerfumeResultDto(){
//
//        return searchResultDto;
//    }
//
//    //선호 향수에서 향수 상세 정보 출력하는 함수
//    @GetMapping("favperfume/advanced")
//    public ResponseData getFavPerfumeDetailDto(
//            @RequestParam(value = "perfumeId") Long perfumeId
//    ){
//        return PerfumeDetailService.searchPerfumeWithDetail(Long perfumeId);
//    }


}
