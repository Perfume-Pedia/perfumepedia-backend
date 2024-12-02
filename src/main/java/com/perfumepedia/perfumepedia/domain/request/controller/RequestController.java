package com.perfumepedia.perfumepedia.domain.request.controller;

import com.perfumepedia.perfumepedia.domain.perfume.service.RequestPerfumeService;
import com.perfumepedia.perfumepedia.domain.perfumeNote.dto.RequestPerfumeDetailReq;
import com.perfumepedia.perfumepedia.domain.request.service.RequestService;
import com.perfumepedia.perfumepedia.global.enums.NoneResponse;
import com.perfumepedia.perfumepedia.global.response.Response;
import com.perfumepedia.perfumepedia.global.response.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestController {

    private final RequestPerfumeService requestPerfumeService;

    @Autowired
    public RequestController(RequestPerfumeService requestPerfumeService) {
        this.requestPerfumeService = requestPerfumeService;
    }

    /**
     * 향수 등록 요청 api
     */
    @PostMapping("/api/perfume/users")
    public ResponseEntity<Response<NoneResponse>> registerPerfumeRequest(
            @RequestBody RequestPerfumeDetailReq reqPerfumeDetailReq,
            @RequestParam Long userId) {
        SuccessResponse<NoneResponse> response = requestPerfumeService.registerPerfumeRequest(reqPerfumeDetailReq, userId);
        return Response.success(response);
    }


    /**
     * 향수 수정 요청 api
     */
    @PostMapping("/api/perfume/perfumeId")
    public ResponseEntity<Response<NoneResponse>> updatePerfumeRequest(
            @RequestBody RequestPerfumeDetailReq reqPerfumeDetailReq,
            @RequestParam Long perfumeId,
            @RequestParam Long userId) {
        SuccessResponse<NoneResponse> response = requestPerfumeService.updatePerfumeRequest(reqPerfumeDetailReq, perfumeId, userId);
        return Response.success(response);
    }

    /**
     * 향수 삭제 요청 api
     */
    @PostMapping("/api/perfume/perfumeId")
    public ResponseEntity<Response<NoneResponse>> deletePerfumeRequest(
            @RequestParam Long perfumeId,
            @RequestParam Long userId) {
        SuccessResponse<NoneResponse> response = requestPerfumeService.deletePerfumeRequest(perfumeId, userId);
        return Response.success(response);
    }


}
