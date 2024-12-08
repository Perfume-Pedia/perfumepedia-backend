package com.perfumepedia.perfumepedia.domain.perfume.repository;

import com.perfumepedia.perfumepedia.domain.perfume.entity.RequestPerfume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RequestPerfumeRepository extends JpaRepository<RequestPerfume, Long> {

    Optional<RequestPerfume> findById(Long requestPerfumeId);
}
