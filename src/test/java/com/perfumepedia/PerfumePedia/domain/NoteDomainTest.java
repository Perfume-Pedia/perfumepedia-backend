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
public class NoteDomainTest {

    @Test
    @DisplayName("매개변수로 빈값을 전달했을 경우 테스트")
    public void 기본생성자_제한() throws Exception{
        //given
        String noteNameIsEmpty = "";
        String noteNameIsNull = null;

        //when
        Throwable exceptionByEmpty = assertThrows(IllegalArgumentException.class, ()->new Note(noteNameIsEmpty));
        Throwable exceptionByNull = assertThrows(IllegalArgumentException.class, ()->new Note(noteNameIsNull));

        //then
        assertEquals("name 은 null 이거나 빈 값일 수 없습니다.", exceptionByEmpty.getMessage());
        assertEquals("name 은 null 이거나 빈 값일 수 없습니다.", exceptionByNull.getMessage());
    }

    @Test
    @DisplayName("매개변수가 있는 생성자 호출 테스트")
    public void 매개변수가_있는_생성자_정상() throws Exception{
        //given
        String noteName = "test note name";

        //when
        Note note = new Note(noteName);

        //then
        assertNotNull(note);
        assertNull("id는 자동으로 생성됩니다.", note.getId());
        assertEquals(note.getName(), noteName);
        assertNull(note.getDbDate());
    }

    @Test
    @DisplayName("dbDate set 메소드 테스트")
    public void dbDate_초기화() throws Exception{
        //given
        String noteName = "test note name";
        String noteDbDate = "2023-11-16";

        //when
        Note note = new Note(noteName);
        note.setDbDate(noteDbDate);

        DBDate dbDate = new DBDate(noteDbDate);

        //then
        assertNotNull(note.getDbDate());
        assertEquals(note.getDbDate().getCreatedAt(), dbDate.getCreatedAt());
        assertEquals(note.getDbDate().getUpdatedAt(), dbDate.getUpdatedAt());
    }
}
