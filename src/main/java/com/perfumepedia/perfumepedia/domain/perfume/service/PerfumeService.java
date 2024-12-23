package com.perfumepedia.perfumepedia.domain.perfume.service;

import com.perfumepedia.perfumepedia.domain.brand.entity.Brand;
import com.perfumepedia.perfumepedia.domain.brand.repository.BrandRepository;
import com.perfumepedia.perfumepedia.domain.note.entity.Note;
import com.perfumepedia.perfumepedia.domain.note.repository.NoteRepository;
import com.perfumepedia.perfumepedia.domain.perfume.dto.PerfumeUpdateReq;
import com.perfumepedia.perfumepedia.domain.perfume.entity.Perfume;
import com.perfumepedia.perfumepedia.domain.perfume.entity.RequestPerfume;
import com.perfumepedia.perfumepedia.domain.perfume.queryDSLRepository.PerfumeRepositoryCustom;
import com.perfumepedia.perfumepedia.domain.perfume.repository.PerfumeRepository;
import com.perfumepedia.perfumepedia.domain.perfume.repository.RequestPerfumeRepository;
import com.perfumepedia.perfumepedia.domain.perfumeNote.dto.PerfumeDetailResponse;
import com.perfumepedia.perfumepedia.domain.perfumeNote.dto.PerfumeSearchDetail;
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
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static com.perfumepedia.perfumepedia.global.enums.ErrorCode.*;
import static com.perfumepedia.perfumepedia.global.enums.SuccessCode.*;

@Service
public class PerfumeService {

    private PerfumeRepository perfumeRepository;
    private PerfumeNoteRepository perfumeNoteRepository;
    private NoteRepository noteRepository;
    private BrandRepository brandRepository;
    private RequestPerfumeNoteRepository requestPerfumeNoteRepository;
    private RequestRepository requestRepository;

    @Autowired
    public PerfumeService(PerfumeRepository perfumeRepository, PerfumeNoteRepository perfumeNoteRepository,
                          NoteRepository noteRepository, BrandRepository brandRepository,
                          RequestPerfumeNoteRepository requestPerfumeNoteRepository,
                          RequestRepository requestRepository) {
        this.perfumeRepository = perfumeRepository;
        this.perfumeNoteRepository = perfumeNoteRepository;
        this.noteRepository = noteRepository;
        this.brandRepository = brandRepository;
        this.requestPerfumeNoteRepository = requestPerfumeNoteRepository;
        this.requestRepository = requestRepository;

    }

    /**
     * 키워드별 향수 검색
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

//
//    /**
//     * 키워드별 향수 검색 (QueryDSL 사용)
//     */
//    public SuccessResponse<List<PerfumeUpdateReq>> searchPerfumes(String keyword) {
//        // 키워드가 null이거나 빈 문자열인 경우 예외 처리
//        if (keyword == null || keyword.trim().isEmpty()) {
//            throw new AppException(INVALID_SEARCH_KEYWORD);
//        }
//
//        // QueryDSL로 향수 검색
//        List<Perfume> perfumes = perfumeRepository.searchPerfumesByKeyword(keyword.trim());
//
//        // 검색 결과가 없는 경우 예외 처리
//        if (perfumes.isEmpty()) {
//            throw new AppException(PERFUME_NOT_FOUND);
//        }
//
//        // Perfume -> DTO 변환
//        List<PerfumeUpdateReq> searchResult = perfumes.stream()
//                .map(PerfumeUpdateReq::toDto)
//                .collect(Collectors.toList());
//
//        return new SuccessResponse<>(SEARCH_COMPLETED, searchResult);
//    }


    /**
     * 향수 세부 정보 조회
     *
     * @param perfumeId 향수 아이디
     * @return 검색된 향수 세부정보
     */
    public SuccessResponse<PerfumeSearchDetail> getPerfumeDetail(Long perfumeId) {

        // 해당하는 향수가 존재하는지 확인
        Perfume perfume = perfumeRepository.findById(perfumeId)
                .orElseThrow(() -> new AppException(PERFUME_NOT_FOUND));

        // 해당하는 향수의 아이디를 가진 노트들을 조회
        List<PerfumeNote> perfumeNotes = perfumeNoteRepository.findByPerfumeId(perfumeId);
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
        PerfumeSearchDetail detailPerfume = PerfumeSearchDetail.toDto(perfume, notes);


        return new SuccessResponse<>(SEARCH_COMPLETED, detailPerfume);
    }

