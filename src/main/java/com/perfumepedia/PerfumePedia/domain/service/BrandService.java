package com.perfumepedia.PerfumePedia.domain.service;

import com.perfumepedia.PerfumePedia.domain.repository.BrandRepository;
import com.perfumepedia.PerfumePedia.domain.entity.Brand;
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
        // 데이터베이스에 값이 존재하지 않는 경우
        // BrandRepository #save 이용 저장

        // 데이터베이스에 값이 존재하고, 변경 사항이 있는 경우
        // updateBrand 호출

        // 데이터베이스에 값이 존재하고, 변경 사항이 없는 경우
        // 처리하지 않음

        // 저장한 경우 brand의 id return
        // 업데이트 한 경우 기존 brand의 id return
        // nothing 인 경우 기존 brand의 id return
        return null;
    }

    /**
     * Update
     * <p> Brand 엔티티를 매개변수로 받아, 변경사항 더티 체킹으로 수정
     * @param brand Brand 엔티티
     * @return 업데이트 한 Brand 객체의 id
     */
    @Transactional(readOnly = false)
    public Long updateBrand(Brand brand){
        // Domain #set 이용 변경사항 수정
        // url과 updatedAt만 수정 가능

        // update한 객체 id 반환
        return null;
    }
}
