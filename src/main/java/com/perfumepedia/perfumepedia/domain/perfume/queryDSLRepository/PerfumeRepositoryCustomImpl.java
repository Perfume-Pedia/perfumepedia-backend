package com.perfumepedia.perfumepedia.domain.perfume.queryDSLRepository;

import com.perfumepedia.perfumepedia.domain.perfume.entity.Perfume;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.perfumepedia.perfumepedia.domain.brand.entity.QBrand.brand;
import static com.perfumepedia.perfumepedia.domain.note.entity.QNote.note;
import static com.perfumepedia.perfumepedia.domain.perfume.entity.QPerfume.perfume;
import static com.perfumepedia.perfumepedia.domain.perfumeNote.entity.QPerfumeNote.perfumeNote;

@RequiredArgsConstructor
public class PerfumeRepositoryCustomImpl implements PerfumeRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Perfume> searchPerfumesByKeyword(String keyword) {
        return queryFactory
                .selectDistinct(perfume)  // 중복된 Perfume 데이터 제거
                .from(perfume)
                .leftJoin(perfume.brand, brand)
                .leftJoin(perfumeNote).on(perfumeNote.perfume.eq(perfume))
                .leftJoin(perfumeNote.note, note)
                .where(
                        brand.name.containsIgnoreCase(keyword)
                                .or(perfume.name.containsIgnoreCase(keyword))
                                .or(note.name.containsIgnoreCase(keyword))
                )
                .fetch();
    }

}