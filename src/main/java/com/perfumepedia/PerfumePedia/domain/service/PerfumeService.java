package com.perfumepedia.PerfumePedia.domain.service;


import com.perfumepedia.PerfumePedia.domain.entity.Perfume;
import com.perfumepedia.PerfumePedia.domain.repository.BrandRepository;
import com.perfumepedia.PerfumePedia.domain.repository.PerfumeNoteRepository;
import com.perfumepedia.PerfumePedia.domain.repository.PerfumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PerfumeService {

    private final PerfumeRepository perfumeRepository;
    private final BrandRepository brandRepository;
    private final PerfumeNoteRepository perfumeNoteRepository;

    /**
     * Create or Update or Nothing
     * <p> Perfume 엔티티를 매개변수로 받아, 데이터베이스에 없는 엔티티라면 데이터베이스에 저장.
     * <p> 데이터베이스에 있고, 변경 사항이 있다면 update
     * <p> 데이터베이스에 있고, 변경 사항이 없다면 nothing
     * @param perfume Perfume 엔티티
     * @return 저장한(save or update or nothing) perfume id
     */
    @Transactional(readOnly = false)
    public Long savePerfume(Perfume perfume){
        // 데이터베이스에 값이 존재하지 않는 경우
        // PerfumeRepository #save 이용 저장

        // 데이터베이스에 값이 존재하고, 변경 사항이 있는 경우
        // updatePerfume 호출

        // 데이터베이스에 값이 존재하고, 변경 사항이 없는 경우
        // 처리하지 않음

        // 저장한 경우 perfume의 id return
        // 업데이트 한 경우 기존 perfume의 id return
        // nothing 인 경우 기존 perfume의 id return
        return null;
    }

    /**
     * Update
     * <p> Perfume 엔티티를 매개변수로 받아, 변경사항 더티 체킹으로 수정
     * @param perfume Perfume 엔티티
     * @return 업데이트 한 Perfume 객체의 id
     */
    @Transactional(readOnly = false)
    public Long updatePerfume(Perfume perfume){
        // Domain #set 이용 변경사항 수정
        // price, url, discontinue, updatedAt만 수정 가능

        // update한 객체 id 반환
        return null;
    }
}
