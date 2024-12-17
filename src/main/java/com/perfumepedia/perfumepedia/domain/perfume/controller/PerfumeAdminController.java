package com.perfumepedia.perfumepedia.domain.perfume.controller;

import com.perfumepedia.perfumepedia.domain.brand.dto.BrandAndPerfumeCountDto;
import com.perfumepedia.perfumepedia.domain.brand.service.BrandService;
import com.perfumepedia.perfumepedia.domain.perfume.service.PerfumeService;
import com.perfumepedia.perfumepedia.domain.perfume.service.RequestPerfumeService;
import com.perfumepedia.perfumepedia.domain.perfumeNote.dto.PerfumeDetailResponse;
import com.perfumepedia.perfumepedia.domain.perfumeNote.dto.RequestPerfumeDetailReq;
import com.perfumepedia.perfumepedia.domain.perfumeNote.service.RequestPerfumeNoteService;
import com.perfumepedia.perfumepedia.domain.request.dto.RequestListDto;
import com.perfumepedia.perfumepedia.domain.request.service.RequestService;
import com.perfumepedia.perfumepedia.global.enums.NoneResponse;
import com.perfumepedia.perfumepedia.global.response.Response;
import com.perfumepedia.perfumepedia.global.response.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/perfumes/admins") // 공통 URL 경로 앞으로 빼기
@Tag(name = "향수 관리", description = "관리자의 향수 관리 관련 API")
public class PerfumeAdminController {

    private final BrandService brandService;
    private final RequestPerfumeService requestPerfumeService;
    private final PerfumeService perfumeService;
    private final RequestService requestService;
    private final RequestPerfumeNoteService requestPerfumeNoteService;


    @Operation(summary = "브랜드와 향수 개수 조회", description = "등록 되어있는 브랜드와 향수의 개수를 조회합니다.")
    @GetMapping("/counts")
    public ResponseEntity<Response<BrandAndPerfumeCountDto>> getBrandAndPerfumeCount() {
        SuccessResponse<BrandAndPerfumeCountDto> response = brandService.BrandAndPerfumeCount();
        return Response.success(response);
    }

    @Operation(summary = "향수 등록", description = "관리자가 향수의 정보를 입력해 등록합니다.")
    @PostMapping
    public ResponseEntity<Response<NoneResponse>> registerPerfume(@RequestBody RequestPerfumeDetailReq requestPerfumeDetailReq) {
        SuccessResponse<NoneResponse> response = requestPerfumeService.registerPerfume(requestPerfumeDetailReq);
        return Response.success(response);
    }


    @Operation(summary = "향수 수정", description = "관리자가 등록되어 있는 향수의 정보를 수정합니다.")
    @PutMapping("/{perfumeId}")
    public ResponseEntity<Response<NoneResponse>> updatePerfume(@RequestBody RequestPerfumeDetailReq requestPerfumeDetailReq, @PathVariable Long perfumeId) {
        SuccessResponse<NoneResponse> response = requestPerfumeService.updatePerfume(requestPerfumeDetailReq, perfumeId);
        return Response.success(response);
    }


    @Operation(summary = "향수 삭제", description = "관리자가 등록되어 있는 향수를 삭제합니다.")
    @DeleteMapping("/{perfumeId}")
    public ResponseEntity<Response<NoneResponse>> deletePerfume(@PathVariable Long perfumeId) {
        SuccessResponse<NoneResponse> response = requestPerfumeService.deletePerfume(perfumeId);
        return Response.success(response);
    }


    @Operation(summary = "향수 요청 개수 조회", description = "관리자가 등록된 향수 요청개수를 조회합니다.")
    @GetMapping("/request-counts")
    public ResponseEntity<Response<Map<String, Long>>> getRequestCounts() {
        SuccessResponse<Map<String, Long>> response = requestService.getRequestCount();
        return Response.success(response);
    }


    @Operation(summary = "등록 요청 목록 조회", description = "관리자가 등록 요청된 향수의 목록을 조회합니다.")
    @GetMapping("/requests/register")
    public ResponseEntity<Response<List<RequestListDto>>> getRegisterRequests() {
        SuccessResponse<List<RequestListDto>> response = requestService.getRegisterRequests();
        return Response.success(response);
    }


    @Operation(summary = "수정 요청 목록 조회", description = "관리자가 수정 요청된 향수의 목록을 조회합니다.")
    @GetMapping("/requests/update")
    public ResponseEntity<Response<List<RequestListDto>>> getUpdateRequests() {
        SuccessResponse<List<RequestListDto>> response = requestService.getUpdateRequests();
        return Response.success(response);
    }


    @Operation(summary = "삭제 요청 목록 조회", description = "관리자가 삭제 요청된 향수의 목록을 조회합니다.")
    @GetMapping("/requests/delete")
    public ResponseEntity<Response<List<RequestListDto>>> getDeleteRequests() {
        SuccessResponse<List<RequestListDto>> response = requestService.getDeleteRequests();
        return Response.success(response);
    }


    @Operation(summary = "등록 요청 향수 상세 조회", description = "관리자가 등록 요청된 향수의 세부정보를 조회합니다.(수정 요청조회에도 사용)")
    @GetMapping("/register/{perfumeId}")
    public ResponseEntity<Response<PerfumeDetailResponse>> getRegisterRequestDetail(
            @RequestParam String requestType,
            @PathVariable Long requestId
    ) {
        SuccessResponse<PerfumeDetailResponse> response = requestPerfumeNoteService.getRegisterRequestDetail(requestId, requestType);
        return Response.success(response);
    }


    @Operation(summary = "삭제 요청 상세 조회", description = "관리자가 삭제 요청된 향수의 세부정보를 조회합니다.(수정 요청조회에도 사용)")
    @GetMapping("/update/{perfumeId}")
    public ResponseEntity<Response<PerfumeDetailResponse>> getUpdateRequestDetail(
            @RequestParam String requestType,
            @PathVariable Long requestId
    ) {
        SuccessResponse<PerfumeDetailResponse> response = requestPerfumeNoteService.getDeleteRequestDetail(requestId, requestType);
        return Response.success(response);
    }


    @Operation(summary = "요청 수락", description = "관리자가 요청을 수락합니다.")
    @PostMapping("/{requestId}/accept")
    public ResponseEntity<Response<NoneResponse>> acceptPerfumeRequest(@PathVariable Long requestId
//            ,@AuthenticationPrincipal User user
    ) { // 추후에 User 받아와야함
//        SuccessResponse<NoneResponse> response = perfumeService.acceptPerfumeRequest(requestId, user.role);
        SuccessResponse<NoneResponse> response = perfumeService.acceptPerfumeRequest(requestId);
        return Response.success(response);
    }


    @Operation(summary = "요청 거절", description = "관리자가 요청을 거절합니다.")
    @PostMapping("/{requestId}/reject")
    public ResponseEntity<Response<NoneResponse>> rejectPerfumeRequest(@PathVariable Long requestId) {
        SuccessResponse<NoneResponse> response = perfumeService.rejectPerfumeRequest(requestId);
        return Response.success(response);
    }


}
