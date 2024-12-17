package com.perfumepedia.perfumepedia.domain.perfume.service;

import com.perfumepedia.perfumepedia.domain.brand.entity.Brand;
import com.perfumepedia.perfumepedia.domain.note.entity.Note;

import com.perfumepedia.perfumepedia.domain.brand.entity.RequestBrand;
import com.perfumepedia.perfumepedia.domain.brand.repository.BrandRepository;
import com.perfumepedia.perfumepedia.domain.brand.repository.RequestBrandRepository;
import com.perfumepedia.perfumepedia.domain.note.entity.NoteType;
import com.perfumepedia.perfumepedia.domain.note.entity.RequestNote;
import com.perfumepedia.perfumepedia.domain.note.repository.NoteRepository;
import com.perfumepedia.perfumepedia.domain.note.repository.RequestNoteRepository;
import com.perfumepedia.perfumepedia.domain.perfume.entity.Perfume;
import com.perfumepedia.perfumepedia.domain.perfume.entity.RequestPerfume;
import com.perfumepedia.perfumepedia.domain.perfume.repository.PerfumeRepository;
import com.perfumepedia.perfumepedia.domain.perfume.repository.RequestPerfumeRepository;
import com.perfumepedia.perfumepedia.domain.perfumeNote.dto.RequestPerfumeDetailReq;
import com.perfumepedia.perfumepedia.domain.perfumeNote.entity.PerfumeNote;
import com.perfumepedia.perfumepedia.domain.perfumeNote.entity.RequestPerfumeNote;
import com.perfumepedia.perfumepedia.domain.perfumeNote.repository.PerfumeNoteRepository;
import com.perfumepedia.perfumepedia.domain.perfumeNote.repository.RequestPerfumeNoteRepository;
import com.perfumepedia.perfumepedia.domain.request.entity.Request;
import com.perfumepedia.perfumepedia.domain.request.entity.RequestStatus;
import com.perfumepedia.perfumepedia.domain.request.entity.RequestType;
import com.perfumepedia.perfumepedia.domain.request.repository.RequestRepository;
import com.perfumepedia.perfumepedia.global.enums.NoneResponse;
import com.perfumepedia.perfumepedia.global.handler.AppException;
import com.perfumepedia.perfumepedia.global.response.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

import static com.perfumepedia.perfumepedia.global.enums.ErrorCode.PERFUME_NOT_FOUND;
import static com.perfumepedia.perfumepedia.global.enums.SuccessCode.*;

@Service
public class RequestPerfumeService {

    private final RequestPerfumeRepository requestPerfumeRepository;
    private final RequestBrandRepository requestBrandRepository;
    private final RequestNoteRepository requestNoteRepository;
    private final RequestPerfumeNoteRepository reqPerfumeNoteRepository;
    private final RequestRepository requestRepository;
    private final PerfumeRepository perfumeRepository;
    private final BrandRepository brandRepository;
    private final NoteRepository noteRepository;
    private final PerfumeNoteRepository perfumeNoteRepository;

    @Autowired
    public RequestPerfumeService(RequestPerfumeRepository requestPerfumeRepository, RequestBrandRepository requestBrandRepository,
                                 RequestNoteRepository requestNoteRepository, RequestPerfumeNoteRepository reqPerfumeNoteRepository,
                                 RequestRepository requestRepository, PerfumeRepository perfumeRepository, BrandRepository brandRepository,
                                 NoteRepository noteRepository, PerfumeNoteRepository perfumeNoteRepository) {
        this.requestPerfumeRepository = requestPerfumeRepository;
        this.requestBrandRepository = requestBrandRepository;
        this.requestNoteRepository = requestNoteRepository;
        this.reqPerfumeNoteRepository = reqPerfumeNoteRepository;
        this.requestRepository = requestRepository;
        this.perfumeRepository = perfumeRepository;
        this.brandRepository = brandRepository;
        this.noteRepository = noteRepository;
        this.perfumeNoteRepository = perfumeNoteRepository;
    }


