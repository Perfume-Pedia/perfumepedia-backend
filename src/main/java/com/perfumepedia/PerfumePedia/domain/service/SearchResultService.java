package com.perfumepedia.PerfumePedia.domain.service;

import com.perfumepedia.PerfumePedia.domain.dto.ResponseData;
import com.perfumepedia.PerfumePedia.domain.dto.SearchResultDto;
import com.perfumepedia.PerfumePedia.domain.entity.Word;
import com.perfumepedia.PerfumePedia.domain.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SearchResultService {

    private final WordRepository wordRepository;
    private final PerfumeRepository perfumeRepository;
    private final BrandRepository brandRepository;
    private final PerfumeNoteRepository perfumeNoteRepository;
    private final NoteRepository noteRepository;

    public ResponseData searchByKeyword(String keyword){
        // WordRepository #findByAlias 이용
        List<Word> words =  wordRepository.findByAlias(keyword);


        List<SearchResultDto.PerfumeResult> perfumeResultList = new ArrayList<>();

        //
        for(Word word: words){
            SearchResultDto.PerfumeResult perfumeResult = new SearchResultDto.PerfumeResult();
        }

        //
        SearchResultDto searchResultDto = new SearchResultDto();

        //
        ResponseData data = new ResponseData(searchResultDto);

        //
        return data;
    }

    public ResponseData searchByPerfumeId(Long id){
        //


        //
        SearchResultDto searchResultDto = new SearchResultDto();

        //
        ResponseData data = new ResponseData(searchResultDto);

        //
        return data;
    }
}
