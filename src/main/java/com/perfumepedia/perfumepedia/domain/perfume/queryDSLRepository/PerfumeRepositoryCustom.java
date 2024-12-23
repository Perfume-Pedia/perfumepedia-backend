package com.perfumepedia.perfumepedia.domain.perfume.queryDSLRepository;

import com.perfumepedia.perfumepedia.domain.perfume.entity.Perfume;

import java.util.List;

public interface PerfumeRepositoryCustom {
    List<Perfume> searchPerfumesByKeyword(String keyword);

}
