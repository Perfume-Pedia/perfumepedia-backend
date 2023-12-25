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

        topPerfumeNote.setPerfume(perfume);
        topPerfumeNote.setNote(note);


        //when
        Long savedBrandId = brandService.saveBrand(brand);
        Long savedPerfumeId = perfumeService.savePerfume(perfume);
        Long savedNoteId = noteService.saveNote(note);
        Long savedTopPerfumeNoteId = perfumeNoteService.savePerfumeNote(topPerfumeNote);

        //then
        em.flush();
        assertNotNull(topPerfumeNote.getId());
        assertEquals(savedTopPerfumeNoteId, topPerfumeNote.getId());
        assertEquals(savedPerfumeId, topPerfumeNote.getPerfume().getId());
        assertEquals(savedNoteId, topPerfumeNote.getNote().getId());

    }

    @Test
    public void 향수노트_중복_검사() throws Exception{
        //given
        Brand brand = new Brand(brandName);
        Perfume perfume = new Perfume(perfumeName);
        perfume.setBrand(brand);
        Note note = new Note(noteName);

        PerfumeNote topPerfumeNote = new PerfumeNote(topNote);
        PerfumeNote middlePerfumeNote = new PerfumeNote(middleNote);

        topPerfumeNote.setPerfume(perfume);
        topPerfumeNote.setNote(note);

        middlePerfumeNote.setPerfume(perfume);
        middlePerfumeNote.setNote(note);

        //when
        Long savedBrandId = brandService.saveBrand(brand);
        Long savedPerfumeId = perfumeService.savePerfume(perfume);
        Long savedNoteId = noteService.saveNote(note);
        Long savedTopPerfumeNoteId = perfumeNoteService.savePerfumeNote(topPerfumeNote);
        Long savedMiddlePerfumeNoteId = perfumeNoteService.savePerfumeNote(middlePerfumeNote);

        //then
        em.flush();
        assertNotNull(topPerfumeNote.getId());
        assertNull(middlePerfumeNote.getId());

        assertEquals(savedMiddlePerfumeNoteId, savedTopPerfumeNoteId);
    }
}
