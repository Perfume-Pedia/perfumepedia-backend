package com.perfumepedia.PerfumePedia.service;


import com.perfumepedia.PerfumePedia.domain.*;
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

    private void createPerfume(){
        for(int i=0; i<40; i++){
            Perfume perfume = new Perfume("perfumeName"+i);
            Brand brand = new Brand("brandName"+i);
            Note note = new Note("noteName"+i);
            PerfumeNote perfumeNote = new PerfumeNote(NoteType.TOP);

            // perfume #set
            perfume.setBrand(brand);
            perfume.setUrl("test.perfume.url.com");
            perfume.setPrice(10000);
            perfume.setDbDate("2023-12-08");
            perfume.setImage("image_name", "test/image/path");

            // brand #set
            brand.setUrl("test.brand.url.com");
            brand.setDbDate("2023-12-08");

            // note #set
            note.setDbDate("2023-12-08");

            // perfumeNote #set
            perfumeNote.setNote(note);
            perfumeNote.setPerfume(perfume);
            perfumeNote.setDbDate("2023-12-08");

            brandService.saveBrand(brand);
            perfumeService.savePerfume(perfume);
            noteService.saveNote(note);
            perfumeNoteService.savePerfumeNote(perfumeNote);
            createWord(perfume, perfume.getName(), WordType.PERFUME);
            createWord(brand, brand.getName(), WordType.BRAND);
            createWord(note, note.getName(), WordType.NOTE);
        }
    }


    private void createWord(Perfume perfume, String name, WordType wordType){
        int len = name.length();

        for(int i=0; i<len; i++){
            for(int j=i+1; j<len; j++){
                Word word = new Word(name.substring(i,j), name, wordType);
                word.setEntity(perfume);
                word.setDbDate("2023-12-08");
                wordService.saveWord(word);
            }
        }
    }

    private void createWord(Brand brand, String name, WordType wordType){
        int len = name.length();

        for(int i=0; i<len; i++){
            for(int j=i+1; j<len; j++){
                Word word = new Word(name.substring(i,j), name, wordType);
                word.setEntity(brand);
                word.setDbDate("2023-12-08");
                wordService.saveWord(word);
            }
        }
    }

    private void createWord(Note note, String name, WordType wordType){
        int len = name.length();

        for(int i=0; i<len; i++){
            for(int j=i+1; j<len; j++){
                Word word = new Word(name.substring(i,j), name, wordType);
                word.setEntity(note);
                word.setDbDate("2023-12-08");
                wordService.saveWord(word);
            }
        }
    }

    @Test
    public void 향수이름으로_검색() throws Exception{
        //given
        createPerfume();

        Long lastId = 0l;
        int size = 6;
        String keyword = "perfumeName";

        //when
        ResponseData responseData = searchResultService.searchByKeyword(lastId, size, keyword);
        SearchResultDto searchResultDto = (SearchResultDto) responseData.data();
        List<PerfumeResult> perfumeResults = searchResultDto.getItems();

        //then
        assertNotNull(responseData);
        assertNotNull(searchResultDto);
        assertNotNull(perfumeResults);

//        assertEquals("5", searchResultDto.getLast_item_id());
//        assertEquals(5, perfumeResults.size());
    }


    @Test
    public void 선호향수_검색() throws Exception{
        //given
        createPerfume();

        List<Long> ids = new ArrayList<>();
        ids.add(23l);
        ids.add(31l);
        ids.add(11l);
        ids.add(123l);

        //when
        ResponseData responseData = searchResultService.searchByPerfumeId(ids);
        SearchResultDto searchResultDto = (SearchResultDto) responseData.data();
        List<PerfumeResult> perfumeResults = searchResultDto.getItems();

        //then
        assertNotNull(responseData);
        assertNotNull(searchResultDto);
        assertNotNull(perfumeResults);

        assertEquals("11", searchResultDto.getLast_item_id());
        assertEquals(3, perfumeResults.size());


    }
}
