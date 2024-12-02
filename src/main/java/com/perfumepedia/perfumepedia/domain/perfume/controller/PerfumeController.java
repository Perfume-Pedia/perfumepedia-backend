package com.perfumepedia.perfumepedia.domain.perfume.controller;

import com.perfumepedia.perfumepedia.domain.perfume.dto.PerfumeUpdateReq;
import com.perfumepedia.perfumepedia.domain.perfume.service.PerfumeService;
import com.perfumepedia.perfumepedia.domain.perfumeNote.dto.PerfumeDetailResponse;
import com.perfumepedia.perfumepedia.global.response.Response;
import com.perfumepedia.perfumepedia.global.response.SuccessResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "향수 검색", description = "향수 검색 관련 API")
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
    public ResponseEntity<Response<List<PerfumeUpdateReq>>> searchPerfumes(@RequestParam String keyword) {
        SuccessResponse<List<PerfumeUpdateReq>> successResponse = perfumeService.searchPerfumes(keyword);
        return Response.success(successResponse);
    }


    /**
     * 향수 세부정보 조회 API (
     * @param perfumeId 향수 아이디
     * @return 검색된 향수 세부정보
     */
    @GetMapping("/api/search/{id}")
    public ResponseEntity<Response<PerfumeDetailResponse>> getPerfumeDetail(@PathVariable Long perfumeId) {
        SuccessResponse<PerfumeDetailResponse> response = perfumeService.getPerfumeDetail(perfumeId);
        return Response.success(response);
    }


}
