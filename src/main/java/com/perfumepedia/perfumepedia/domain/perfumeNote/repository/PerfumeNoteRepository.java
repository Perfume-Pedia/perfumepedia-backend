package com.perfumepedia.perfumepedia.domain.perfumeNote.repository;

import com.perfumepedia.perfumepedia.domain.perfumeNote.entity.PerfumeNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerfumeNoteRepository extends JpaRepository<PerfumeNote, Long> {
    List<PerfumeNote> findByNoteContaining(String keyword);

    List<PerfumeNote> findByPerfumeId(Long perfumeId);

}
