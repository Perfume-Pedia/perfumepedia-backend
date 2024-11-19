package com.perfumepedia.perfumepedia.domain.request.repository;

import com.perfumepedia.perfumepedia.domain.request.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Long> {
}
