package com.perfumepedia.perfumepedia.domain.perfumeNote.dto;

import com.perfumepedia.perfumepedia.domain.Note.entity.Note;
import com.perfumepedia.perfumepedia.domain.Note.entity.NoteType;
import com.perfumepedia.perfumepedia.domain.perfume.entity.Perfume;
import com.perfumepedia.perfumepedia.domain.perfumeNote.entity.PerfumeNote;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PerfumeNoteDto {

    private Long id;
    private Long perfumeId;
    private String perfumeName;
    private Long noteId;
    private String noteName;
    private String noteType;

    // 생성자
    public PerfumeNoteDto(Long id, Long perfumeId, String perfumeName, Long noteId, String noteName, String noteType) {
        this.id = id;
        this.perfumeId = perfumeId;
        this.perfumeName = perfumeName;
        this.noteId = noteId;
        this.noteName = noteName;
        this.noteType = noteType;
    }

    // Entity -> DTO
    public static PerfumeNoteDto toDto(PerfumeNote perfumeNote) {
        return new PerfumeNoteDto(
                perfumeNote.getId(),
                perfumeNote.getPerfume().getId(),
                perfumeNote.getPerfume().getName(),
                perfumeNote.getNote().getId(),
                perfumeNote.getNote().getName(),
                perfumeNote.getNoteType().name()
        );
    }

    // DTO -> Entity
    public PerfumeNote toEntity(Perfume perfume, Note note) {
        return new PerfumeNote(
                this.id,
                perfume,
                note,
                NoteType.valueOf(this.noteType)  // String 값을 NoteType Enum으로 변환
        );
    }
}