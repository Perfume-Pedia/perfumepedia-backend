package com.perfumepedia.perfumepedia.domain.perfumeNote.repository;

import com.perfumepedia.perfumepedia.domain.perfume.entity.RequestPerfume;
import com.perfumepedia.perfumepedia.domain.perfumeNote.entity.QRequestPerfumeNote;
import com.perfumepedia.perfumepedia.domain.perfumeNote.entity.RequestPerfumeNote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestPerfumeNoteRepository extends JpaRepository<RequestPerfumeNote, Long> {

    List<RequestPerfumeNote> findByRequestPerfumeId(Long requestPerfumeId);

    List<RequestPerfumeNote> findByRequestPerfume(RequestPerfume requestPerfume);

}