package com.perfumepedia.perfumepedia.domain.note.entity;

import com.perfumepedia.perfumepedia.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Note extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NOTE_ID")
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Builder
    public Note(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
