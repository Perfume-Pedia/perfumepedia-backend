package com.perfumepedia.perfumepedia.domain.perfume.service;

import com.perfumepedia.perfumepedia.domain.brand.entity.Brand;
import com.perfumepedia.perfumepedia.domain.brand.repository.BrandRepository;
import com.perfumepedia.perfumepedia.domain.note.entity.Note;
import com.perfumepedia.perfumepedia.domain.note.repository.NoteRepository;
import com.perfumepedia.perfumepedia.domain.perfume.dto.PerfumeUpdateReq;
import com.perfumepedia.perfumepedia.domain.perfume.entity.Perfume;
import com.perfumepedia.perfumepedia.domain.perfume.entity.RequestPerfume;
import com.perfumepedia.perfumepedia.domain.perfume.repository.PerfumeRepository;
import com.perfumepedia.perfumepedia.domain.perfume.repository.RequestPerfumeRepository;
import com.perfumepedia.perfumepedia.domain.perfumeNote.dto.PerfumeDetailResponse;
import com.perfumepedia.perfumepedia.domain.perfumeNote.entity.PerfumeNote;
import com.perfumepedia.perfumepedia.domain.perfumeNote.entity.RequestPerfumeNote;
import com.perfumepedia.perfumepedia.domain.perfumeNote.repository.PerfumeNoteRepository;
import com.perfumepedia.perfumepedia.domain.perfumeNote.repository.RequestPerfumeNoteRepository;
import com.perfumepedia.perfumepedia.domain.request.entity.Request;
import com.perfumepedia.perfumepedia.domain.request.entity.RequestStatus;
import com.perfumepedia.perfumepedia.domain.request.repository.RequestRepository;
import com.perfumepedia.perfumepedia.global.enums.NoneResponse;
import com.perfumepedia.perfumepedia.global.handler.AppException;
import com.perfumepedia.perfumepedia.global.response.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.perfumepedia.perfumepedia.global.enums.ErrorCode.*;
import static com.perfumepedia.perfumepedia.global.enums.SuccessCode.*;

@Service
public class PerfumeService {

    private PerfumeRepository perfumeRepository;
    private PerfumeNoteRepository perfumeNoteRepository;
    private NoteRepository noteRepository;
    private BrandRepository brandRepository;
    private RequestPerfumeNoteRepository requestPerfumeNoteRepository;
    private RequestPerfumeRepository requestPerfumeRepository;
    private RequestRepository requestRepository;



    @Autowired
    public PerfumeService(PerfumeRepository perfumeRepository, PerfumeNoteRepository perfumeNoteRepository,
                          NoteRepository noteRepository, BrandRepository brandRepository, RequestPerfumeNoteRepository requestPerfumeNoteRepository,
                          RequestPerfumeRepository requestPerfumeRepository, RequestRepository requestRepository) {
        this.perfumeNoteRepository = perfumeNoteRepository;
        this.perfumeRepository = perfumeRepository;
        this.noteRepository = noteRepository;
        this.brandRepository = brandRepository;
        this.requestPerfumeNoteRepository = requestPerfumeNoteRepository;
        this.requestPerfumeRepository = requestPerfumeRepository;
        this.requestRepository = requestRepository;
    }

    /**
     * 키워드별 향수 검색
     *
     * @param keyword 검색어
     * @return 검색된 향수 리스트
     */
    public SuccessResponse<List<PerfumeUpdateReq>> searchPerfumes(String keyword) {
        // 브렌드 이름으로 향수 검색
        List<Perfume> perfumesByBrand = perfumeRepository.findByBrand_NameContaining(keyword);
        // 향수 이름으로 향수 검색
        List<Perfume> perfumesByName = perfumeRepository.findByNameContaining(keyword);
        // 노트 이름으로 향수 검색
        List<PerfumeNote> perfumeNotes = perfumeNoteRepository.findByNote_NameContaining(keyword);

        // 중복 제거를 위함
        Set<Perfume> resultPerfume = new HashSet<>();

        resultPerfume.addAll(perfumesByBrand);
        resultPerfume.addAll(perfumesByName);

        for (PerfumeNote perfumeNote : perfumeNotes) {
            resultPerfume.add(perfumeNote.getPerfume());
        }
        // 검색 결과가 없을 경우 예외 처리
        if (resultPerfume.isEmpty()) {
            throw new AppException(PERFUME_NOT_FOUND);
        }

        // Perfume -> DTO
        List<PerfumeUpdateReq> searchResult = new ArrayList<>();

        for (Perfume perfume : resultPerfume) {
            searchResult.add(PerfumeUpdateReq.toDto(perfume));
        }

        return new SuccessResponse<>(SEARCH_COMPLETED, searchResult);
    }


