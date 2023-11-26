package com.perfumepedia.PerfumePedia.service;


import com.perfumepedia.PerfumePedia.domain.Perfume;
import com.perfumepedia.PerfumePedia.repository.BrandRepository;
import com.perfumepedia.PerfumePedia.repository.PerfumeNoteRepository;
import com.perfumepedia.PerfumePedia.repository.PerfumeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import static org.apache.catalina.authenticator.jaspic.PersistentProviderRegistrations.log;

@Service

@Slf4j
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
    //@Slf4j //log
    @Transactional(readOnly = false)
    public Long savePerfume(Perfume perfume){
        try {
            validateDuplicatePerfume(perfume);// 중복 검사

        } catch (IllegalStateException e) {
            // 데이터베이스에 값이 존재하고, 변경 사항이 있는 경우
            // updatePerfume 호출
            log.info(e.getMessage());
            return updatePerfume(perfume); // 업데이트 한 경우 기존 perfume의 id return
        }

        // PerfumeRepository #save 이용 저장
        perfumeRepository.save(perfume);
        return perfume.getId();   // 저장한 경우 perfume의 id return

    }

    private void validateDuplicatePerfume(Perfume perfume) {
        perfumeRepository.findByName(perfume.getName())
                .ifPresent(p->{
                    throw new IllegalStateException("이미 존재하는 향수입니다.");
                });
    }

    /**
     * Update
     * <p> Perfume 엔티티를 매개변수로 받아, 변경사항 더티 체킹으로 수정
     * @param perfume Perfume 엔티티
     * @return 업데이트 한 Perfume 객체의 id
     */
    @Transactional(readOnly = false)
    public Long updatePerfume(Perfume perfume){

        Perfume existingPerfume = perfumeRepository.findByName(perfume.getName())
                .orElseThrow(NullPointerException::new);

        // Domain #set 이용 변경사항 수정
        // price, url, discontinue, updatedAt만 수정 가능
        existingPerfume.setPrice(perfume.getPrice());
        existingPerfume.setUrl(perfume.getUrl());
        existingPerfume.getDbDate().setUpdatedAt(perfume.getDbDate().getUpdatedAt());

        // update한 객체 id 반환
        return existingPerfume.getId();
    }

    //@Slf4j //log
    @Transactional(readOnly = false)
    public void updateDiscontinue(Perfume perfume){
        try {
            validateNonExistentPerfume(perfume);//중복검사
        } catch (IllegalStateException e) {//해당 함수가 존재하지 않을 경우
        }
        Perfume existingPerfume = perfumeRepository.findByName(perfume.getName())
                .orElseThrow(NullPointerException::new);

        existingPerfume.setDiscontinue();
    }
    private void validateNonExistentPerfume(Perfume perfume) {
        perfumeRepository.findByName(perfume.getName())
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 향수입니다."));
    }


}