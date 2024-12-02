package com.perfumepedia.perfumepedia.domain.brand.repository;

import com.perfumepedia.perfumepedia.domain.brand.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    List<Brand> findByNameContaining(String keyword);

    Optional<Brand> findByName(String name);
}
