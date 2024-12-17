package com.perfumepedia.perfumepedia.domain.brand.repository;

import com.perfumepedia.perfumepedia.domain.brand.entity.Brand;
import com.perfumepedia.perfumepedia.domain.brand.entity.RequestBrand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RequestBrandRepository extends JpaRepository<RequestBrand, Long> {
    Optional<RequestBrand> findByName(String name);


}
