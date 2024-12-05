package com.perfumepedia.perfumepedia.domain.request.controller;

import com.perfumepedia.perfumepedia.domain.perfume.service.RequestPerfumeService;
import com.perfumepedia.perfumepedia.domain.perfumeNote.dto.PerfumeDetailResponse;
import com.perfumepedia.perfumepedia.domain.perfumeNote.dto.RequestPerfumeDetailReq;
import com.perfumepedia.perfumepedia.domain.perfumeNote.service.RequestPerfumeNoteService;
import com.perfumepedia.perfumepedia.domain.request.dto.RequestListDto;
import com.perfumepedia.perfumepedia.domain.request.service.RequestService;
import com.perfumepedia.perfumepedia.global.enums.NoneResponse;
import com.perfumepedia.perfumepedia.global.response.Response;
import com.perfumepedia.perfumepedia.global.response.SuccessResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Tag(name = "향수 요청", description = "향수 요청 관련 API")

public class RequestController {

    private final RequestPerfumeService requestPerfumeService;
    private final RequestService requestService;
    private final RequestPerfumeNoteService requestPerfumeNoteService;

    @Autowired
    public RequestController(RequestPerfumeService requestPerfumeService, RequestService requestService, RequestPerfumeNoteService requestPerfumeNoteService) {
        this.requestPerfumeService = requestPerfumeService;
        this.requestService = requestService;
        this.requestPerfumeNoteService = requestPerfumeNoteService;

    }

    /**
     * 향수 등록 요청 api
     */
    @PostMapping("/api/perfumes/users")
    public ResponseEntity<Response<NoneResponse>> registerPerfumeRequest(
            @RequestBody RequestPerfumeDetailReq reqPerfumeDetailReq,
            @AuthenticationPrincipal Long userId) {
        SuccessResponse<NoneResponse> response = requestPerfumeService.registerPerfumeRequest(reqPerfumeDetailReq, userId);
        return Response.success(response);
    }


    /**
     * 향수 수정 요청 api
     */
    @PostMapping("/api/perfumes/users/{perfumeId}")
    public ResponseEntity<Response<NoneResponse>> updatePerfumeRequest(
            @RequestBody RequestPerfumeDetailReq reqPerfumeDetailReq,
            @PathVariable Long perfumeId,
            @AuthenticationPrincipal Long userId) {
        SuccessResponse<NoneResponse> response = requestPerfumeService.updatePerfumeRequest(reqPerfumeDetailReq, perfumeId, userId);
        return Response.success(response);
    }

    /**
     * 향수 삭제 요청 api
     */
    @PostMapping("/api/perfumes/users/{perfumeId}")
    public ResponseEntity<Response<NoneResponse>> deletePerfumeRequest(
            @PathVariable Long perfumeId,
            @AuthenticationPrincipal Long userId) {
        SuccessResponse<NoneResponse> response = requestPerfumeService.deletePerfumeRequest(perfumeId, userId);
        return Response.success(response);
    }

    /**
     * 향수 요청 개수 조회 api
     */
    @GetMapping("/api/perfumes/admins/request-counts")
    public ResponseEntity<Response<Map<String, Long>>> getRequestCounts() {
        SuccessResponse<Map<String, Long>> response = requestService.getRequestCount();
        return Response.success(response);
    }

    /**
     * 등록 요청 목록 조회
     */
    @GetMapping("/api/perfumes/admins/requests/register")
    public ResponseEntity<Response<List<RequestListDto>>> getRegisterRequests() {
        SuccessResponse<List<RequestListDto>> response = requestService.getRegisterRequests();
        return Response.success(response);
    }

    /**
     * 수정 요청 목록 조회
     */
    @GetMapping("/api/perfumes/admins/requests/update")
    public ResponseEntity<Response<List<RequestListDto>>> getUpdateRequests() {
        SuccessResponse<List<RequestListDto>> response = requestService.getUpdateRequests();
        return Response.success(response);
    }

    /**
     * 삭제 요청 목록 조회
     */
    @GetMapping("/api/perfumes/admins/requests/delete")
    public ResponseEntity<Response<List<RequestListDto>>> getDeleteRequests() {
        SuccessResponse<List<RequestListDto>> response = requestService.getDeleteRequests();
        return Response.success(response);
    }

    /**
     *  신규 향수 상세 조회 (등록, 수정)
     */
    @GetMapping("/api/perfumes/admins/register/{perfumeId}")
    public ResponseEntity<Response<PerfumeDetailResponse>> getRegisterRequestDetail(
            @RequestParam String requestType,
            @PathVariable Long perfumeId
    ) {
        SuccessResponse<PerfumeDetailResponse> response = requestPerfumeNoteService.getRegisterRequestDetail(perfumeId, requestType);
        return Response.success(response);
    }

    /**
     * 기존 요청 상세 조회(삭제, 수정)
     */
    @GetMapping("/api/perfumes/admins/update")
    public ResponseEntity<Response<PerfumeDetailResponse>> getUpdateRequestDetail(
            @RequestParam String requestType,
            @PathVariable Long perfumeId
    ) {
        SuccessResponse<PerfumeDetailResponse> response = requestPerfumeNoteService.getDeleteRequestDetail(perfumeId, requestType);
        return Response.success(response);
    }

}
