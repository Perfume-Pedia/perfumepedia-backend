package com.perfumepedia.PerfumePedia.service;

import com.perfumepedia.PerfumePedia.dto.PerfumeDetailDto;
import com.perfumepedia.PerfumePedia.dto.ResponseData;
import com.perfumepedia.PerfumePedia.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        //
        PerfumeDetailDto perfumeDetailDto = new PerfumeDetailDto();

        //
        ResponseData data = new ResponseData(perfumeDetailDto);

        //
        return data;
    }
}
