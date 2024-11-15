package com.perfumepedia.perfumepedia.domain.perfumeNote.entity;

import com.perfumepedia.perfumepedia.domain.Note.entity.Note;
import com.perfumepedia.perfumepedia.domain.Note.entity.NoteType;
import com.perfumepedia.perfumepedia.domain.perfume.entity.Perfume;
import com.perfumepedia.perfumepedia.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
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

}