    /**
     * 향수 세부 정보 조회
     *
     * @param perfumaId 향수 아이디
     * @return 검색된 향수 세부정보
     */
    public SuccessResponse<PerfumeDetailResponse> getPerfumeDetail(Long perfumaId) {

        // 해당하는 향수가 존재하는지 확인
        Perfume perfume = perfumeRepository.findById(perfumaId)
                .orElseThrow(() -> new AppException(PERFUME_NOT_FOUND));

        // 해당하는 향수의 아이디를 가진 노트들을 조회
        List<PerfumeNote> perfumeNotes = perfumeNoteRepository.findByPerfumeId(perfumaId);
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
        PerfumeDetailResponse detailPerfume = PerfumeDetailResponse.toDto(perfume, notes);


        return new SuccessResponse<>(SEARCH_COMPLETED, detailPerfume);
    }

    /**
     * 요청 수락
     */
    @Transactional
    public SuccessResponse<NoneResponse> registerPerfumeRequest(Long requestId) {
        // 요청 정보 조회
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new AppException(REQUEST_NOT_FOUND));

        // 요청한 향수 정보 조회(등록 -> RequestPerfume에서 조회)
        RequestPerfume reqPerfume = request.getRequestPerfume();

        // 요청한 향수 노트 정보 조회
        List<RequestPerfumeNote> reqPerfumeNotes = requestPerfumeNoteRepository.findByRequestPerfume(reqPerfume);

        // 요청한 브랜드 정보 조회 없다면 기존 브랜드 매핑
        Brand brand = brandRepository.findByName(reqPerfume.getRequestBrand().getName())
                .orElseGet(() -> brandRepository.save(
                        Brand.builder()
                                .name(reqPerfume.getRequestBrand().getName())
                                .url(reqPerfume.getRequestBrand().getUrl())
                                .build()
                ));

        // 요청한 향수 정보 저장 또는 기존 향수 매핑
        Perfume perfume = perfumeRepository.findByName(reqPerfume.getName())
                .orElseGet(() -> perfumeRepository.save(
                        Perfume.builder()
                                .name(reqPerfume.getName())
                                .price(reqPerfume.getPrice())
                                .brand(brand)
                                .build()
                ));

        // 요청한 노트 정보 저장 또는 기존 노트 매핑 후 PerfumeNote 저장
        for (RequestPerfumeNote reqPerfumeNote : reqPerfumeNotes) {
            Note note = noteRepository.findByName(reqPerfumeNote.getRequestNote().getName())
                    .orElseGet(() -> noteRepository.save(
                            Note.builder()
                                    .name(reqPerfumeNote.getRequestNote().getName())
                                    .build()
                    ));

            // PerfumeNote 저장
            perfumeNoteRepository.save(
                    PerfumeNote.builder()
                            .perfume(perfume)
                            .note(note)
                            .noteType(reqPerfumeNote.getNoteType())
                            .build()
            );
        }

        // 요청 상태를 승인으로 변경
        request.updateRequestStatus(RequestStatus.APPROVED);
        requestRepository.save(request);

        return new SuccessResponse<>(REGISTER_COMPLETED, NoneResponse.NONE);
    }


    /**
     * 요청 거절
     * */
    @Transactional
    public SuccessResponse<NoneResponse> rejectPerfumeRequest(Long requestId) {
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new AppException(REQUEST_NOT_FOUND));

        // 요청 상태를 거절로 변경 후 저장
        request.updateRequestStatus(RequestStatus.REJECTED);
        requestRepository.save(request);

        return new SuccessResponse<>(REJECTED_COMPLETED, NoneResponse.NONE);
    }







}
