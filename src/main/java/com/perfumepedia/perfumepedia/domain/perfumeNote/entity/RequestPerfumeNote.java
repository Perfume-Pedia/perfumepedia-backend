package com.perfumepedia.perfumepedia.domain.perfumeNote.entity;

import com.perfumepedia.perfumepedia.domain.Note.entity.Note;
import com.perfumepedia.perfumepedia.domain.Note.entity.NoteType;
import com.perfumepedia.perfumepedia.domain.Note.entity.RequestNote;
import com.perfumepedia.perfumepedia.domain.perfume.entity.Perfume;
import com.perfumepedia.perfumepedia.domain.perfume.entity.RequestPerfume;
import com.perfumepedia.perfumepedia.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestPerfumeNote extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REQUEST_PERFUME_NOTE_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "REQUEST_PERFUME_ID")
    private RequestPerfume requestPerfume;

    @ManyToOne
    @JoinColumn(name = "REQUEST_NOTE_ID")
    private RequestNote requestNote;

    @Enumerated(EnumType.STRING)
    @Column(name = "NOTE_TYPE")
    private NoteType noteType;

}
