package com.perfumepedia.perfumepedia.domain.perfumeNote.dto;

import com.perfumepedia.perfumepedia.domain.perfumeNote.entity.RequestPerfumeNote;
import com.perfumepedia.perfumepedia.domain.perfume.entity.RequestPerfume;
import com.perfumepedia.perfumepedia.domain.note.entity.RequestNote;
import com.perfumepedia.perfumepedia.domain.note.entity.NoteType;
import lombok.Builder;

public record RequestPerfumeNoteUpdateReq(Long id, Long requestPerfumeId, String requestPerfumeName,
                                          Long requestNoteId, String requestNoteName, String noteType) {

    @Builder
    public RequestPerfumeNoteUpdateReq(Long id, Long requestPerfumeId, String requestPerfumeName,
                                       Long requestNoteId, String requestNoteName, String noteType) {
        this.id = id;
        this.requestPerfumeId = requestPerfumeId;
        this.requestPerfumeName = requestPerfumeName;
        this.requestNoteId = requestNoteId;
        this.requestNoteName = requestNoteName;
        this.noteType = noteType;
    }

    // Entity -> DTO
    public static RequestPerfumeNoteUpdateReq toDto(RequestPerfumeNote requestPerfumeNote) {
        return RequestPerfumeNoteUpdateReq.builder()
                .id(requestPerfumeNote.getId())
                .requestPerfumeId(requestPerfumeNote.getRequestPerfume().getId())
                .requestPerfumeName(requestPerfumeNote.getRequestPerfume().getName()) // RequestPerfume에서 name을 가져옴
                .requestNoteId(requestPerfumeNote.getRequestNote().getId())
                .requestNoteName(requestPerfumeNote.getRequestNote().getName()) // RequestNote에서 name을 가져옴
                .noteType(requestPerfumeNote.getNoteType().name()) // String으로 변환
                .build();
    }

    // DTO -> Entity
    public RequestPerfumeNote toEntity(RequestPerfume requestPerfume, RequestNote requestNote) {
        return RequestPerfumeNote.builder()
                .id(this.id)
                .requestPerfume(requestPerfume)
                .requestNote(requestNote)
                .noteType(NoteType.valueOf(this.noteType))  // Enum으로 변환
                .build();
    }
}
