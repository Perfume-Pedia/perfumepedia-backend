package com.perfumepedia.PerfumePedia.domain.service;

import com.perfumepedia.PerfumePedia.domain.entity.Word;
import com.perfumepedia.PerfumePedia.domain.repository.WordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = false)
@RequiredArgsConstructor
public class WordService {

    private final WordRepository wordRepository;

    /**
     * Create or Nothing
     * <p> Word 엔티티를 매개변수로 받아, 데이터베이스에 없는 엔티티라면 데이터베이스에 저장.
     * <p> 데이터베이스에 있다면 nothing
     * @param word Word 엔티티
     * @return 저장한(save or nothing) word id
     */
    @Transactional(readOnly = false)
    public Long saveWord(Word word){
        // 데이터베이스에 값이 존재하지 않는 경우
        // WordRepository #save 이용 저장

        // 데이터베이스에 값이 존재하는 경우
        // 처리하지 않음

        // 저장한 경우 note의 id return

        return null;
    }

    /**
     * Update
     * <p> Word 엔티티를 매개변수로 받아, weight 더티 체킹으로 수정
     * @param word Word 엔티티
     * @return 업데이트 한 Word 객체의 id
     */
    @Transactional(readOnly = false)
    public Long increaseWeight(Word word){
        // Domain #set 이용 변경사항 수정
        // weight, updatedAt만 수정 가능
        // weight +1 증가

        // update한 객체 id 반환
        return null;
    }
}
