package com.perfumepedia.PerfumePedia.service;

import com.perfumepedia.PerfumePedia.domain.*;
import com.perfumepedia.PerfumePedia.dto.PerfumeDetailDto;
import com.perfumepedia.PerfumePedia.dto.ResponseData;
import com.perfumepedia.PerfumePedia.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PerfumeDetailService {

    private final WordRepository wordRepository;
    private final PerfumeRepository perfumeRepository;
    private final BrandRepository brandRepository;
    private final PerfumeNoteRepository perfumeNoteRepository;
    private final NoteRepository noteRepository;

    public ResponseData searchPerfumeWithDetail(Long perfumeId){

        // PerfumeDetailDto 객체 생성
        PerfumeDetailDto perfumeDetailDto = new PerfumeDetailDto();

        // perfumeRepository #findById 를 이용하여 perfumeId 에 해당하는 향수를 조회
        Optional<Perfume> perfumeOptional = perfumeRepository.findById(perfumeId);



        // 값이 있을 경우만 객체 사용 가능
        if (perfumeOptional.isPresent()) {
            Perfume perfume = perfumeOptional.get();


            perfumeDetailDto.setUuid(perfume.getId().toString());
            perfumeDetailDto.setPerfume_name(perfume.getName());
            perfumeDetailDto.setBrand_name(perfume.getBrand().getName());
            perfumeDetailDto.setPrice(String.valueOf(perfume.getPrice()));
            perfumeDetailDto.setUrl(perfume.getUrl());
            perfumeDetailDto.setImage_path(perfume.getImage().getPath());
            perfumeDetailDto.setCreated_at(perfume.getDbDate().getCreatedAt());

            // perfumeNoteRepository #findByPerfume를 이용하여 perfumeNotes를 생성
            List<PerfumeNote> perfumeNotes = perfumeNoteRepository.findByPerfume(perfume);

            List<String> topNoteNames = new ArrayList<>();
            List<String> middleNoteNames = new ArrayList<>();
            List<String> baseNoteNames = new ArrayList<>();
            List<String> singleNoteNames = new ArrayList<>();


            for(PerfumeNote perfumeNote : perfumeNotes) {
                String noteName = noteRepository.findById(perfumeNote.getNote().getId()).get().getName();

                if(perfumeNote.getNoteType() == NoteType.TOP) {
                    topNoteNames.add(noteName);
                }
                if(perfumeNote.getNoteType() == NoteType.MIDDLE) {
                    middleNoteNames.add(noteName);
                }
                if(perfumeNote.getNoteType() == NoteType.BASE) {
                    baseNoteNames.add(noteName);
                }
                if(perfumeNote.getNoteType() == NoteType.SINGLE) {
                    singleNoteNames.add(noteName);
                }

            }
            perfumeDetailDto.setTop_note_names(topNoteNames);
            perfumeDetailDto.setTop_note_names(middleNoteNames);
            perfumeDetailDto.setTop_note_names(baseNoteNames);
            perfumeDetailDto.setTop_note_names(singleNoteNames);

        }

        ResponseData data = new ResponseData(perfumeDetailDto);

        return data;
    }
}
