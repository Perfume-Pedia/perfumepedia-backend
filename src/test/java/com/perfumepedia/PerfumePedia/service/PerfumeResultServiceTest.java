package com.perfumepedia.PerfumePedia.service;


import com.perfumepedia.PerfumePedia.dto.PerfumeResult;
import com.perfumepedia.PerfumePedia.dto.ResponseData;
import com.perfumepedia.PerfumePedia.dto.SearchResultDto;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@RequiredArgsConstructor
public class PerfumeResultServiceTest {
    @Autowired BrandService brandService;
    @Autowired PerfumeService perfumeService;
    @Autowired NoteService noteService;
    @Autowired PerfumeNoteService perfumeNoteService;
    @Autowired WordService wordService;
    @Autowired SearchResultService searchResultService;
    @Autowired EntityManager em;

    @Test
    public void 선호향수_검색() throws Exception{
        //given
        List<Long> ids = new ArrayList<>();
        ids.add(12l);
        ids.add(33l);
        ids.add(21l);

        //when
        ResponseData responseData = searchResultService.searchByPerfumeId(ids);
        SearchResultDto searchResultDto = (SearchResultDto) responseData.data();
        List<PerfumeResult> perfumeResults = searchResultDto.getItems();

        //then
        assertNotNull(responseData);
        assertNotNull(searchResultDto);
        assertNotNull(perfumeResults);

        assertEquals("", searchResultDto.getLast_item_id());
        assertEquals(0, perfumeResults.size());


    }
}
