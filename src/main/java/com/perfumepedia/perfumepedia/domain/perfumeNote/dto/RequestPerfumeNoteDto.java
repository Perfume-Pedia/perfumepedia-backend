package com.perfumepedia.perfumepedia.domain.perfumeNote.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestPerfumeNoteDto {

    private Long id;
    private Long requestPerfumeId;
    private String requestPerfumeName;
    private Long requestNoteId;
    private String requestNoteName;
    private String noteType;

    // 생성자
    public RequestPerfumeNoteDto(Long id, Long requestPerfumeId, String requestPerfumeName, Long requestNoteId, String requestNoteName, String noteType) {
        this.id = id;
        this.requestPerfumeId = requestPerfumeId;
        this.requestPerfumeName = requestPerfumeName;
        this.requestNoteId = requestNoteId;
        this.requestNoteName = requestNoteName;
        this.noteType = noteType;
    }

    // Entity -> DTO
    public static RequestPerfumeNoteDto toDto(RequestPerfumeNoteDto perfumeNote) {
        return new RequestPerfumeNoteDto(
                perfumeNote.getId(),
                perfumeNote.getRequestPerfumeId(),
                perfumeNote.getRequestPerfumeName(),
                perfumeNote.getRequestNoteId(),
                perfumeNote.getRequestNoteName(),
                perfumeNote.getNoteType()
        );
    }

    // DTO -> Entity
    public RequestPerfumeNoteDto toEntity() {
        return new RequestPerfumeNoteDto(
                this.id,
                this.requestPerfumeId,
                this.requestPerfumeName,
                this.requestNoteId,
                this.requestNoteName,
                this.noteType
        );
    }


}
