package com.perfumepedia.perfumepedia.domain.brand.repository;

import com.perfumepedia.perfumepedia.domain.brand.entity.RequestBrand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestBrandRepository extends JpaRepository<RequestBrand, Long> {
}
