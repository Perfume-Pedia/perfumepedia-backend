package com.perfumepedia.PerfumePedia.controller;

import com.perfumepedia.PerfumePedia.dto.PerfumeDetailDto;
import com.perfumepedia.PerfumePedia.dto.SearchResultDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/searchresult")
public class SearchResultController {

    //WordService WordService=new WordServise();

    SearchResultDto searchResultDto = new SearchResultDto();
    PerfumeDetailDto perfumeDetailDto = new PerfumeDetailDto();


}
