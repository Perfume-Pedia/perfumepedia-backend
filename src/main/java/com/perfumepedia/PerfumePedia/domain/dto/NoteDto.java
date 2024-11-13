package com.perfumepedia.PerfumePedia.domain.dto;

import com.perfumepedia.PerfumePedia.domain.entity.Note;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class NoteDto {
    private Long id;
    private String name;

    // 생성자
    public NoteDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Entity -> DTO
    public static NoteDto toDto(Note note) {
        return new NoteDto(
                note.getId(),
                note.getName()
        );
    }

    // DTO -> Entity
    public Note toEntity() {
        return new Note(
                this.id,
                this.name
        );
    }

}
