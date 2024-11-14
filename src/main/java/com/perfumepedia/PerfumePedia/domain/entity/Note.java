package com.perfumepedia.PerfumePedia.domain.entity;


import com.perfumepedia.PerfumePedia.domain.dto.NoteDto;
import com.perfumepedia.PerfumePedia.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Note extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NOTE_ID")
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

}
