package com.perfumepedia.perfumepedia.domain.perfume.service;

import com.perfumepedia.perfumepedia.domain.perfume.dto.PerfumeUpdateReq;
import com.perfumepedia.perfumepedia.domain.perfume.entity.Perfume;
import com.perfumepedia.perfumepedia.domain.perfume.repository.PerfumeRepository;
import com.perfumepedia.perfumepedia.domain.perfumeNote.entity.PerfumeNote;
import com.perfumepedia.perfumepedia.domain.perfumeNote.repository.PerfumeNoteRepository;
import com.perfumepedia.perfumepedia.global.enums.ErrorCode;
import com.perfumepedia.perfumepedia.global.handler.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PerfumeService {

    @Autowired
    private PerfumeRepository perfumeRepository;

    @Autowired
    private PerfumeNoteRepository perfumeNoteRepository;

    /**
     * 키워드별 향수 검색
     * @param keyword 검색어
     * @return 검색된 향수 리스트
     */
    public List<PerfumeUpdateReq> searchPerfumes(String keyword) {
        // 브렌드 이름으로 향수 검색
        List<Perfume> perfumesByBrand = perfumeRepository.findByBrandNameContaining(keyword);
        // 향수 이름으로 향수 검색
        List<Perfume> perfumesByName = perfumeRepository.findByNameContaining(keyword);
        // 노트 이름으로 향수 검색
        List<PerfumeNote> perfumeNotes = perfumeNoteRepository.findByNoteContaining(keyword);

        // 중복 제거를 위함
        Set<Perfume> resultPerfume = new HashSet<>();

        resultPerfume.addAll(perfumesByBrand);
        resultPerfume.addAll(perfumesByName);

        for (PerfumeNote perfumeNote : perfumeNotes) {
            resultPerfume.add(perfumeNote.getPerfume());
        }
        // 검색 결과가 없을 경우 예외 처리
        if(resultPerfume.isEmpty()) {
            throw new AppException(ErrorCode.PERFUME_NOT_FOUND);
        }

        // Perfume -> DTO로 변환
        List<PerfumeUpdateReq> searchResult = new ArrayList<>();

        for (Perfume perfume : resultPerfume) {
            searchResult.add(PerfumeUpdateReq.toDto(perfume));
        }

        return searchResult;
    }


}
