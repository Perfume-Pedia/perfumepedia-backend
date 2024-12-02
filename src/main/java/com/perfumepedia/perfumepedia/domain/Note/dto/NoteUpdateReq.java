package com.perfumepedia.perfumepedia.domain.note.dto;

import com.perfumepedia.perfumepedia.domain.note.entity.Note;
import lombok.Builder;
import lombok.Getter;

public record NoteUpdateReq(Long id, String name) {

    @Builder
    public NoteUpdateReq(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Entity -> DTO
    public static NoteUpdateReq toDto(Note note) {
        return NoteUpdateReq.builder()
                .id(note.getId())
                .name(note.getName())
                .build();
    }

    // DTO -> Entity
    public Note toEntity() {
        return Note.builder()
                .id(this.id)
                .name(this.name)
                .build();
    }
}
