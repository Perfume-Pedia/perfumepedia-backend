package com.perfumepedia.PerfumePedia.domain.entity;


import com.perfumepedia.PerfumePedia.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
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
