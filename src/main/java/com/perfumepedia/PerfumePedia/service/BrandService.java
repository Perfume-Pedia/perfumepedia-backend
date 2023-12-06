package com.perfumepedia.PerfumePedia.service;

import com.perfumepedia.PerfumePedia.domain.Brand;
import com.perfumepedia.PerfumePedia.repository.BrandRepository;
import com.perfumepedia.PerfumePedia.repository.WordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;

    /**
     * Create or Update or Nothing
     * <p> Brand 엔티티를 매개변수로 받아, 데이터베이스에 없는 엔티티라면 데이터베이스에 저장.
     * <p> 데이터베이스에 있고, 변경 사항이 있다면 update
     * <p> 데이터베이스에 있고, 변경 사항이 없다면 nothing
     * @param brand Brand 엔티티
     * @return 저장한(save or update or nothing) brand의 id
     */
    @Transactional(readOnly = false)
    public Long saveBrand(Brand brand) {
        try {
            validateDuplicateBrand(brand);  // 중복 검사
        } catch (IllegalStateException e) {

            // 데이터베이스에 값이 존재하고, 변경 사항이 있는 경우
            // updateBrand 호출
            return updateBrand(brand); // 업데이트 한 경우 기존 brand의 id return
        }

        // BrandRepository #save 이용 저장
        brandRepository.save(brand);  // 중복이 없을 경우에만 저장
        return brand.getId();  // 저장한 경우 brand의 id return
    }


    // 중복 검사
    private void validateDuplicateBrand(Brand brand) {
        brandRepository.findByName(brand.getName())
                .ifPresent(b->{
                    throw new IllegalStateException("이미 존재하는 브랜드입니다.");
                });
    }


    /**
     * Update
     * <p> Brand 엔티티를 매개변수로 받아, 변경사항 더티 체킹으로 수정
     * @param brand Brand 엔티티
     * @return 업데이트 한 Brand 객체의 id
     */
    @Transactional(readOnly = false)
    public Long updateBrand(Brand brand){

        Brand existingBrand = brandRepository.findByName(brand.getName())
                .orElseThrow(NullPointerException::new);
        // url과 updatedAt만 수정 가능
        // Domain #set 이용 변경사항 수정
        existingBrand.setUrl(brand.getUrl());
        existingBrand.getDBDate().setUpdatedAt(brand.getDBDate().getUpdatedAt());

        return existingBrand.getId(); // update한 객체 id 반환

    }
}

