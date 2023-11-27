package com.perfumepedia.PerfumePedia.service;

import com.perfumepedia.PerfumePedia.domain.Word;
import com.perfumepedia.PerfumePedia.dto.PerfumeResult;
import com.perfumepedia.PerfumePedia.dto.ResponseData;
import com.perfumepedia.PerfumePedia.dto.SearchResultDto;
import com.perfumepedia.PerfumePedia.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SearchResultService {

    private final WordRepository wordRepository;
    private final PerfumeRepository perfumeRepository;
    private final BrandRepository brandRepository;
    private final WordService wordService;

    /**
     * 향수 검색 api에 사용됨
     * <p>키워드로 향수 목록을 검색할 때 사용하는 메소드
     * <p> 향수 목록 중 lastId 다음 번호부터 size 만큼 반환한다.
     * @param lastId 마지막으로 검색된 향수의 id
     * @param size 반환할 향수의 개수
     * @param keyword 사용자가 입력한 검색어
     * @return 향수 목록
     */
    public ResponseData searchByKeyword(Long lastId, int size, String keyword){
        // WordRepository #findByAlias 이용(최대 크기5의 리스트)
        List<Word> words =  wordRepository.findByAlias(keyword);

        // 향수 목록을 위한 리스트 생성
        List<PerfumeResult> perfumeResults = new ArrayList<>();

        // words 목록을 이용해 각 별칭에 맞는 향수 목록 저장
        for(Word word: words){
            // word와 연관된 word 객체들의 가중치 증가
            increaseWeight(word);
            
            // word의 wordType에 따라 처리
            switch (word.getWordType()) {
                case BRAND -> addPerfumeByBrand(word, perfumeResults);
                case PERFUME -> addPerfumeByPerfume(word, perfumeResults);
                case NOTE -> addPerfumeByNote(word, perfumeResults);
                default -> throw new IllegalArgumentException("Unsupported wordType: " + word.getWordType());
            }
        }

        // SearchResultDto객체 생성 및 초기화
        SearchResultDto searchResultDto = new SearchResultDto();

        // #setsetItems: lastId와 size에 맞게 리스트 조작 후 set
        // 여기서 perfumeResults 조작
        searchResultDto.setItems(perfumeResults);

        // #setLast_item_id: items 마지막 객체의 id값으로 초기화
        String LastItemId = ""; // 여기서 값 초기화
        searchResultDto.setLast_item_id(LastItemId);

        // ResponseData 객체화
        ResponseData data = new ResponseData(searchResultDto);

        // ResponseData 반환
        return data;
    }

    private void increaseWeight(Word baseWord) {
        // WordRepository #findByTypeAndTypeId 이용
        List<Word> words = wordRepository.findByTypeAndTypeId(baseWord.getTypeId(), baseWord.getWordType());

        // word들의 가중치 1씩 증가
        for(Word word: words){
            wordService.increaseWeight(word);
        }
    }

    /**
     * word의 타입이 Brand 경우 호출
     * <p> perfumeResults에 perfumeResult 객체들 추가
     * @param word
     * @param perfumeResults
     */
    private void addPerfumeByBrand(Word word, List<PerfumeResult> perfumeResults) {

    }

    /**
     * word의 타입이 Perfume인 경우 호출
     * <p> perfumeResults에 perfumeResult 객체들 추가
     * @param word
     * @param perfumeResults
     */
    private void addPerfumeByPerfume(Word word, List<PerfumeResult> perfumeResults) {
    }

    /**
     * word의 타입이 Note인 경우 호출
     * <p> perfumeResults에 perfumeResult 객체들 추가
     * @param word
     * @param perfumeResults
     */
    private void addPerfumeByNote(Word word, List<PerfumeResult> perfumeResults) {

    }


    /**
     * 선호 향수 api에 사용됨
     * <p>향수 id들로 향수 목록을 검색할 때 사용하는 메소드
     * <p> ids의 길이만큼(개수만큼) 반환한다.
     * @param ids id 배열 -> size가 0이어도 됨
     * @return 향수 목록 반환
     */
    public ResponseData searchByPerfumeId(Long ...ids){
        //


        //
        SearchResultDto searchResultDto = new SearchResultDto();

        //
        ResponseData data = new ResponseData(searchResultDto);

        //
        return data;
    }
}
