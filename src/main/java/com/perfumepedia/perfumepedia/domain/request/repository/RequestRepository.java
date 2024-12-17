package com.perfumepedia.perfumepedia.domain.request.repository;

import com.perfumepedia.perfumepedia.domain.request.entity.Request;
import com.perfumepedia.perfumepedia.domain.request.entity.RequestStatus;
import com.perfumepedia.perfumepedia.domain.request.entity.RequestType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RequestRepository extends JpaRepository<Request, Long> {

    // 요청이 없을 경우 0을 반환
    long countByRequestTypeAndRequestStatus(RequestType requestType, RequestStatus requestStatus);

    List<Request> findByRequestTypeAndRequestStatus(RequestType requestType, RequestStatus requestStatus);

    Optional<Request> findByIdAndRequestType(Long id, RequestType requestType);




}
