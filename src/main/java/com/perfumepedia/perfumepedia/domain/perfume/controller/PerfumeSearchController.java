package com.perfumepedia.perfumepedia.domain.perfume.controller;

import com.perfumepedia.perfumepedia.domain.perfume.dto.PerfumeUpdateReq;
import com.perfumepedia.perfumepedia.domain.perfume.service.PerfumeService;
import com.perfumepedia.perfumepedia.domain.perfumeNote.dto.PerfumeSearchDetail;
import com.perfumepedia.perfumepedia.global.response.Response;
import com.perfumepedia.perfumepedia.global.response.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "향수 검색", description = "향수 검색 관련 API")
public class PerfumeSearchController {

    private final PerfumeService perfumeService;


    @GetMapping("/search")
    @Operation(summary = "향수 검색", description = "입력한 keyword와 일치하는 향수의 목록이 검색됩니다.")
    public ResponseEntity<Response<List<PerfumeUpdateReq>>> searchPerfumes(@RequestParam String keyword) {
        SuccessResponse<List<PerfumeUpdateReq>> successResponse = perfumeService.searchPerfumes(keyword);
        return Response.success(successResponse);
    }


    @Operation(summary = "향수 세부정보 검색", description = "선택한 향수의 세부정보가 조회됩니다.")
    @GetMapping("/search/{perfumeId}")
    public ResponseEntity<Response<PerfumeSearchDetail>> getPerfumeDetail(@PathVariable Long perfumeId) {
        SuccessResponse<PerfumeSearchDetail> response = perfumeService.getPerfumeDetail(perfumeId);
        return Response.success(response);
    }


}
