package com.perfumepedia.PerfumePedia.controller;

import com.perfumepedia.PerfumePedia.dto.AutoCompleteWordDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/autocomplete")
public class AutoCompleteController {

//    private fianl WordService WordService;
//
//    @Autowired
//    public SearchResultController(WordService wordService){
//        this.wordService=wordService;
//    }

    AutoCompleteWordDto autoCompleteWordDto;

//    검색어 자동완성 컨트롤
    @PostMapping()
    public AutoCompleteWordDto getContainKeyword(
            @RequestParam(value="keyword",required = true) String keyword,
            @RequestParam(defaultValue = "5") int limit){


        return autoCompleteWordDto;
    }



}