    /**
     * 요청 타입별 요청 수락 -> 유저 아이디 포함 role 이 관리자인지 확인한는 로직
     */
    @Transactional
//    public SuccessResponse<NoneResponse> acceptPerfumeRequest(Long requestId, String role) {
    public SuccessResponse<NoneResponse> acceptPerfumeRequest(Long requestId) {

//        if (!"ADMIN".equals(role)) {
//            throw new AppException(ACCESS_DENIED);
//        }
        // 요청 정보 조회
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new AppException(REQUEST_NOT_FOUND));

        // 요청 타입에 따라 처리
        switch (request.getRequestType()) {
            case CREATE:
                return acceptRegisterRequest(request); // 등록 요청 처리
            case UPDATE:
                return acceptUpdateRequest(request);   // 수정 요청 처리
            case DELETE:
                return acceptDeleteRequest(request);   // 삭제 요청 처리
            default:
                throw new AppException(INVALID_REQUEST_TYPE);
        }
    }

    /**
     * 등록 요청 처리 batch Note, PerfumeNote 리스트값 저장할때 쓰기
     */
    public SuccessResponse<NoneResponse> acceptRegisterRequest(Request request) {
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
     * 수락 요청 처리
     */
    public SuccessResponse<NoneResponse> acceptUpdateRequest(Request request) {
        // 기존 향수 정보 조회
        Perfume perfume = request.getPerfume(); // 기존 향수 정보

        // 요청된 향수 정보 조회
        RequestPerfume reqPerfume = request.getRequestPerfume(); // 요청된 향수 정보

        // 기존 브랜드가 있는지 확인 없으면 새로 생성
        Brand brand = brandRepository.findByName(reqPerfume.getRequestBrand().getName())
                .orElseGet(() -> brandRepository.save(
                        Brand.builder()
                                .name(reqPerfume.getRequestBrand().getName())
                                .url(reqPerfume.getRequestBrand().getUrl())
                                .build()
                ));

        // 기존 향수의 정보를 요청된 정보로 업데이트 - 영속성 문제?
//        perfume = Perfume.builder()
//                .id(perfume.getId())
//                .name(reqPerfume.getName())
//                .price(reqPerfume.getPrice())
//                .brand(brand)
//                .build();

        perfume.update(reqPerfume.getName(), reqPerfume.getPrice(), brand);


        perfumeRepository.save(perfume);

        // 기존 향수와 연결된 모든 PerfumeNote 삭제
        perfumeNoteRepository.deleteByPerfume(perfume);

        // 요청된 노트 정보 처리
        List<RequestPerfumeNote> reqPerfumeNotes = requestPerfumeNoteRepository.findByRequestPerfume(reqPerfume);
        for (RequestPerfumeNote reqPerfumeNote : reqPerfumeNotes) {
            // 기존 노트가 있으면 사용, 없으면 새로 생성
            Note note = noteRepository.findByName(reqPerfumeNote.getRequestNote().getName())
                    .orElseGet(() -> noteRepository.save(
                            Note.builder()
                                    .name(reqPerfumeNote.getRequestNote().getName())
                                    .build()
                    ));

            // PerfumeNote 테이블에 새로운 노트 추가
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

        return new SuccessResponse<>(UPDATE_COMPLETED, NoneResponse.NONE);
    }


    /**
     * 삭제 요청 처리
     */
    public SuccessResponse<NoneResponse> acceptDeleteRequest(Request request) {
        // 삭제할 향수 정보 조회
        Perfume perfume = request.getPerfume();

        // 향수-노트 관계 삭제
        List<PerfumeNote> perfumeNotes = perfumeNoteRepository.findByPerfume(perfume);

//        //  새로운 Request 객체 생성 Perfume, requestPerfume을 null로 설정
//        Request updatedRequest = new Request(request.getId(), request.getRequestType(), RequestStatus.APPROVED,
//                request.getUserId(), null, null);

        request.updateRequestStatus(RequestStatus.APPROVED);
        request.clearPerfumeAndRequestPerfume();

        // 향수 삭제
        requestRepository.save(request);
        perfumeNoteRepository.deleteAll(perfumeNotes);
        perfumeRepository.delete(perfume);


        return new SuccessResponse<>(DELETE_COMPLETED, NoneResponse.NONE);
    }


    /**
     * 요청 거절
     */
    public SuccessResponse<NoneResponse> rejectPerfumeRequest(Long requestId) {
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new AppException(REQUEST_NOT_FOUND));

        // 요청 상태를 거절로 변경 후 저장
        request.updateRequestStatus(RequestStatus.REJECTED);
        requestRepository.save(request);

        return new SuccessResponse<>(REJECTED_COMPLETED, NoneResponse.NONE);
    }
}
