package com.perfumepedia.perfumepedia.domain.note.dto;

import com.perfumepedia.perfumepedia.domain.note.entity.RequestNote;
import lombok.Builder;
import lombok.Getter;

public record RequestNoteUpdateReq(Long id, String name) {

    @Builder
    public RequestNoteUpdateReq(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Entity -> DTO
    public static RequestNoteUpdateReq toDto(RequestNote requestNote) {
        return RequestNoteUpdateReq.builder()
                .id(requestNote.getId())
                .name(requestNote.getName())
                .build();
    }

    // DTO -> Entity
    public RequestNote toEntity() {
        return RequestNote.builder()
                .id(this.id)
                .name(this.name)
                .build();
    }
}
