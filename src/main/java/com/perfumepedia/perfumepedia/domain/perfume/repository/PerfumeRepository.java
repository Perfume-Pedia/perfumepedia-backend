package com.perfumepedia.perfumepedia.domain.perfume.repository;

import com.perfumepedia.perfumepedia.domain.perfume.entity.Perfume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PerfumeRepository extends JpaRepository<Perfume, Long> {
    List<Perfume> findByNameContaining(String keyword);

    List<Perfume> findByBrandNameContaining(String keyword);

    long count();

    Optional<Perfume> findByName(String name);
}
