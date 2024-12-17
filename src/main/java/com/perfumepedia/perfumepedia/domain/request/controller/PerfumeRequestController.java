package com.perfumepedia.perfumepedia.domain.request.controller;

import com.perfumepedia.perfumepedia.domain.perfume.service.RequestPerfumeService;
import com.perfumepedia.perfumepedia.domain.perfumeNote.dto.RequestPerfumeDetailReq;
import com.perfumepedia.perfumepedia.global.enums.NoneResponse;
import com.perfumepedia.perfumepedia.global.response.Response;
import com.perfumepedia.perfumepedia.global.response.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@Tag(name = "향수 요청", description = "유저의 향수 요청 관련 API")
public class PerfumeRequestController {

    private final RequestPerfumeService requestPerfumeService;


    @Operation(summary = "향수 등록 요청", description = "유저가 향수의 정보를 작성해 등록 요청합니다.")
    @PostMapping("/perfumes/users")
    public ResponseEntity<Response<NoneResponse>> registerPerfumeRequest(
            @RequestBody RequestPerfumeDetailReq reqPerfumeDetailReq
//            @AuthenticationPrincipal Long userId
    ) {
        SuccessResponse<NoneResponse> response = requestPerfumeService.registerPerfumeRequest(reqPerfumeDetailReq, 1L);
        return Response.success(response);
    }


    @Operation(summary = "향수 수정 요청", description = "유저가 향수의 정보를 작성해 수정 요청합니다.")
    @PostMapping("/perfumes/users/{perfumeId}/update")
    public ResponseEntity<Response<NoneResponse>> updatePerfumeRequest(
            @RequestBody RequestPerfumeDetailReq reqPerfumeDetailReq,
            @PathVariable Long perfumeId
//            @AuthenticationPrincipal Long userId
    ) {
        SuccessResponse<NoneResponse> response = requestPerfumeService.updatePerfumeRequest(reqPerfumeDetailReq, perfumeId, 1L);
        return Response.success(response);
    }


    @Operation(summary = "향수 삭제 요청", description = "유저가 삭제를 원하는 향수를 삭제 요청합니다.")
    @PostMapping("/perfumes/users/{perfumeId}/delete")
    public ResponseEntity<Response<NoneResponse>> deletePerfumeRequest(
            @PathVariable Long perfumeId
//            @AuthenticationPrincipal Long userId
    ) {
        SuccessResponse<NoneResponse> response = requestPerfumeService.deletePerfumeRequest(perfumeId, 1L);
        return Response.success(response);
    }


}
