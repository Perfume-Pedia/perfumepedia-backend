package com.perfumepedia.perfumepedia.domain.brand.service;

import com.perfumepedia.perfumepedia.domain.brand.dto.BrandAndPerfumeCountDto;
import com.perfumepedia.perfumepedia.domain.brand.repository.BrandRepository;
import com.perfumepedia.perfumepedia.domain.perfume.repository.PerfumeRepository;
import com.perfumepedia.perfumepedia.global.response.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.perfumepedia.perfumepedia.global.enums.SuccessCode.REQUEST_COMPLETED;

@Service
public class BrandService {

    private final BrandRepository brandRepository;
    private final PerfumeRepository perfumeRepository;


    @Autowired
    public BrandService(BrandRepository brandRepository, PerfumeRepository perfumeRepository) {
        this.brandRepository = brandRepository;
        this.perfumeRepository = perfumeRepository;
    }

    /**
     * 저장되어있는 브랜드와 향수의 개수를 반환하는 메서드
     */
    public SuccessResponse<BrandAndPerfumeCountDto> BrandAndPerfumeCount() {

        Long brandCount = brandRepository.count();
        Long perfumeCount = perfumeRepository.count();

        BrandAndPerfumeCountDto brandAndPerfumeCountDto = BrandAndPerfumeCountDto.builder()
                .brandCount(brandCount)
                .perfumeCount(perfumeCount)
                .build();

        return new SuccessResponse<>(REQUEST_COMPLETED, brandAndPerfumeCountDto);
    }


}
