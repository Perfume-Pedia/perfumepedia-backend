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
    public String homePage(){//메인 페이지
        return "home";
    }

    @GetMapping("note")
    public String notePage(){//노트 페이지 (정적 컨텐츠)
        return "note";
    }

    @GetMapping("brand")
    public String brandPage(){//브랜드 페이지 (정적 컨텐츠)
        return "brand";
    }

    @GetMapping("search")
    public String searchPage(//검색 결과 페이지 (api)
            @RequestParam(required = true) String keyword,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "1") int page){

        return "search"; // view 이름수정할 수도 있음
    }

    @GetMapping("my")
    public String myPage(){//마이 페이지_북마크 출력
        return "search";
    }

}

