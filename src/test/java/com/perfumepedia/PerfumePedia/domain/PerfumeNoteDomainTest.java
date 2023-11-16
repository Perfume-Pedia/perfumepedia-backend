package com.perfumepedia.PerfumePedia.domain;


import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@RequiredArgsConstructor
public class PerfumeNoteDomainTest {

    @Test
    @DisplayName("매개 변수로 빈 값을 전달했을 경우 테스트")
    public void 기본생성자_제한() throws Exception{
        //given
        NoteType noteTypeIsNull = null;

        //when
        Throwable exceptionByNull = assertThrows(IllegalArgumentException.class, () -> new PerfumeNote(noteTypeIsNull));

        //then
        assertEquals("noteType 은 null 일 수 없습니다.", exceptionByNull.getMessage());
    }

    @Test
    @DisplayName("매개변수가 있는 생성자 호출 테스트")
    public void 매개변수가_있는_생성자_정상() throws Exception{
        //given
        NoteType noteType = NoteType.TOP;

        //when
        PerfumeNote perfumeNote = new PerfumeNote(noteType);

        //then
        assertNotNull(perfumeNote);
        assertNull("id는 자동으로 생성됩니다.", perfumeNote.getId());

        assertNotNull(perfumeNote.getNoteType());
        assertEquals(noteType, perfumeNote.getNoteType());

        assertNull(perfumeNote.getDbDate());
        assertNull(perfumeNote.getPerfume());
        assertNull(perfumeNote.getNote());
    }

    @Test
    @DisplayName("perfume, note 관계형 메소드 테스트")
    public void perfume_note_관계_설정() throws Exception{
        //given
        Perfume perfume = new Perfume("test perfume name");
        Note note = new Note("test note name");
        NoteType noteType = NoteType.MIDDLE;

        //when
        PerfumeNote perfumeNote = new PerfumeNote(noteType);
        perfumeNote.setPerfume(perfume);
        perfumeNote.setNote(note);

        //then
        assertNotNull(perfumeNote.getPerfume());
        assertEquals(perfume.getName(), perfumeNote.getPerfume().getName());

        assertNotNull(perfumeNote.getNote());
        assertEquals(note.getName(), perfumeNote.getNote().getName());
    }

    @Test
    @DisplayName("dbDate set 메소드 테스트")
    public void dbDate_초기화() throws Exception{
        //given
        NoteType noteType = NoteType.TOP;
        String perfumeNoteDbDate = "2023-11-16";

        //when
        PerfumeNote perfumeNote = new PerfumeNote(noteType);
        perfumeNote.setDbDate(perfumeNoteDbDate);

        DBDate dbDate = new DBDate(perfumeNoteDbDate);

        //then
        assertNotNull(perfumeNote.getDbDate());
        assertEquals(dbDate.getCreatedAt(), perfumeNote.getDbDate().getCreatedAt());
        assertEquals(dbDate.getUpdatedAt(), perfumeNote.getDbDate().getUpdatedAt());
    }
}
