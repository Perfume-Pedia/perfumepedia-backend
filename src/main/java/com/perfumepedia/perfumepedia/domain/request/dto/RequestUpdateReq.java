package com.perfumepedia.perfumepedia.domain.request.dto;

import com.perfumepedia.perfumepedia.domain.request.entity.Request;
import com.perfumepedia.perfumepedia.domain.request.entity.RequestStatus;
import com.perfumepedia.perfumepedia.domain.request.entity.RequestType;
import com.perfumepedia.perfumepedia.domain.perfumeNote.entity.PerfumeNote;
import lombok.Builder;

public record RequestUpdateReq(
        Long id,
        String type,
        String status,
        String userName,
        Long requestPerfumeNoteId,
        Long perfumeNoteId) {

    @Builder
    public RequestUpdateReq {
    }

    // Entity -> DTO 변환 메서드
    public static RequestUpdateReq toDto(Request request) {
        return RequestUpdateReq.builder()
                .id(request.getId())
                .type(request.getRequestType().toString())
                .status(request.getRequestStatus().toString())
//                .userName(request.getUser().getNickname()); // 유저 도메인없음
                .requestPerfumeNoteId(request.getRequestPerfumeNote().getId())
                .perfumeNoteId(request.getPerfumeNote() != null ? request.getPerfumeNote().getId() : null)
                .build();
    }

//     DTO -> Entity 변환 메서드
//    public Request toEntity(RequestPerfumeNote requestPerfumeNote, PerfumeNote perfumeNote, User user) {
//        return Request.builder()
//                .id(this.id)
//                .requestType(RequestType.valueOf(this.type)) // Enum으로 변환
//                .requestStatus(RequestStatus.valueOf(this.status)) // Enum으로 변환
//                .user(user) // 유저 도메인없음
//                .requestPerfumeNote(requestPerfumeNote)
//                .perfumeNote(perfumeNote)
//                .build();
//    }
}
