package com.perfumepedia.perfumepedia.domain.perfume.service;

import com.perfumepedia.perfumepedia.domain.note.repository.NoteRepository;
import com.perfumepedia.perfumepedia.domain.perfume.dto.PerfumeUpdateReq;
import com.perfumepedia.perfumepedia.domain.perfume.entity.Perfume;
import com.perfumepedia.perfumepedia.domain.perfume.repository.PerfumeRepository;
import com.perfumepedia.perfumepedia.domain.perfumeNote.dto.PerfumeDetailResponse;
import com.perfumepedia.perfumepedia.domain.perfumeNote.entity.PerfumeNote;
import com.perfumepedia.perfumepedia.domain.perfumeNote.repository.PerfumeNoteRepository;
import com.perfumepedia.perfumepedia.global.enums.ErrorCode;
import com.perfumepedia.perfumepedia.global.handler.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PerfumeService {

    private PerfumeRepository perfumeRepository;
    private PerfumeNoteRepository perfumeNoteRepository;
    private NoteRepository noteRepository;

    @Autowired
    public PerfumeService(PerfumeRepository perfumeRepository, PerfumeNoteRepository perfumeNoteRepository, NoteRepository noteRepository) {
        this.perfumeRepository = perfumeRepository;
        this.perfumeNoteRepository = perfumeNoteRepository;
        this.noteRepository = noteRepository;
    }

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


    /**
     *  향수 세부 정보 조회
     * @param perfumaId 향수 아이디
     * @return 검색된 향수 세부정보
     */
    public PerfumeDetailResponse getPerfumeDetail(Long perfumaId) {

        // 해당하는 향수가 존재하는지 확인
        Perfume perfume = perfumeRepository.findById(perfumaId)
                .orElseThrow(() -> new AppException(ErrorCode.PERFUME_NOT_FOUND));

        // 해당하는 향수의 아이디를 가진 노트들을 조회
        List<PerfumeNote> perfumeNotes = perfumeNoteRepository.findByPerfumeId(perfumaId);
        if (perfumeNotes.isEmpty()) {
            throw new AppException((ErrorCode.NOTE_NOT_FOUND));
        }

        Map<String, List<String>> notes = new HashMap<>();

        for (PerfumeNote perfumeNote : perfumeNotes) {
            String noteType = perfumeNote.getNoteType().name();

            if (!notes.containsKey(noteType)) {
                notes.put(noteType, new ArrayList<>());
            }
            notes.get(noteType).add(perfumeNote.getNote().getName());
        }

        return PerfumeDetailResponse.toDto(perfume, notes);
    }




}
