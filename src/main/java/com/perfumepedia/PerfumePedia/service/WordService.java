package com.perfumepedia.PerfumePedia.service;

import com.perfumepedia.PerfumePedia.domain.Brand;
import com.perfumepedia.PerfumePedia.domain.Word;
import com.perfumepedia.PerfumePedia.repository.WordRepository;
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
        try {
            validateDuplicateWord(word);
        } catch (IllegalStateException e) {
            // 데이터베이스에 값이 존재하는 경우
            // 처리하지 않음
            return wordRepository.findByAliasAndName(word.getAlias(), word.getName()).get().getId();
        }
        wordRepository.save(word);  // WordRepository #save 이용 저장
        return word.getId();  // 저장한 경우 note의 id return

    }

    private void validateDuplicateWord(Word word) {
        wordRepository.findByAliasAndName(word.getAlias(), word.getName())
                .ifPresent(w->{
                    throw new IllegalStateException("이미 존재하는 단어(별칭)입니다.");
                });
    }


    /**
     * Update
     * <p> Word 엔티티를 매개변수로 받아, weight 더티 체킹으로 수정
     * @param word Word 엔티티
     * @return 업데이트 한 Word 객체의 id
     */
    @Transactional(readOnly = false)
    public Long increaseWeight(Word word){

        Word existingWord = wordRepository.findByAliasAndName(word.getAlias(), word.getName())
                .orElseThrow(NullPointerException::new);
        // Domain #set 이용 변경사항 수정
        // weight, updatedAt만 수정 가능
        // weight +1 증가
        existingWord.increaseWeight();
        existingWord.getDbDate().setUpdatedAt(word.getDbDate().getUpdatedAt());

        // update한 객체 id 반환
        return existingWord.getId();
    }
}
