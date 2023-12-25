package com.perfumepedia.PerfumePedia.controller;

import com.perfumepedia.PerfumePedia.dto.ResponseData;
import com.perfumepedia.PerfumePedia.service.AutoCompleteService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/autocomplete")
public class AutoCompleteController {


    private AutoCompleteService autoCompleteService;

    @Autowired
    public AutoCompleteController(AutoCompleteService autoCompleteService){
        this.autoCompleteService=autoCompleteService;
    }



    //    검색어 자동완성 컨트롤
    @Tag(name = "자동 완성", description = "키워드에 따른 자동완성 결과를 반환하는 API")
    @Parameter(name = "keyword", description = "키워드 값", example = "딥")
    @GetMapping
    public ResponseData getContainKeyword(
            @RequestParam(value="keyword",required = true) String keyword){

        return autoCompleteService.findKeywords(keyword);
    }



}
