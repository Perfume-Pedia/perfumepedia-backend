package com.perfumepedia.perfumepedia.domain.perfumeNote.repository;

import com.perfumepedia.perfumepedia.domain.note.entity.Note;
import com.perfumepedia.perfumepedia.domain.perfume.entity.Perfume;
import com.perfumepedia.perfumepedia.domain.perfumeNote.entity.PerfumeNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerfumeNoteRepository extends JpaRepository<PerfumeNote, Long> {
    List<PerfumeNote> findByNote_NameContaining(String keyword);

    List<PerfumeNote> findByPerfumeId(Long perfumeId);

    List<PerfumeNote> findByPerfume(Perfume Perfume);

    void deleteByPerfume(Perfume perfume);




}
