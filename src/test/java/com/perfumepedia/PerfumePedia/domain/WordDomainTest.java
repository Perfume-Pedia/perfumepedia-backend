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
public class WordDomainTest {

    @Test
    @DisplayName("매개변수로 빈값을 전달했을 경우 테스트")
    public void 기본생성자_제한() throws Exception{
        //given
        String wordAliasIsEmpty = "";
        String wordAliasIsNull = null;
        String wordAliasIsNormal = "test alias";

        String wordNameIsEmpty = "";
        String wordNameIsNull = null;
        String wordNameIsNormal = "test name";

        WordType wordTypeIsNull = null;
        WordType wordTypeIsNormal = WordType.PERFUME;

        Word wordIsNull = null;

        //when
        Throwable exceptionByAliasEmpty = assertThrows(IllegalArgumentException.class, () ->
                new Word(wordAliasIsEmpty, wordNameIsNormal, wordTypeIsNormal));
        Throwable exceptionByAliasNull = assertThrows(IllegalArgumentException.class, () ->
                new Word(wordAliasIsNull, wordNameIsNormal, wordTypeIsNormal));
        Throwable exceptionByNameEmpty = assertThrows(IllegalArgumentException.class, () ->
                new Word(wordAliasIsNormal, wordNameIsEmpty, wordTypeIsNormal));
        Throwable exceptionByNameNull = assertThrows(IllegalArgumentException.class, () ->
                new Word(wordAliasIsNormal, wordNameIsNull, wordTypeIsNormal));
        Throwable exceptionByWordTypeNull = assertThrows(IllegalArgumentException.class, () ->
                new Word(wordAliasIsNormal, wordNameIsNormal, wordTypeIsNull));
        Throwable exceptionByWordNull = assertThrows(IllegalArgumentException.class, () ->
                new Word(wordAliasIsNormal, wordNameIsNormal, wordIsNull));

        //then
        assertEquals("alias 는 null 이거나 빈 값일 수 없습니다.", exceptionByAliasEmpty.getMessage());
        assertEquals("alias 는 null 이거나 빈 값일 수 없습니다.", exceptionByAliasNull.getMessage());
        assertEquals("name 은 null 이거나 빈 값일 수 없습니다.", exceptionByNameEmpty.getMessage());
        assertEquals("name 은 null 이거나 빈 값일 수 없습니다.", exceptionByNameNull.getMessage());
        assertEquals("wordType 은 null 일 수 없습니다.", exceptionByWordTypeNull.getMessage());
        assertEquals("baseWord 는 null 일 수 없습니다.", exceptionByWordNull.getMessage());
    }


    @Test
    @DisplayName("매개변수가 있는 생성자 호출 테스트")
    public void 매개변수가_있는_생성자_정상() throws Exception{
        //given
        String alias = "test alias";
        String name = "test name";
        WordType wordType = WordType.PERFUME;

        //when
        Word wordByWordType = new Word(alias, name, wordType);
        Word wordByBaseWord = new Word(alias, name, wordByWordType);

        //then
        assertNotNull(wordByWordType);
        assertNotNull(wordByBaseWord);

        assertNull("Id는 자동 쟁성됩니다.", wordByWordType.getId());
        assertNull("Id는 자동 쟁성됩니다.", wordByBaseWord.getId());

        assertEquals(alias, wordByWordType.getAlias());
        assertEquals(alias, wordByBaseWord.getAlias());

        assertEquals(name, wordByWordType.getName());
        assertEquals(name, wordByBaseWord.getName());

        assertEquals(wordType, wordByWordType.getWordType());
        assertEquals(wordType, wordByBaseWord.getWordType());

        assertEquals(0, wordByWordType.getWeight());
        assertEquals(0, wordByBaseWord.getWeight());
    }

    @Test
    @DisplayName("dbDate Set 메소드 테스트")
    public void dbDate_초기화() throws Exception{
        //given
        String alias = "test alias";
        String name = "test name";
        WordType wordType = WordType.NOTE;
        String yearMonthDay = "2023-11-17";

        //when
        Word word = new Word(alias, name, wordType);
        word.setDbDate(yearMonthDay);
        DBDate dbDate = new DBDate(yearMonthDay);

        //then
        assertNotNull(word.getDbDate());
        assertEquals(dbDate.getCreatedAt(), word.getDbDate().getCreatedAt());
        assertEquals(dbDate.getUpdatedAt(), word.getDbDate().getUpdatedAt());
    }

    @Test
    @DisplayName("관계형 메소드 Perfume 테스트")
    public void 관계형_메소드_perfume_연결() throws Exception{
        //given
        String alias = "test alias";
        String name = "test name";
        WordType wordType = WordType.PERFUME;

        String perfumeName = "test perfume name";

        //when
        Word word = new Word(alias, name, wordType);
        Perfume perfume = new Perfume(perfumeName);
        word.setEntity(perfume);

        //then
        assertNotNull(word.getPerfume());
        assertNull(word.getBrand());
        assertNull(word.getNote());

        assertEquals(perfume, word.getPerfume());
        assertEquals(perfume.getName(), word.getPerfume().getName());
    }

    @Test
    @DisplayName("관계형 메소드 Brand 테스트")
    public void 관계형_메소드_brand_연결() throws Exception{
        //given
        String alias = "test alias";
        String name = "test name";
        WordType wordType = WordType.BRAND;

        String brandName = "test brand name";

        //when
        Word word = new Word(alias, name, wordType);
        Brand brand = new Brand(brandName);
        word.setEntity(brand);

        //then
        assertNotNull(word.getBrand());
        assertNull(word.getPerfume());
        assertNull(word.getNote());

        assertEquals(brand, word.getBrand());
        assertEquals(brand.getName(), word.getBrand().getName());
    }

    @Test
    @DisplayName("관계형 메소드 Note 테스트")
    public void 관계형_메소드_note_연결() throws Exception{
        //given
        String alias = "test alias";
        String name = "test name";
        WordType wordType = WordType.NOTE;

        String noteName = "test note name";

        //when
        Word word = new Word(alias, name, wordType);
        Note note = new Note(noteName);
        word.setEntity(note);

        //then
        assertNotNull(word.getNote());
        assertNull(word.getPerfume());
        assertNull(word.getBrand());

        assertEquals(note, word.getNote());
        assertEquals(note.getName(), word.getNote().getName());
    }
}
