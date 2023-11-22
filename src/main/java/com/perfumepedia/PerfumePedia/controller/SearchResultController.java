package com.perfumepedia.PerfumePedia.controller;

import com.perfumepedia.PerfumePedia.dto.PerfumeDetailDto;
import com.perfumepedia.PerfumePedia.dto.SearchResultDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class SearchResultController {


    //WordService WordService;
    //
    // new WordServise();

    SearchResultDto searchResultDto ;
    PerfumeDetailDto perfumeDetailDto ;

    @GetMapping("search")
    public SearchResultDto getSearchResultDto(){

        return searchResultDto;
    }

    @GetMapping("search/advanced")
    public PerfumeDetailDto getPerfumeDetailDto(){

        return perfumeDetailDto;
    }

    @GetMapping("favperfume")
    public SearchResultDto getFavPerfumeResultDto(){

        return searchResultDto;
    }

    @GetMapping("favperfume/advanced")
    public PerfumeDetailDto getFavPerfumeDetailDto(){

        return perfumeDetailDto;
    }


}
