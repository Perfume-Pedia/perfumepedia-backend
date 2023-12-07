package com.perfumepedia.PerfumePedia.controller;

import com.perfumepedia.PerfumePedia.dto.ResponseData;
import com.perfumepedia.PerfumePedia.service.AutoCompleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/autocomplete")
public class AutoCompleteController {


    private AutoCompleteService autoCompleteService;

    @Autowired
    public AutoCompleteController(AutoCompleteService autoCompleteService){
        this.autoCompleteService=autoCompleteService;
    }



//    검색어 자동완성 컨트롤
    @GetMapping
    public ResponseData getContainKeyword(
            @RequestParam(value="keyword",required = true) String keyword){

        return autoCompleteService.findKeywords(keyword);
    }



}
