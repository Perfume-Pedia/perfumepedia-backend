package com.perfumepedia.perfumepedia.domain.Note.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestNoteDto {
    private Long id;
    private String name;

    // 생성자
    public RequestNoteDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Entity -> DTO
    public static RequestNoteDto toDto(RequestNoteDto note) {
        return new RequestNoteDto(
                note.getId(),
                note.getName()
        );
    }

    // DTO -> Entity
    public RequestNoteDto toEntity() {
        return new RequestNoteDto(
                this.id,
                this.name
        );
    }
}
