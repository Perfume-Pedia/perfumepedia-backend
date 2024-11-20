package com.perfumepedia.perfumepedia.domain.note.repository;

import com.perfumepedia.perfumepedia.domain.note.entity.RequestNote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestNoteRepository extends JpaRepository<RequestNote, Long> {
}
