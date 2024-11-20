package com.perfumepedia.perfumepedia.domain.perfumeNote.repository;

import com.perfumepedia.perfumepedia.domain.perfumeNote.entity.RequestPerfumeNote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestPerfumeNoteRepository extends JpaRepository<RequestPerfumeNote, Long> {
}
