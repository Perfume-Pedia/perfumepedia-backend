package com.perfumepedia.perfumepedia.searchPerfumes;

import com.perfumepedia.perfumepedia.domain.brand.entity.Brand;
import com.perfumepedia.perfumepedia.domain.note.entity.Note;
import com.perfumepedia.perfumepedia.domain.note.entity.NoteType;
import com.perfumepedia.perfumepedia.domain.perfume.dto.PerfumeUpdateReq;
import com.perfumepedia.perfumepedia.domain.perfume.entity.Perfume;
import com.perfumepedia.perfumepedia.domain.perfume.repository.PerfumeRepository;
import com.perfumepedia.perfumepedia.domain.perfume.service.PerfumeService;
import com.perfumepedia.perfumepedia.domain.perfumeNote.entity.PerfumeNote;
import com.perfumepedia.perfumepedia.domain.perfumeNote.repository.PerfumeNoteRepository;
import com.perfumepedia.perfumepedia.global.handler.AppException;
import com.perfumepedia.perfumepedia.global.response.SuccessResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SearchPerfumesTest {

    @Mock
    private PerfumeRepository perfumeRepository;

    @Mock
    private PerfumeNoteRepository perfumeNoteRepository;

    @InjectMocks
    private PerfumeService perfumeService;

    private Brand brand1;
    private Note note1;
    private Perfume perfume1;
    private PerfumeNote perfumeNote1;

    @BeforeEach
    void setUp() {
        brand1 = Brand.builder()
                .id(1L)
                .name("조말론")
                .url("조말론.com")
                .build();

        note1 = Note.builder()
                .id(1L)
                .name("바닐라")
                .build();

        perfume1 = Perfume.builder()
                .id(1L)
                .name("블랙배리")
                .price(40000)
                .brand(brand1)
                .build();

        perfumeNote1 = PerfumeNote.builder()
                .id(1L)
                .perfume(perfume1)
                .note(note1)
                .noteType(NoteType.TOP)
                .build();

    }


    @Test
    void 브랜드로_향수검색() {

        // when : 향수 검색
        when(perfumeRepository.findByBrand_NameContaining("조말론"))
                .thenReturn(List.of(perfume1));

        when(perfumeRepository.findByNameContaining("조말론"))
                .thenReturn(Collections.emptyList());


        when(perfumeNoteRepository.findByNote_NameContaining("조말론"))
                .thenReturn(Collections.emptyList());

        // then : 검색 결과
        // 존재하는 향수 검색
        SuccessResponse<List<PerfumeUpdateReq>> existPerfume =
                perfumeService.searchPerfumes("조말론");

        assertThat(existPerfume.data()).isNotNull();
        assertThat(existPerfume.data().get(0).name()).isEqualTo("블랙배리");
        assertThat(existPerfume.data().get(0).price()).isEqualTo(40000);
        assertThat(existPerfume.data().get(0).brandName()).isEqualTo("조말론");
    }

    @Test
    void 향수이름으로_검색() {
        when(perfumeRepository.findByBrand_NameContaining("블랙배리"))
                .thenReturn(Collections.emptyList());
        when(perfumeRepository.findByNameContaining("블랙배리"))
                .thenReturn(List.of(perfume1));
        when(perfumeNoteRepository.findByNote_NameContaining("블랙배리"))
                .thenReturn(Collections.emptyList());

        SuccessResponse<List<PerfumeUpdateReq>> perfume = perfumeService.searchPerfumes("블랙배리");

        assertThat(perfume.data()).isNotNull();
        assertThat(perfume.data().get(0).name()).isEqualTo("블랙배리");
    }

    @Test
    void 노트로_향수검색() {
        when(perfumeRepository.findByBrand_NameContaining("바닐라"))
                .thenReturn(Collections.emptyList());
        when(perfumeRepository.findByNameContaining("바닐라"))
                .thenReturn(Collections.emptyList());
        when(perfumeNoteRepository.findByNote_NameContaining("바닐라"))
                .thenReturn(List.of(perfumeNote1));

        SuccessResponse<List<PerfumeUpdateReq>> perfume = perfumeService.searchPerfumes("바닐라");

        assertThat(perfume.data()).isNotNull();
        assertThat(perfume.data().get(0).name()).isEqualTo("블랙배리");
    }

    @Test
    void 존재하지_않는_향수검색() {
        // Given
        when(perfumeRepository.findByBrand_NameContaining("바닐라"))
                .thenReturn(Collections.emptyList());
        when(perfumeRepository.findByNameContaining("바닐라"))
                .thenReturn(Collections.emptyList());
        when(perfumeNoteRepository.findByNote_NameContaining("바닐라"))
                .thenReturn(Collections.emptyList());

        // When & Then
        try {
            perfumeService.searchPerfumes("바닐라");
        } catch (AppException e) {
            assertThat(e.getMessage()).isEqualTo("일치하는 향수가 업서용.");
        }
    }

}
