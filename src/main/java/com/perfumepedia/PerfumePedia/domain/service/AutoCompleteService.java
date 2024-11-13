//package com.perfumepedia.PerfumePedia.domain.service;
//
//import com.perfumepedia.PerfumePedia.domain.repository.WordRepository;
//import com.perfumepedia.PerfumePedia.domain.entity.Word;
//import com.perfumepedia.PerfumePedia.domain.dto.AutoCompleteWordDto;
//import com.perfumepedia.PerfumePedia.domain.dto.ResponseData;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Service
//@Transactional(readOnly = true)
//@RequiredArgsConstructor
//public class AutoCompleteService {
//
//    private final WordRepository wordRepository;
//
//    /**
//     * 사용자가 입력한 keyword에 해당하는 검색어목록을 반환하는 함수입니다.
//     * <p> word 엔티티로부터 weight 기준 내림차순으로 반환합니다.
//     * @param keyword 사용자가 입력한 keyword입니다. 예) 조말론
//     * @return AutoCompleteWordDto 객체를 반환합니다.
//     */
//    public ResponseData findKeywords(String keyword){
//        AutoCompleteWordDto autoCompleteWordDto = new AutoCompleteWordDto();
//
//        // WordRepository #findByAlias 사용
//        // #findByAlias에 의해 Word 객체 List 반횐됨
//        // 자동으로 5개 이하의 결과가 반환되며, desc sort 돼 있음
//        List<Word> words = wordRepository.findByAlias(keyword);
//
//        // AutoCompleteWordDto #set 메소드들을 활용해 초기화 진행
//
//
//        // ResponseData 객체화
//        ResponseData data = new ResponseData(autoCompleteWordDto);
//
//        // 객체화한 ResponseData 반환
//        return data;
//    }
//}