    /**
     * 향수 등록 요청 api
     *
     * @param reqPerfumeDetailReq 향수 등록에 필요한 요청 데이터
     * @param userId              요청한 유저의 ID
     * @return perfumeDetailReq
     */

    public SuccessResponse<NoneResponse> registerPerfumeRequest(RequestPerfumeDetailReq reqPerfumeDetailReq, Long userId) {
        // 요청 브랜드에 저장
        RequestBrand reqBrand = saveReqBrand(reqPerfumeDetailReq.getBrandName());

        // 요청 향수에 저장
        RequestPerfume reqPerfume = saveReqPerfume(reqPerfumeDetailReq.getName(), reqPerfumeDetailReq.getPrice(), reqBrand);

        // 요청 노트 & 요청 퍼퓸 노트 저장
        saveReqNotes(reqPerfumeDetailReq, reqPerfume);

        // 요청 저장
        Request request = Request.builder()
                .requestType(RequestType.CREATE) // 등록 요청
                .requestStatus(RequestStatus.PENDING) // 대기중
                .userId(userId)
                .perfume(null) // 등록 요청 상태인 향수 정보
                .requestPerfume(reqPerfume) // 등록 요청 상태인 향수 정보
                .build();

        requestRepository.save(request);

        // 반환 데이터 없음
        return new SuccessResponse<>(REQUEST_COMPLETED, NoneResponse.NONE);
    }

    /**
     * 향수 수정 요청 api
     *
     * @param reqPerfumeDetailReq 수정할 향수의 정보
     * @param perfume             수정할 향수의 ID
     * @param userId              수정 요청한 유저의 ID
     **/
    public SuccessResponse<NoneResponse> updatePerfumeRequest(RequestPerfumeDetailReq reqPerfumeDetailReq, Long perfume, Long userId) {
        // 기존 향수 조회
        Perfume originPerfume = perfumeRepository.findById(perfume)
                .orElseThrow(() -> new AppException(PERFUME_NOT_FOUND));

        // 요청 브랜드에 저장
        RequestBrand reqBrand = saveReqBrand(reqPerfumeDetailReq.getBrandName());

        // 요청 향수에 저장
        RequestPerfume reqPerfume = saveReqPerfume(reqPerfumeDetailReq.getName(), reqPerfumeDetailReq.getPrice(), reqBrand);

        // 요청 노트 & 요청 퍼퓸 노트 저장
        saveReqNotes(reqPerfumeDetailReq, reqPerfume);


        Request request = Request.builder()
                .requestType(RequestType.UPDATE) // 수정 요청
                .requestStatus(RequestStatus.PENDING) // 대기중
                .userId(userId)
                .requestPerfume(reqPerfume) // 수정 요청 된 향수
                .perfume(originPerfume)   // 기존 향수
                .build();

        requestRepository.save(request);

        return new SuccessResponse<>(REQUEST_COMPLETED, NoneResponse.NONE);


    }

    /**
     * 향수 삭제 요청 api
     *
     * @param perfumeId 삭제할 향수의 ID
     * @param userId    삭제 요청한 유저의 ID
     **/
    public SuccessResponse<NoneResponse> deletePerfumeRequest(Long perfumeId, Long userId) {
        Perfume perfume = perfumeRepository.findById(perfumeId)
                .orElseThrow(() -> new AppException(PERFUME_NOT_FOUND));

        Request request = Request.builder()
                .requestType(RequestType.DELETE) // 삭제 요청
                .requestStatus(RequestStatus.PENDING) // 대기중
                .userId(userId)
                .perfume(perfume) // 기존 향수
                .requestPerfume(null) // 삭제 요청 된 향수
                .build();

        requestRepository.save(request);

        return new SuccessResponse<>(REQUEST_COMPLETED, NoneResponse.NONE);
    }


