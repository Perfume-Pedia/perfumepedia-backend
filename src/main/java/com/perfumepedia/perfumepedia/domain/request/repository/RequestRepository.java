package com.perfumepedia.perfumepedia.domain.request.repository;

import com.perfumepedia.perfumepedia.domain.request.entity.Request;
import com.perfumepedia.perfumepedia.domain.request.entity.RequestStatus;
import com.perfumepedia.perfumepedia.domain.request.entity.RequestType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Long> {

    // 요청이 없을 경우 0을 반환
    long countByRequestTypeAndRequestStatus(RequestType requestType, RequestStatus requestStatus);

}
