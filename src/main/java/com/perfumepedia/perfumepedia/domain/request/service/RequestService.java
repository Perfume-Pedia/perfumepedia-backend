package com.perfumepedia.perfumepedia.domain.request.service;

import com.perfumepedia.perfumepedia.domain.request.entity.RequestStatus;
import com.perfumepedia.perfumepedia.domain.request.entity.RequestType;
import com.perfumepedia.perfumepedia.domain.request.repository.RequestRepository;
import com.perfumepedia.perfumepedia.global.response.SuccessResponse;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.perfumepedia.perfumepedia.global.enums.SuccessCode.REQUEST_COMPLETED;

@Service
public class RequestService {
    private final RequestRepository requestRepository;

    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    /**
     * 요청 타입별 요청 개수 조회
     * */
    public SuccessResponse<Map<String, Long>> getRequestCount() {
        Map<String, Long> requestCount = new HashMap<>();
        requestCount.put("CREATE", requestRepository.countByRequestTypeAndRequestStatus(RequestType.CREATE, RequestStatus.PENDING));
        requestCount.put("UPDATE", requestRepository.countByRequestTypeAndRequestStatus(RequestType.UPDATE, RequestStatus.PENDING));
        requestCount.put("DELETE", requestRepository.countByRequestTypeAndRequestStatus(RequestType.DELETE, RequestStatus.PENDING));

        return new SuccessResponse<>(REQUEST_COMPLETED, requestCount);
    }


}
