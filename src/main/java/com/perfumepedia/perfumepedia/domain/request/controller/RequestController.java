package com.perfumepedia.perfumepedia.domain.request.controller;

import com.perfumepedia.perfumepedia.domain.perfume.service.RequestPerfumeService;
import com.perfumepedia.perfumepedia.domain.perfumeNote.dto.RequestPerfumeDetailReq;
import com.perfumepedia.perfumepedia.domain.request.service.RequestService;
import com.perfumepedia.perfumepedia.global.enums.NoneResponse;
import com.perfumepedia.perfumepedia.global.response.Response;
import com.perfumepedia.perfumepedia.global.response.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class RequestController {

    private final RequestPerfumeService requestPerfumeService;
    private final RequestService requestService;

    @Autowired
    public RequestController(RequestPerfumeService requestPerfumeService, RequestService requestService) {
        this.requestPerfumeService = requestPerfumeService;
        this.requestService = requestService;

    }

    /**
     * 향수 등록 요청 api
     */
    @PostMapping("/api/perfumes/users")
    public ResponseEntity<Response<NoneResponse>> registerPerfumeRequest(
            @RequestBody RequestPerfumeDetailReq reqPerfumeDetailReq,
            @RequestParam Long userId) {
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
            @RequestParam Long userId) {
        SuccessResponse<NoneResponse> response = requestPerfumeService.updatePerfumeRequest(reqPerfumeDetailReq, perfumeId, userId);
        return Response.success(response);
    }

    /**
     * 향수 삭제 요청 api
     */
    @PostMapping("/api/perfumes/users/{perfumeId}")
    public ResponseEntity<Response<NoneResponse>> deletePerfumeRequest(
            @PathVariable Long perfumeId,
            @RequestParam Long userId) {
        SuccessResponse<NoneResponse> response = requestPerfumeService.deletePerfumeRequest(perfumeId, userId);
        return Response.success(response);
    }

    /**
     * 향수 요청 개수 조회 api
     * */
    @GetMapping("/api/perfumes/admins/request-counts")
    public ResponseEntity<Response<Map<String, Long>>> getRequestCounts() {
        SuccessResponse<Map<String, Long>> response = requestService.getRequestCount();
        return Response.success(response);
    }


}