    /**
     * RequestPerfumeNote에 저장하는 메소드
     *
     * @param perfume     요청 향수정보
     * @param requestNote 요청 노트 정보
     * @param type        노트 타입
     */
    private void saveReqPerfumeNote(RequestPerfume perfume, RequestNote requestNote, NoteType type) {
        RequestPerfumeNote perfumeNote = RequestPerfumeNote.builder()
                .requestPerfume(perfume)
                .requestNote(requestNote)
                .noteType(type)
                .build();
        // PerfumeNote 저장
        reqPerfumeNoteRepository.save(perfumeNote);
    }

    /**
     * 노트의 타입별로 저장하는 메서드
     *
     * @param dto     요청된 모든 정보
     * @param Perfume 요청 향수 정보
     */
    private void saveReqNotes(RequestPerfumeDetailReq dto, RequestPerfume Perfume) {
        saveNotesAndReqPerfumeNote(dto.getTopNote(), Perfume, NoteType.TOP);
        saveNotesAndReqPerfumeNote(dto.getMiddleNote(), Perfume, NoteType.MIDDLE);
        saveNotesAndReqPerfumeNote(dto.getBaseNote(), Perfume, NoteType.BASE);
        saveNotesAndReqPerfumeNote(dto.getSingleNote(), Perfume, NoteType.SINGLE);
    }


    /**
     * 노트 리스트를 분리하여 각각의 노트들을 저장 &<p>
     * RequestPerfumeNote에 저장하는 메서드
     *
     * @param notes      노트 정보
     * @param reqPerfume 요청 향수 정보
     * @param type       노트 타입
     */
    private void saveNotesAndReqPerfumeNote(String notes, RequestPerfume reqPerfume, NoteType type) {

        if (notes != null && !notes.isEmpty()) {
            String[] noteList = notes.split(","); // ,기준으로 노트를 분리
            for (String rawNote : noteList) {
                String note = rawNote.trim(); // 분리된 노트 앞뒤 공백 제거
                if (!note.isEmpty()) {
                    //  RequestNote 저장
                    RequestNote reqNote = requestNoteRepository.findByName(note)
                            .orElseGet(() -> requestNoteRepository.save(
                                    RequestNote.builder()
                                            .name(note)
                                            .build())
                            );
                    // RequestPerfumeNote 저장
                    saveReqPerfumeNote(reqPerfume, reqNote, type);
                }
            }
        }
    }

    // 요청 브렌드를 저장 후 반환
    public RequestBrand saveReqBrand(String brandName) {
        // 브랜드 이름이 이미 존재하면 해당 브랜드를 반환, 없으면 새 브랜드를 생성하여 저장
        return requestBrandRepository.findByName(brandName)
                .orElseGet(() -> requestBrandRepository.save(
                        RequestBrand.builder()
                                .name(brandName)
                                .url(null) // 임시 처리
                                .build()
                ));
    }


    // 요청 향수를 저장 후 반환

    public RequestPerfume saveReqPerfume(String name, int price, RequestBrand requestBrand) {

        RequestPerfume perfume = RequestPerfume.builder()
                .name(name)
                .price(price)
                .requestBrand(requestBrand)
                .build();

        return requestPerfumeRepository.save(perfume);
    }












    /**
     * 향수 등록
     */
    @Transactional
    public SuccessResponse<NoneResponse> registerPerfume(RequestPerfumeDetailReq reqPerfumeDetailReq) {
        // 브랜드에 저장
        Brand Brand = saveBrand(reqPerfumeDetailReq.getBrandName());

        // 향수에 저장
        Perfume perfume = savePerfume(reqPerfumeDetailReq.getName(), reqPerfumeDetailReq.getPrice(), Brand);

        // 노트 & 퍼퓸 노트 저장
        saveNotes(reqPerfumeDetailReq, perfume);

        return new SuccessResponse<>(REGISTER_COMPLETED, NoneResponse.NONE);
    }


