package com.perfumepedia.perfumepedia.domain.perfumeNote.repository;

import com.perfumepedia.perfumepedia.domain.perfumeNote.entity.PerfumeNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfumeNoteRepository extends JpaRepository<PerfumeNote, Long> {

}
