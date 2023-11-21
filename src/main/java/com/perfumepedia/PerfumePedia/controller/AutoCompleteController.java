package com.perfumepedia.PerfumePedia.controller;

import com.perfumepedia.PerfumePedia.dto.AutoCompleteWordDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/autocomplete")
public class AutoCompleteController {

    AutoCompleteWordDto autoCompleteWordDto;

//    검색어 자동완성 컨트롤
    @PostMapping()
    public AutoCompleteWordDto getContainKeyword(
            @RequestParam(value="keyword",required = true) String keyword,
            @RequestParam(defaultValue = "5") int limit){


        return autoCompleteWordDto;
    }

    //WordService WordService=new WordServise();



}