    // 브랜드 저장
    public Brand saveBrand(String brandName) {
        // 브랜드 이름이 이미 존재하면 해당 브랜드를 반환, 없으면 새 브랜드를 생성하여 저장
        return brandRepository.findByName(brandName)
                .orElseGet(() -> brandRepository.save(
                        Brand.builder()
                                .name(brandName)
                                .url(null) // 임시 처리
                                .build()
                ));
    }

    // 향수 저장

    public Perfume savePerfume(String name, int price, Brand brand) {

        Perfume perfume = Perfume.builder()
                .name(name)
                .price(price)
                .brand(brand)
                .build();

        return perfumeRepository.save(perfume);
    }

    // 노트를 분리후 저장
    private void saveNotesAndPerfumeNote(String notes, Perfume Perfume, NoteType type) {

        if (notes != null && !notes.isEmpty()) {
            String[] noteList = notes.split(","); // ,기준으로 노트를 분리
            for (String rawNote : noteList) {
                String note = rawNote.trim(); // 분리된 노트 앞뒤 공백 제거
                if (!note.isEmpty()) {
                    //  Note 저장
                    Note noteEntity = noteRepository.findByName(note)
                            .orElseGet(() -> noteRepository.save(
                                    Note.builder()
                                            .name(note)
                                            .build())
                            );

                    // PerfumeNote 저장
                    savePerfumeNote(Perfume, noteEntity, type);
                }
            }
        }
    }

    // PerfumeNote 저장
    private void savePerfumeNote(Perfume perfume, Note note, NoteType type) {
        PerfumeNote perfumeNote = PerfumeNote.builder()
                .perfume(perfume)
                .note(note)
                .noteType(type)
                .build();
        // PerfumeNote 저장
        perfumeNoteRepository.save(perfumeNote);
    }

    // 타입별로 노트 저장
    private void saveNotes(RequestPerfumeDetailReq dto, Perfume Perfume) {
        saveNotesAndPerfumeNote(dto.getTopNote(), Perfume, NoteType.TOP);
        saveNotesAndPerfumeNote(dto.getMiddleNote(), Perfume, NoteType.MIDDLE);
        saveNotesAndPerfumeNote(dto.getBaseNote(), Perfume, NoteType.BASE);
        saveNotesAndPerfumeNote(dto.getSingleNote(), Perfume, NoteType.SINGLE);
    }


    /**
     * 향수 수정 api
     * */
    @Transactional
    public SuccessResponse<NoneResponse> updatePerfume(RequestPerfumeDetailReq reqPerfumeDetailReq, Long perfumeId) {
        // 수정할 향수 조회
        Perfume perfume = perfumeRepository.findById(perfumeId)
                .orElseThrow(() -> new AppException(PERFUME_NOT_FOUND));

        // 기존 브랜드 조회 또는 생성
        Brand brand = brandRepository.findByName(reqPerfumeDetailReq.getBrandName())
                .orElseGet(() -> brandRepository.save(
                        Brand.builder()
                                .name(reqPerfumeDetailReq.getBrandName())
                                .build()
                ));

        perfume.update(reqPerfumeDetailReq.getName(), reqPerfumeDetailReq.getPrice(), brand);

        perfumeRepository.save(perfume);

        perfumeNoteRepository.deleteByPerfume(perfume);

        saveNotes(reqPerfumeDetailReq, perfume);

        return new SuccessResponse<>(UPDATE_COMPLETED, NoneResponse.NONE);
    }


    /**
     * 향수 삭제 api
     * */
    @Transactional
    public SuccessResponse<NoneResponse> deletePerfume(Long perfumeId) {
        Perfume perfume = perfumeRepository.findById(perfumeId)
                .orElseThrow(() -> new AppException(PERFUME_NOT_FOUND));

        List<PerfumeNote> perfumeNotes = perfumeNoteRepository.findByPerfume(perfume);

        perfumeNoteRepository.deleteAll(perfumeNotes);
        perfumeRepository.delete(perfume);

        return new SuccessResponse<>(DELETE_COMPLETED, NoneResponse.NONE);

    }
}
