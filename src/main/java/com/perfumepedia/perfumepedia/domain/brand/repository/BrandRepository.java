package com.perfumepedia.perfumepedia.domain.brand.repository;

import com.perfumepedia.perfumepedia.domain.brand.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {


    // 해당 테이블의 모든 데이터 개수를 반환
    long count();

    Optional<Brand> findByName(String name);

}
