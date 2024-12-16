package com.perfumepedia.perfumepedia.domain.brand.controller;

import com.perfumepedia.perfumepedia.domain.brand.dto.BrandAndPerfumeCountDto;
import com.perfumepedia.perfumepedia.domain.brand.service.BrandService;
import com.perfumepedia.perfumepedia.global.response.Response;
import com.perfumepedia.perfumepedia.global.response.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Tag(name = "관리자 대시보드", description = "관리자 대시보드 관련 API")

public class BrandController {

    private final BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    /**
     * 모든 브랜드와 향수 개수 조회 api
     */
    @Operation(summary = "브랜드와 향수 개수 조회", description = "등록 되어있는 브랜드와 향수의 개수를 조회합니다.")
    @GetMapping("/perfumes/admins/counts")
    public ResponseEntity<Response<BrandAndPerfumeCountDto>> getBrandAndPerfumeCount() {
        SuccessResponse<BrandAndPerfumeCountDto> response = brandService.BrandAndPerfumeCount();
        return Response.success(response);
    }


}
