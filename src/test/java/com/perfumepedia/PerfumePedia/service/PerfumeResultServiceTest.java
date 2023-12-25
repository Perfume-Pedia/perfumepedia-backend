package com.perfumepedia.PerfumePedia.service;


import com.perfumepedia.PerfumePedia.domain.*;
import com.perfumepedia.PerfumePedia.dto.PerfumeResult;
import com.perfumepedia.PerfumePedia.dto.ResponseData;
import com.perfumepedia.PerfumePedia.dto.SearchResultDto;
import com.perfumepedia.PerfumePedia.repository.BrandRepository;
import com.perfumepedia.PerfumePedia.repository.NoteRepository;
import com.perfumepedia.PerfumePedia.repository.PerfumeNoteRepository;
import com.perfumepedia.PerfumePedia.repository.PerfumeRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
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
    @Autowired BrandRepository brandRepository;
    @Autowired PerfumeService perfumeService;
    @Autowired PerfumeRepository perfumeRepository;
    @Autowired NoteService noteService;
    @Autowired NoteRepository noteRepository;
    @Autowired PerfumeNoteService perfumeNoteService;
    @Autowired PerfumeNoteRepository perfumeNoteRepository;
    @Autowired WordService wordService;
    @Autowired SearchResultService searchResultService;
    @Autowired EntityManager em;

    @Before
    public void setUp(){
        for(int i=1; i<=40; i++) {
            Perfume perfume = new Perfume("perfumeName" + i);
            Brand brand = new Brand("brandName");
            Note note = new Note("noteName" + i);
            PerfumeNote perfumeNote = new PerfumeNote(NoteType.TOP);

            // brand #set
            brand.setUrl("test.brand.url.com");
            brand.setDbDate("2023-12-08");
            Long savedBrandId =  brandService.saveBrand(brand);
            brand = brandRepository.findById(savedBrandId).get();

            // note #set
            note.setDbDate("2023-12-08");
            Long savedNoteId =  noteService.saveNote(note);
            note = noteRepository.findById(savedNoteId).get();

            // perfume #set
            perfume.setBrand(brand);
            perfume.setUrl("test.perfume.url.com");
            perfume.setPrice(10000);
            perfume.setDbDate("2023-12-08");
            perfume.setImage("image_name", "test/image/path");
            Long savedPerfumeId =  perfumeService.savePerfume(perfume);
            perfume = perfumeRepository.findById(savedPerfumeId).get();

            // perfumeNote #set
            perfumeNote.setNote(note);
            perfumeNote.setPerfume(perfume);
            perfumeNote.setDbDate("2023-12-08");
            perfumeNoteService.savePerfumeNote(perfumeNote);

            createWord(perfume, perfume.getName(), WordType.PERFUME);
            createWord(brand, brand.getName(), WordType.BRAND);
            createWord(note, note.getName(), WordType.NOTE);
        }
    }


    private void createWord(Perfume perfume, String name, WordType wordType){
        int len = name.length();

        for(int i=0; i<len; i++){
            for(int j=i+1; j<=len; j++){
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
//    @Rollback(value = false)
    public void 향수이름으로_검색() throws Exception{
        //given
        Long lastId = 0l;
        int size = 6;
        String keyword = "perfumeName";

        //when
        ResponseData responseData = searchResultService.searchByKeyword(lastId, size, keyword);
        SearchResultDto searchResultDto = (SearchResultDto) responseData.data();
        List<PerfumeResult> perfumeResults = searchResultDto.getItems();

        // then
        assertNotNull(responseData);
        assertNotNull(searchResultDto);
        assertNotNull(perfumeResults);

        assertEquals(5, perfumeResults.size());
    }

    @Test
//    @Rollback(value = false)
    public void 없는_키워드_검색() throws Exception{
        //given
        Long lastId = 0l;
        int size = 6;
        String keyword = "perfumeName41";

        //when
        ResponseData responseData = searchResultService.searchByKeyword(lastId, size, keyword);
        SearchResultDto searchResultDto = (SearchResultDto) responseData.data();
        List<PerfumeResult> perfumeResults = searchResultDto.getItems();

        // then
        assertNotNull(responseData);
        assertNotNull(searchResultDto);
        assertNotNull(perfumeResults);

        assertEquals(0, perfumeResults.size());

    }


    @Test
//    @Rollback(value = false)
    public void 향수검색_가중치_정렬_확인() throws Exception{
        Long startId = perfumeRepository.findByName("perfumeName1").orElseThrow(
                () -> new IllegalArgumentException("perfumeName1 은 없는 향수 이름 입니다.")
        ).getId();

        //given
        Long lastId = 0l;
        int size = 6;
        String keyword = "perfumeName";
        Long keywordId1 = 21l;
        Long keywordId2 = 30l;
        Long keywordId3 = 11l;
        Long keywordId4 = 33l;
        Long keywordId5 = 13l;

        List<Long> ids = new ArrayList<>();
        ids.add(keywordId1);
        ids.add(keywordId2);
        ids.add(keywordId3);
        ids.add(keywordId4);
        ids.add(keywordId5);

        //when
        for(Long id: ids){
            searchResultService.searchByKeyword(lastId, size, keyword+id);
            searchResultService.searchByKeyword(lastId, size, keyword+id);
            searchResultService.searchByKeyword(lastId, size, keyword+id);
            searchResultService.searchByKeyword(lastId, size, keyword+id);
            searchResultService.searchByKeyword(lastId, size, keyword+id);
            searchResultService.searchByKeyword(lastId, size, keyword+id);
            searchResultService.searchByKeyword(lastId, size, keyword+id);
            searchResultService.searchByKeyword(lastId, size, keyword+id);
        }

        ResponseData responseData = searchResultService.searchByKeyword(lastId, size, keyword);
        SearchResultDto searchResultDto = (SearchResultDto) responseData.data();
        List<PerfumeResult> perfumeResults = searchResultDto.getItems();

        // then
        assertNotNull(responseData);
        assertNotNull(searchResultDto);
        assertNotNull(perfumeResults);

        assertEquals(5, perfumeResults.size());

        for(Long id: ids){
            Long finalId = id + startId -1;

            boolean keywordId1IsExists = perfumeResults.stream()
                    .anyMatch(perfumeResult -> finalId.toString().equals(perfumeResult.getUuid()));
            assertTrue("The UUID '" + finalId + "' should exist in the list", keywordId1IsExists);

        }


    }


    @Test
//    @Rollback(value = false)
    public void 선호향수_검색() throws Exception{
        Long startId = perfumeRepository.findByName("perfumeName1").orElseThrow(
                () -> new IllegalArgumentException("perfumeName1 은 없는 향수 이름 입니다.")
        ).getId();

        //given
        List<Long> ids = new ArrayList<>();
        for(Long i=startId; i<startId+40; i++){
            ids.add(i);
        }

        //when
        ResponseData responseData = searchResultService.searchByPerfumeId(ids);
        SearchResultDto searchResultDto = (SearchResultDto) responseData.data();
        List<PerfumeResult> perfumeResults = searchResultDto.getItems();

        //then
        assertNotNull(responseData);
        assertNotNull(searchResultDto);
        assertNotNull(perfumeResults);

        assertEquals(40, perfumeResults.size());
        assertEquals(startId+39+"", searchResultDto.getLast_item_id());
    }
}
