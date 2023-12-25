package com.perfumepedia.PerfumePedia.service;


import com.perfumepedia.PerfumePedia.domain.*;
import com.perfumepedia.PerfumePedia.dto.PerfumeDetailDto;
import com.perfumepedia.PerfumePedia.dto.PerfumeResult;
import com.perfumepedia.PerfumePedia.dto.ResponseData;
import com.perfumepedia.PerfumePedia.dto.SearchResultDto;
import com.perfumepedia.PerfumePedia.repository.BrandRepository;
import com.perfumepedia.PerfumePedia.repository.NoteRepository;
import com.perfumepedia.PerfumePedia.repository.PerfumeNoteRepository;
import com.perfumepedia.PerfumePedia.repository.PerfumeRepository;
import com.sun.security.auth.NTSid;
import lombok.RequiredArgsConstructor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@RequiredArgsConstructor
public class PerfumeDetailServiceTest {

    @Autowired PerfumeDetailService perfumeDetailService;

    @Autowired PerfumeService perfumeService;
    @Autowired BrandService brandService;
    @Autowired NoteService noteService;
    @Autowired PerfumeNoteService perfumeNoteService;

    @Autowired PerfumeRepository perfumeRepository;
    @Autowired BrandRepository brandRepository;
    @Autowired NoteRepository noteRepository;
    @Autowired PerfumeNoteRepository perfumeNoteRepository;

    @Before
    public void setUp(){
        for(int i=1; i<=40; i++) {
            Perfume perfume = new Perfume("perfumeName" + i);
            Brand brand = new Brand("brandName" + i);
            Note topNote = new Note("topNoteName" + i);
            Note middleNote = new Note("middleNoteName" + i);
            Note baseNote = new Note("baseNoteName" + i);
            Note singleNote = new Note("singleNoteName" + i);
            PerfumeNote perfumeNote1 = new PerfumeNote(NoteType.TOP);
            PerfumeNote perfumeNote2 = new PerfumeNote(NoteType.MIDDLE);
            PerfumeNote perfumeNote3 = new PerfumeNote(NoteType.BASE);
            PerfumeNote perfumeNote4 = new PerfumeNote(NoteType.SINGLE);

            // brand #set
            brand.setUrl("test.brand.url.com");
            brand.setDbDate("2023-12-08");
            Long savedBrandId =  brandService.saveBrand(brand);
            brand = brandRepository.findById(savedBrandId).get();

            // note #set
            topNote.setDbDate("2023-12-08");
            Long savedTopNoteId =  noteService.saveNote(topNote);
            topNote = noteRepository.findById(savedTopNoteId).get();

            middleNote.setDbDate("2023-12-08");
            Long savedMiddleNoteId =  noteService.saveNote(middleNote);
            middleNote = noteRepository.findById(savedMiddleNoteId).get();

            baseNote.setDbDate("2023-12-08");
            Long savedBaseNoteId =  noteService.saveNote(baseNote);
            baseNote = noteRepository.findById(savedBaseNoteId).get();

            singleNote.setDbDate("2023-12-08");
            Long savedSingleNoteId =  noteService.saveNote(singleNote);
            singleNote = noteRepository.findById(savedSingleNoteId).get();

            // perfume #set
            perfume.setBrand(brand);
            perfume.setUrl("test.perfume.url.com");
            perfume.setPrice(10000);
            perfume.setDbDate("2023-12-08");
            perfume.setImage("image_name", "test/image/path");
            Long savedPerfumeId =  perfumeService.savePerfume(perfume);
            perfume = perfumeRepository.findById(savedPerfumeId).get();

            // perfumeNote #set
            perfumeNote1.setNote(topNote);
            perfumeNote1.setPerfume(perfume);
            perfumeNote1.setDbDate("2023-12-08");
            perfumeNoteService.savePerfumeNote(perfumeNote1);

            perfumeNote2.setNote(middleNote);
            perfumeNote2.setPerfume(perfume);
            perfumeNote2.setDbDate("2023-12-08");
            perfumeNoteService.savePerfumeNote(perfumeNote2);

            perfumeNote3.setNote(baseNote);
            perfumeNote3.setPerfume(perfume);
            perfumeNote3.setDbDate("2023-12-08");
            perfumeNoteService.savePerfumeNote(perfumeNote3);

            perfumeNote4.setNote(singleNote);
            perfumeNote4.setPerfume(perfume);
            perfumeNote4.setDbDate("2023-12-08");
            perfumeNoteService.savePerfumeNote(perfumeNote4);
        }
    }

