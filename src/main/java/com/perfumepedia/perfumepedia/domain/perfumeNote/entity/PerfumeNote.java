package com.perfumepedia.perfumepedia.domain.perfumeNote.entity;

import com.perfumepedia.perfumepedia.domain.note.entity.Note;
import com.perfumepedia.perfumepedia.domain.note.entity.NoteType;
import com.perfumepedia.perfumepedia.domain.perfume.entity.Perfume;
import com.perfumepedia.perfumepedia.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PerfumeNote extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PERFUME_NOTE_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PERFUME_ID")
    private Perfume perfume;

    @ManyToOne
    @JoinColumn(name = "NOTE_ID")
    private Note note;

    @Enumerated(EnumType.STRING)
    @Column(name = "NOTE_TYPE")
    private NoteType noteType;

    @Builder
    public PerfumeNote(Long id, Perfume perfume, Note note, NoteType noteType) {
        this.id = id;
        this.perfume = perfume;
        this.note = note;
        this.noteType = noteType;
    }
}
