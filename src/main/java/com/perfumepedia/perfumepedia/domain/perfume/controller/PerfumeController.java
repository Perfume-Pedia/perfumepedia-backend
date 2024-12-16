package com.perfumepedia.perfumepedia.domain.perfume.controller;

import com.perfumepedia.perfumepedia.domain.perfume.dto.PerfumeUpdateReq;
import com.perfumepedia.perfumepedia.domain.perfume.service.PerfumeService;
import com.perfumepedia.perfumepedia.domain.perfume.service.RequestPerfumeService;
import com.perfumepedia.perfumepedia.domain.perfumeNote.dto.PerfumeDetailResponse;
import com.perfumepedia.perfumepedia.domain.perfumeNote.dto.PerfumeSearchDetail;
import com.perfumepedia.perfumepedia.domain.perfumeNote.dto.RequestPerfumeDetailReq;
import com.perfumepedia.perfumepedia.global.enums.NoneResponse;
import com.perfumepedia.perfumepedia.global.response.Response;
import com.perfumepedia.perfumepedia.global.response.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "향수 검색", description = "향수 검색 관련 API")
public class PerfumeController {

    private final PerfumeService perfumeService;
    private final RequestPerfumeService requestPerfumeService;

    @Autowired
    public PerfumeController(PerfumeService perfumeService, RequestPerfumeService requestPerfumeService) {
        this.perfumeService = perfumeService;
        this.requestPerfumeService = requestPerfumeService;
    }

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


    @Operation(summary = "향수 등록", description = "관리자가 향수의 정보를 입력해 등록합니다.")
    @PostMapping("/perfumes/admins")
    public ResponseEntity<Response<NoneResponse>> registerPerfume(@RequestBody RequestPerfumeDetailReq requestPerfumeDetailReq) {
        SuccessResponse<NoneResponse> response = requestPerfumeService.registerPerfume(requestPerfumeDetailReq);
        return Response.success(response);
    }


    @Operation(summary = "향수 수정", description = "관리자가 등록되어 있는 향수의 정보를 수정합니다.")
    @PutMapping("/perfumes/admins/{perfumeId}")
    public ResponseEntity<Response<NoneResponse>> updatePerfume(@RequestBody RequestPerfumeDetailReq requestPerfumeDetailReq, @PathVariable Long perfumeId) {
        SuccessResponse<NoneResponse> response = requestPerfumeService.updatePerfume(requestPerfumeDetailReq, perfumeId);
        return Response.success(response);
    }


    @Operation(summary = "향수 삭제", description = "관리자가 등록되어 있는 향수를 삭제합니다.")
    @DeleteMapping("/perfumes/admins/{perfumeId}")
    public ResponseEntity<Response<NoneResponse>> deletePerfume(@PathVariable Long perfumeId) {
        SuccessResponse<NoneResponse> response = requestPerfumeService.deletePerfume(perfumeId);
        return Response.success(response);
    }


}
