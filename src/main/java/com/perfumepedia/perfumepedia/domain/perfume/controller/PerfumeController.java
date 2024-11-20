package com.perfumepedia.perfumepedia.domain.perfume.controller;

import com.perfumepedia.perfumepedia.domain.perfume.dto.PerfumeUpdateReq;
import com.perfumepedia.perfumepedia.domain.perfume.service.PerfumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PerfumeController {

    private final PerfumeService perfumeService;

    @Autowired
    public PerfumeController(PerfumeService perfumeService) {
        this.perfumeService = perfumeService;
    }

    /**
     * 향수 검색 API (브랜드, 향수 이름, 노트 이름으로 검색)
     * @param keyword 검색어
     * @return 검색된 향수 리스트
     */
    @GetMapping("/api/searchs")
    public ResponseEntity<List<PerfumeUpdateReq>> searchPerfumes(@RequestParam String keyword) {
        return ResponseEntity.ok(perfumeService.searchPerfumes(keyword));
    }


}
