package com.perfumepedia.PerfumePedia.service;

import com.perfumepedia.PerfumePedia.domain.DBDate;
import com.perfumepedia.PerfumePedia.domain.Note;
import com.perfumepedia.PerfumePedia.repository.NoteRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
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
public class NoteServiceTest {

    @Autowired NoteService noteService;
    @Autowired NoteRepository noteRepository;
    @Autowired EntityManager em;

    @Test
    public void 노트_추가() throws Exception{
        //given
        Note note = new Note("test note name");

        //when
        Long savedId = noteService.saveNote(note);

        //then
        em.flush();
        assertNotNull(note.getId());
        assertNotNull(note.getName());
        assertNull(note.getDbDate());
        assertNull(note.getDbDate());

        assertEquals(savedId, note.getId());
        assertEquals("test note name", note.getName());
    }

    @Test
    public void 노트_중복_검사() throws Exception{
        //given
        Note note1 = new Note("same note name");
        Note note2 = new Note("same note name");

        note1.setDbDate("2023-11-30");
        note2.setDbDate("2023-12-01");

        //when
        Long saveIdByNote1 = noteService.saveNote(note1);
        Long saveIdByNote2 = noteService.saveNote(note2);

        Note noteByNote1 = noteRepository.findById(saveIdByNote1).get();
        Note noteByNote2 = noteRepository.findById(saveIdByNote2).get();

        //then
        em.flush();
        assertNotNull(note1.getId());
        assertNull(note2.getId());
        assertEquals(saveIdByNote1, saveIdByNote2);

        assertEquals(new DBDate("2023-11-30").getCreatedAt(), note1.getDbDate().getCreatedAt());
        assertEquals(new DBDate("2023-11-30").getUpdatedAt(), note1.getDbDate().getUpdatedAt());

        assertNotEquals(new DBDate("2023-12-01").getUpdatedAt(), note1.getDbDate().getUpdatedAt());

        assertEquals(noteByNote1, noteByNote2);
    }
}