    @Test
//    @Rollback(value = false)
    public void 향수상세검색_ID가존재하는경우() throws Exception{
        //given
        Long nameNumber = 20l;
        String perfumeName = "perfumeName"+nameNumber;
        String brandName = "brandName"+nameNumber;
        String url = "test.perfume.url.com";
        String imagePath = "test/image/path";
        String price = "10000";
        String topNote = "topNoteName"+nameNumber;
        String middleNote = "middleNoteName"+nameNumber;
        String baseNote = "baseNoteName"+nameNumber;
        String singleNote = "singleNoteName"+nameNumber;

        Long id = perfumeRepository.findByName(perfumeName).orElseThrow(
                () -> new IllegalArgumentException(perfumeName+" 는 없는 향수 이름입니다.")
        ).getId();
        String uuid = id.toString();

        //when
        ResponseData responseData = perfumeDetailService.searchPerfumeWithDetail(id);
        PerfumeDetailDto perfumeDetailDto = (PerfumeDetailDto) responseData.data();

        //then
        assertNotNull(responseData);
        assertNotNull(perfumeDetailDto);

        assertNotNull(perfumeDetailDto.getUuid());
        assertNotNull(perfumeDetailDto.getPerfume_name());
        assertNotNull(perfumeDetailDto.getBrand_name());
        assertNotNull(perfumeDetailDto.getPrice());
        assertNotNull(perfumeDetailDto.getUrl());
        assertNotNull(perfumeDetailDto.getImage_path());
        assertNotNull(perfumeDetailDto.getTop_note_names());
        assertNotNull(perfumeDetailDto.getMiddle_note_names());
        assertNotNull(perfumeDetailDto.getBase_note_names());
        assertNotNull(perfumeDetailDto.getSingle_note_names());

        assertEquals(uuid, perfumeDetailDto.getUuid());
        assertEquals(perfumeName, perfumeDetailDto.getPerfume_name());
        assertEquals(brandName, perfumeDetailDto.getBrand_name());
        assertEquals(url, perfumeDetailDto.getUrl());
        assertEquals(imagePath, perfumeDetailDto.getImage_path());
        assertEquals(price, perfumeDetailDto.getPrice());

        boolean isExistInTopNote = perfumeDetailDto.getTop_note_names()
                .stream().anyMatch(note -> note.equals(topNote));
        boolean isExistInMiddleNote = perfumeDetailDto.getMiddle_note_names()
                .stream().anyMatch(note -> note.equals(middleNote));
        boolean isExistInBaseNote = perfumeDetailDto.getBase_note_names()
                .stream().anyMatch(note -> note.equals(baseNote));
        boolean isExistInSingleNote = perfumeDetailDto.getSingle_note_names()
                .stream().anyMatch(note -> note.equals(singleNote));

        assertTrue(isExistInTopNote);
        assertTrue(isExistInMiddleNote);
        assertTrue(isExistInBaseNote);
        assertTrue(isExistInSingleNote);
    }

    @Test
//    @Rollback(value = false)
    public void 향수상세검색_ID가없는경우() throws Exception{
        Long startId = perfumeRepository.findByName("perfumeName1").orElseThrow(
                () -> new IllegalArgumentException("perfumeName1 은 없는 향수 이름 입니다.")
        ).getId();

        //given
        Long id = startId + 50l;
        String uuid = id.toString();
        String perfumeName = "perfumeName"+uuid;
        String brandName = "brandName"+uuid;
        String url = "test.perfume.url.com";
        String imagePath = "test/image/path";
        String price = "10000";
        String topNote = "topNoteName"+uuid;
        String middleNote = "middleNoteName"+uuid;
        String baseNote = "baseNoteName"+uuid;
        String singleNote = "singleNoteName"+uuid;

        //when
        ResponseData responseData = perfumeDetailService.searchPerfumeWithDetail(id);
        PerfumeDetailDto perfumeDetailDto = (PerfumeDetailDto) responseData.data();

        //then
        assertNotNull(responseData);
        assertNotNull(perfumeDetailDto);

        assertNull(perfumeDetailDto.getUuid());
        assertNull(perfumeDetailDto.getPerfume_name());
        assertNull(perfumeDetailDto.getBrand_name());
        assertNull(perfumeDetailDto.getPrice());
        assertNull(perfumeDetailDto.getUrl());
        assertNull(perfumeDetailDto.getImage_path());
        assertNull(perfumeDetailDto.getTop_note_names());
        assertNull(perfumeDetailDto.getMiddle_note_names());
        assertNull(perfumeDetailDto.getBase_note_names());
        assertNull(perfumeDetailDto.getSingle_note_names());

        assertNotEquals(uuid, perfumeDetailDto.getUuid());
        assertNotEquals(perfumeName, perfumeDetailDto.getPerfume_name());
        assertNotEquals(brandName, perfumeDetailDto.getBrand_name());
        assertNotEquals(url, perfumeDetailDto.getUrl());
        assertNotEquals(imagePath, perfumeDetailDto.getImage_path());
        assertNotEquals(price, perfumeDetailDto.getPrice());

        assertNull(perfumeDetailDto.getTop_note_names());
        assertNull(perfumeDetailDto.getMiddle_note_names());
        assertNull(perfumeDetailDto.getBase_note_names());
        assertNull(perfumeDetailDto.getSingle_note_names());
    }
}
