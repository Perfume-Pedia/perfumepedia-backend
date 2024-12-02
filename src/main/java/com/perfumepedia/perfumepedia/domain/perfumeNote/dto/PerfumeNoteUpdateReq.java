package com.perfumepedia.perfumepedia.domain.perfumeNote.dto;

import com.perfumepedia.perfumepedia.domain.note.entity.Note;
import com.perfumepedia.perfumepedia.domain.note.entity.NoteType;
import com.perfumepedia.perfumepedia.domain.perfume.entity.Perfume;
import com.perfumepedia.perfumepedia.domain.perfumeNote.entity.PerfumeNote;
import lombok.Builder;

public record PerfumeNoteUpdateReq(Long id, Long perfumeId, String perfumeName, Long noteId, String noteName,
                                   String noteType) {

    @Builder
    public PerfumeNoteUpdateReq(Long id, Long perfumeId, String perfumeName, Long noteId, String noteName, String noteType) {
        this.id = id;
        this.perfumeId = perfumeId;
        this.perfumeName = perfumeName;
        this.noteId = noteId;
        this.noteName = noteName;
        this.noteType = noteType;
    }

    // Entity -> DTO
    public static PerfumeNoteUpdateReq toDto(PerfumeNote perfumeNote) {
        return PerfumeNoteUpdateReq.builder()
                .id(perfumeNote.getId())
                .perfumeId(perfumeNote.getPerfume().getId())
                .perfumeName(perfumeNote.getPerfume().getName())
                .noteId(perfumeNote.getNote().getId())
                .noteName(perfumeNote.getNote().getName())
                .noteType(perfumeNote.getNoteType().name())
                .build();
    }

    // DTO -> Entity
    public PerfumeNote toEntity(Perfume perfume, Note note) {
        return PerfumeNote.builder()
                .id(this.id)
                .perfume(perfume)
                .note(note)
                .noteType(NoteType.valueOf(this.noteType)) // Enum으로 변환
                .build();
    }
}
