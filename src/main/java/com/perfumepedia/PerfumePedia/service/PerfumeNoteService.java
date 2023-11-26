package com.perfumepedia.PerfumePedia.service;


import com.perfumepedia.PerfumePedia.domain.Brand;
import com.perfumepedia.PerfumePedia.domain.PerfumeNote;
import com.perfumepedia.PerfumePedia.repository.PerfumeNoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PerfumeNoteService {

    private final PerfumeNoteRepository perfumeNoteRepository;

    /**
     * Create or Nothing
     * <p> PerfumeNote 엔티티를 매개변수로 받아, 데이터베이스에 없는 엔티티라면 데이터베이스에 저장.
     * <p> 데이터베이스에 있다면 nothing
     * @param perfumeNote PerfumeNote 엔티티
     * @return 저장한(save or update or nothing) perfumeNote의 id
     */
    @Transactional(readOnly = false)
    public Long savePerfumeNote(PerfumeNote perfumeNote) {
        // 데이터베이스에 값이 존재하지 않는 경우
        // PerfumeNoteRepository #save 이용 저장

        // 데이터베이스에 값이 존재하는 경우
        // 처리하지 않음

        // 저장한 경우 perfumeNote의 id return
        return null;
    }
}
