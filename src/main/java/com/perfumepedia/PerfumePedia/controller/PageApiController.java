package com.perfumepedia.PerfumePedia.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class PageApiController {

    @GetMapping
    public String homePage(){
        return "home";
    }

    @GetMapping("note")
    public String notePage(){
        return "note";
    }

    @GetMapping("brand")
    public String brandPage(){
        return "brand";
    }

    @GetMapping("search")
    public String searchPage(
            @RequestParam(required = true) String keyword,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "1") int page){


        return "search"; // view 이름수정할 수도 있음
    }

}

