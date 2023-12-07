package com.perfumepedia.PerfumePedia.service;

import com.perfumepedia.PerfumePedia.domain.Word;
import com.perfumepedia.PerfumePedia.dto.AutoCompleteWord;
import com.perfumepedia.PerfumePedia.dto.AutoCompleteWordDto;
import com.perfumepedia.PerfumePedia.dto.ResponseData;
import com.perfumepedia.PerfumePedia.repository.WordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.perfumepedia.PerfumePedia.dto.AutoCompleteWordDto;


import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AutoCompleteService {

    private final WordRepository wordRepository;

    /**
     * 사용자가 입력한 keyword에 해당하는 검색어목록을 반환하는 함수입니다.
     * <p> word 엔티티로부터 weight 기준 내림차순으로 반환합니다.
     * @param keyword 사용자가 입력한 keyword입니다. 예) 조말론
     * @return AutoCompleteWordDto 객체를 반환합니다.
     */
    public ResponseData findKeywords(String keyword){
        // keyword를 기반으로 레포지토리에서 단어들을 가져옴 (5개)
        List<Word> words = wordRepository.findByAlias(keyword);

        // AutoCompleteWordDto 객체 생성
        AutoCompleteWordDto autoCompleteWordDto = new AutoCompleteWordDto();

        // AutoCompleteWords 생성
        List<AutoCompleteWord> autoCompleteWords = new ArrayList<>();

        // AutoCompleteWordDto #set 메소드들을 활용해 초기화 진행
        for(Word word : words) {
            AutoCompleteWord autoCompleteWord = new AutoCompleteWord();
            autoCompleteWord.setKeyword(word.getName());

            autoCompleteWords.add(autoCompleteWord);
        }

        // AutoCompleteWordDto #setItems 이용
        autoCompleteWordDto.setItems(autoCompleteWords);
        autoCompleteWordDto.setItem_count(words.size());

        // ResponseData 객체화
        ResponseData data = new ResponseData(autoCompleteWordDto);

        // 객체화한 ResponseData 반환
        return data;
    }


}
