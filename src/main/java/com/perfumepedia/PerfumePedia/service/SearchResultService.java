package com.perfumepedia.PerfumePedia.service;

import com.perfumepedia.PerfumePedia.domain.Perfume;
import com.perfumepedia.PerfumePedia.domain.PerfumeNote;
import com.perfumepedia.PerfumePedia.domain.Word;
import com.perfumepedia.PerfumePedia.dto.PerfumeResult;
import com.perfumepedia.PerfumePedia.dto.ResponseData;
import com.perfumepedia.PerfumePedia.dto.SearchResultDto;
import com.perfumepedia.PerfumePedia.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SearchResultService {

    private final WordRepository wordRepository;
    private final PerfumeRepository perfumeRepository;
    private final PerfumeNoteRepository perfumeNoteRepository;
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
        List<Word> words = wordRepository.findByAlias(keyword);

        // 향수 목록을 위한 리스트 생성
        List<PerfumeResult> perfumeResults = new ArrayList<>();

        // words 목록을 이용해 각 별칭에 맞는 향수 목록 저장
        for(Word word: words){
            // word와 연관된 word 객체들의 가중치 증가 -> lastId가 0인 경우만(첫 검색시)
            if(lastId!=0)
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
        // size 개의 향수 정보를 담기 위한 sizeOfPerfumes
        List<PerfumeResult> items= new ArrayList<>();
        String last_item_id = "";

        // lastId 값에 따라 인덱싱기 위해 사용44
        boolean isFindUuid = lastId==0? true: false;

        for (PerfumeResult perfumeResult : perfumeResults) {
            if(size == 0)
                break;

            String uuid = perfumeResult.getUuid();
            if(isFindUuid){
                items.add(perfumeResult);
                size--;
                last_item_id = uuid;
                continue;
            }

            // lastId 에 해당하는 uuid 값을 찾은 경우
            if(uuid.equals(lastId.toString())){
                // lastId 를 찾았으므로, 다음 perfumeResult부터 add하기 위해 넘김
                isFindUuid = true;
            }
        }

        searchResultDto.setItems(items);
        searchResultDto.setLast_item_id(last_item_id);

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

        List<Perfume> perfumes = perfumeRepository.findByBrand(word.getBrand());

        // PerfumeResult 객체 생성 및 추가
        for (Perfume perfume : perfumes) {
            PerfumeResult perfumeResult = new PerfumeResult(perfume);
            perfumeResults.add(perfumeResult);
        }
    }

    /**
     * word의 타입이 Perfume인 경우 호출
     * <p> perfumeResults에 perfumeResult 객체들 추가
     * @param word
     * @param perfumeResults
     */
    private void addPerfumeByPerfume(Word word, List<PerfumeResult> perfumeResults) {

        Optional<Perfume> optionalPerfume = perfumeRepository.findById(word.getTypeId());

        // PerfumeResult 객체 생성 및 추가
        optionalPerfume.ifPresent(perfume -> {
            PerfumeResult perfumeResult = new PerfumeResult(perfume);
            perfumeResults.add(perfumeResult);
        });
    }

    /**
     * word의 타입이 Note인 경우 호출
     * <p> perfumeResults에 perfumeResult 객체들 추가
     * @param word
     * @param perfumeResults
     */
    private void addPerfumeByNote(Word word, List<PerfumeResult> perfumeResults) {

        List<PerfumeNote> perfumeNotes = perfumeNoteRepository.findByNote(word.getNote());

        for (PerfumeNote perfumeNote : perfumeNotes) {
            Optional<Perfume> optionalPerfume = perfumeRepository.findById(perfumeNote.getPerfume().getId());

            // PerfumeResult 객체 생성 및 추가
            optionalPerfume.ifPresent(perfume -> {
                PerfumeResult perfumeResult = new PerfumeResult(perfume);
                perfumeResults.add(perfumeResult);
            });
        }
    }


    /**
     * 선호 향수 api에 사용됨
     * <p>향수 id들로 향수 목록을 검색할 때 사용하는 메소드
     * <p> ids의 길이만큼(개수만큼) 반환한다.
     * @param ids id 리스트 -> size가 0이어도 됨
     * @return 향수 목록 반환
     */
    public ResponseData searchByPerfumeId(List<Long> ids){
        // 값을 담을 perfumeResults 리스트 생성
        List<PerfumeResult> perfumeResults = new ArrayList<>();

        for (Long id : ids) {
            Optional<Perfume> optionalPerfume = perfumeRepository.findById(id);

            // PerfumeResult 객체 생성 및 추가
            optionalPerfume.ifPresent(perfume -> {
                PerfumeResult perfumeResult = new PerfumeResult(perfume);
                perfumeResults.add(perfumeResult);
            });
        }

        SearchResultDto searchResultDto = new SearchResultDto();
        searchResultDto.setItems(perfumeResults);
        if(perfumeResults.size()>0)
            searchResultDto.setLast_item_id(perfumeResults.get(perfumeResults.size()-1).getUuid());
        else searchResultDto.setLast_item_id("");


        ResponseData data = new ResponseData(searchResultDto);

        return data;

    }
}
