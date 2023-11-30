package com.perfumepedia.PerfumePedia.service;


import com.perfumepedia.PerfumePedia.domain.*;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@RequiredArgsConstructor
public class PerfumeNoteServiceTest {

    @Autowired PerfumeNoteService perfumeNoteService;
    @Autowired BrandService brandService;
    @Autowired PerfumeService perfumeService;
    @Autowired NoteService noteService;
    @Autowired EntityManager em;

    String perfumeName = "test perfume name";
    String brandName = "test brand name";
    String noteName = "test note name";
    String date = "2023-11-30";
    NoteType topNote = NoteType.TOP;
    NoteType middleNote = NoteType.MIDDLE;
    NoteType baseNote = NoteType.BASE;
    NoteType singleNote = NoteType.SINGLE;


    @Test
    public void 향수노트_추가() throws Exception{
        //given
        Brand brand = new Brand(brandName);
        Perfume perfume = new Perfume(perfumeName);
        perfume.setBrand(brand);
        Note note = new Note(noteName);

        PerfumeNote topPerfumeNote = new PerfumeNote(topNote);
        PerfumeNote middlePerfumeNote = new PerfumeNote(middleNote);
        PerfumeNote basePerfumeNote = new PerfumeNote(baseNote);
        PerfumeNote singlePerfumeNote = new PerfumeNote(singleNote);

        topPerfumeNote.setPerfume(perfume);
        topPerfumeNote.setNote(note);

        middlePerfumeNote.setPerfume(perfume);
        middlePerfumeNote.setNote(note);

        basePerfumeNote.setPerfume(perfume);
        basePerfumeNote.setNote(note);

        singlePerfumeNote.setPerfume(perfume);
        singlePerfumeNote.setNote(note);

        //when
        Long savedBrandId = brandService.saveBrand(brand);
        Long savedPerfumeId = perfumeService.savePerfume(perfume);
        Long savedNoteId = noteService.saveNote(note);

        Long savedTopPerfumeNoteId = perfumeNoteService.savePerfumeNote(topPerfumeNote);
        Long savedMiddlePerfumeNoteId = perfumeNoteService.savePerfumeNote(middlePerfumeNote);
        Long savedBasePerfumeNoteId = perfumeNoteService.savePerfumeNote(basePerfumeNote);
        Long savedSinglePerfumeNoteId = perfumeNoteService.savePerfumeNote(singlePerfumeNote);

        //then
        em.flush();
        assertNotNull(topPerfumeNote.getId());
//        assertNotNull(middlePerfumeNote.getId());
//        assertNotNull(basePerfumeNote.getId());
//        assertNotNull(singlePerfumeNote.getId());

        assertEquals(savedTopPerfumeNoteId, topPerfumeNote.getId());
//        assertEquals(savedMiddlePerfumeNoteId, middlePerfumeNote.getId());
//        assertEquals(savedBasePerfumeNoteId, basePerfumeNote.getId());
//        assertEquals(savedSinglePerfumeNoteId, singlePerfumeNote.getId());

        assertEquals(savedPerfumeId, topPerfumeNote.getPerfume().getId());
//        assertEquals(savedPerfumeId, middlePerfumeNote.getPerfume().getId());
//        assertEquals(savedPerfumeId, basePerfumeNote.getPerfume().getId());
//        assertEquals(savedPerfumeId, singlePerfumeNote.getPerfume().getId());

        assertEquals(savedNoteId, topPerfumeNote.getNote().getId());
//        assertEquals(savedNoteId, middlePerfumeNote.getNote().getId());
//        assertEquals(savedNoteId, basePerfumeNote.getNote().getId());
//        assertEquals(savedNoteId, singlePerfumeNote.getNote().getId());
    }
}
