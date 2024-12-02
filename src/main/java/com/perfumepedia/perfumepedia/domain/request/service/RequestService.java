package com.perfumepedia.perfumepedia.domain.request.service;

import com.perfumepedia.perfumepedia.domain.request.dto.RequestListDto;
import com.perfumepedia.perfumepedia.domain.request.entity.Request;
import com.perfumepedia.perfumepedia.domain.request.entity.RequestStatus;
import com.perfumepedia.perfumepedia.domain.request.entity.RequestType;
import com.perfumepedia.perfumepedia.domain.request.repository.RequestRepository;
import com.perfumepedia.perfumepedia.global.response.SuccessResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
     */
    public SuccessResponse<Map<String, Long>> getRequestCount() {
        Map<String, Long> requestCount = new HashMap<>();
        requestCount.put("CREATE", requestRepository.countByRequestTypeAndRequestStatus(RequestType.CREATE, RequestStatus.PENDING));
        requestCount.put("UPDATE", requestRepository.countByRequestTypeAndRequestStatus(RequestType.UPDATE, RequestStatus.PENDING));
        requestCount.put("DELETE", requestRepository.countByRequestTypeAndRequestStatus(RequestType.DELETE, RequestStatus.PENDING));

        return new SuccessResponse<>(REQUEST_COMPLETED, requestCount);
    }

    /**
     * 등록 요청 조회
     * */
    public SuccessResponse<List<RequestListDto>> getRegisterRequests() {
        return getRequests(RequestType.CREATE);
    }

    /**
     * 수정 요청 조회
     * */
    public SuccessResponse<List<RequestListDto>> getUpdateRequests() {
        return getRequests(RequestType.UPDATE);
    }

    /**
     * 삭제 요청 조회
     * */
    public SuccessResponse<List<RequestListDto>> getDeleteRequests() {
        return getRequests(RequestType.DELETE);
    }

    /**
     * 요청 타입별 요청 조회 후 RequestListDto에 저장 <p>
     * 등록 요청 - RequestPerfume 에서 받아옴<p>
     * 수정, 삭제 요청 - Perfume 에서 받아옴
     * */
    public SuccessResponse<List<RequestListDto>> getRequests(RequestType requestType) {
        List<Request> requests = requestRepository.findByRequestTypeAndRequestStatus(requestType, RequestStatus.PENDING);
        List<RequestListDto> requestList = new ArrayList<>();

        for (Request request : requests) {
            Long perfumeId = null;
            String perfumeName = null;

            if (requestType == RequestType.CREATE) {
                perfumeId = request.getRequestPerfume().getId();
                perfumeName = request.getRequestPerfume().getName();
            } else {
                perfumeId = request.getPerfume().getId();
                perfumeName = request.getPerfume().getName();
            }

            RequestListDto requestDto = RequestListDto.builder()
                    .perfumeId(perfumeId)
                    .perfumeName(perfumeName)
                    .build();

            requestList.add(requestDto);
        }

        return new SuccessResponse<>(REQUEST_COMPLETED, requestList);
    }


}
