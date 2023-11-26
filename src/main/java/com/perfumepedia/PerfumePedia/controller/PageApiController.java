package com.perfumepedia.PerfumePedia.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class PageApiController {

    @GetMapping
    public String homePage(){//메인 페이지
        return "Main";
    }

    @GetMapping("notes")
    public String notePage(){//노트 페이지 (정적 컨텐츠)
        return "Notes";
    }

    @GetMapping("brands")
    public String brandPage(){//브랜드 페이지 (정적 컨텐츠)
        return "Perfumes";
    }

    @GetMapping("api/search")
    public String searchPage(//검색 결과 페이지 (api)
            @RequestParam(required = true) String keyword,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "1") int page){

        return "Search"; // view 이름수정할 수도 있음
    }


    @GetMapping("api/search/advance")
    public String searchDetailPage(@RequestParam String uuid) {
        return "PerfumeDetail";
    }

    @GetMapping("api/favperfume")
    public String myPage(
            @RequestParam(required = true) String keyword,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "1") int page){

        return "Search"; // view 이름수정할 수도 있음
    }

    @GetMapping("api/favperfume/advance")
    public String myPerfumeDetailPage(@RequestParam String uuid){
        return "PerfumeDetail";
    }
}

