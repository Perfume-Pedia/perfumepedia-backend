package com.perfumepedia.perfumepedia.domain.perfumeNote.service;

import com.perfumepedia.perfumepedia.domain.perfume.entity.Perfume;
import com.perfumepedia.perfumepedia.domain.perfume.entity.RequestPerfume;
import com.perfumepedia.perfumepedia.domain.perfumeNote.dto.PerfumeDetailResponse;
import com.perfumepedia.perfumepedia.domain.perfumeNote.entity.PerfumeNote;
import com.perfumepedia.perfumepedia.domain.perfumeNote.entity.RequestPerfumeNote;
import com.perfumepedia.perfumepedia.domain.perfumeNote.repository.PerfumeNoteRepository;
import com.perfumepedia.perfumepedia.domain.perfumeNote.repository.RequestPerfumeNoteRepository;
import com.perfumepedia.perfumepedia.domain.request.entity.Request;
import com.perfumepedia.perfumepedia.domain.request.entity.RequestType;
import com.perfumepedia.perfumepedia.domain.request.repository.RequestRepository;
import com.perfumepedia.perfumepedia.global.handler.AppException;
import com.perfumepedia.perfumepedia.global.response.SuccessResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.perfumepedia.perfumepedia.global.enums.ErrorCode.*;
import static com.perfumepedia.perfumepedia.global.enums.SuccessCode.SEARCH_COMPLETED;

@Service
public class RequestPerfumeNoteService {

    private final RequestRepository requestRepository;
    private final RequestPerfumeNoteRepository requestPerfumeNoteRepository;
    private final PerfumeNoteRepository PerfumeNoteRepository;


    public RequestPerfumeNoteService(RequestRepository requestRepository, RequestPerfumeNoteRepository requestPerfumeNoteRepository, PerfumeNoteRepository PerfumeNoteRepository) {
        this.requestRepository = requestRepository;
        this.requestPerfumeNoteRepository = requestPerfumeNoteRepository;
        this.PerfumeNoteRepository = PerfumeNoteRepository;
    }

    /**
     * 등록 요청 / 수정 요청 상세 조회
     */
    public SuccessResponse<PerfumeDetailResponse> getRegisterRequestDetail(Long requestId, String requestType) {
        RequestType type = RequestType.valueOf(requestType.toUpperCase());

        // 요청 조회
        Request registerRequest = requestRepository.findById(requestId)
                .orElseThrow(() -> new AppException(REQUEST_NOT_FOUND));

        if (registerRequest.getRequestType() != type) {
            throw new AppException(REQUEST_TYPE_MISMATCH);
        }

        // 요청한 향수 정보 (RequestPerfume에서 id 받아옴)
        RequestPerfume requestPerfume = registerRequest.getRequestPerfume();

        // 요청한 향수 노트 정보
        List<RequestPerfumeNote> requestPerfumeNotes = requestPerfumeNoteRepository.findByRequestPerfumeId(requestPerfume.getId());

        if (requestPerfumeNotes.isEmpty()) {
            throw new AppException(NOTE_NOT_FOUND);
        }

        Map<String, List<String>> notes = new HashMap<>();
        for (RequestPerfumeNote requestPerfumeNote : requestPerfumeNotes) {
            String noteType = requestPerfumeNote.getNoteType().name();
            if (!notes.containsKey(noteType)) {
                notes.put(noteType, new ArrayList<>());
            }
            notes.get(noteType).add(requestPerfumeNote.getRequestNote().getName());
        }

        // entity -> dto
        PerfumeDetailResponse detailPerfume = PerfumeDetailResponse.fromEntity(requestPerfume, notes, requestId);
        return new SuccessResponse<>(SEARCH_COMPLETED, detailPerfume);
    }

    /**
     * 삭제 요청 / 수정 요청 상세 조회
     */
    public SuccessResponse<PerfumeDetailResponse> getDeleteRequestDetail(Long requestId, String requestType) {

        RequestType type = RequestType.valueOf(requestType.toUpperCase());

        // 요청 조회
        Request deleteRequest = requestRepository.findById(requestId)
                .orElseThrow(() -> new AppException(REQUEST_NOT_FOUND));

        // 요청 타입 검증
        if (deleteRequest.getRequestType() != type) {
            throw new AppException(REQUEST_TYPE_MISMATCH);
        }

        // 요청한 향수 정보 (Perfume에서 id 받아옴)
        Perfume perfume = deleteRequest.getPerfume();

        // 요청한 향수 노트 정보
        List<PerfumeNote> perfumeNotes = PerfumeNoteRepository.findByPerfumeId(perfume.getId());
        if (perfumeNotes.isEmpty()) {
            throw new AppException(NOTE_NOT_FOUND);
        }

        Map<String, List<String>> notes = new HashMap<>();
        for (PerfumeNote perfumeNote : perfumeNotes) {
            String noteType = perfumeNote.getNoteType().name();

            if (!notes.containsKey(noteType)) {
                notes.put(noteType, new ArrayList<>());
            }
            notes.get(noteType).add(perfumeNote.getNote().getName());
        }
        // entity -> dto
        PerfumeDetailResponse detailPerfume = PerfumeDetailResponse.toDto(perfume, notes, requestId);

        return new SuccessResponse<>(SEARCH_COMPLETED, detailPerfume);
    }
}
