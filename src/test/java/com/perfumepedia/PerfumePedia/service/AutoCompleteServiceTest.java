package com.perfumepedia.PerfumePedia.service;


import com.perfumepedia.PerfumePedia.domain.*;
import com.perfumepedia.PerfumePedia.dto.AutoCompleteWord;
import com.perfumepedia.PerfumePedia.dto.AutoCompleteWordDto;
import com.perfumepedia.PerfumePedia.dto.ResponseData;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@RequiredArgsConstructor
public class AutoCompleteServiceTest {

    @Autowired AutoCompleteService autoCompleteService;
    @Autowired WordService wordService;
    @Autowired PerfumeService perfumeService;
    @Autowired BrandService brandService;
    @Autowired NoteService noteService;
    @Autowired EntityManager em;

    String keyword = "test word alias";
    String wordAlias = "test word alias";
    String perfumeName = "test perfume name";
    String brandName = "test brand name";
    String noteName = "test note name";
    String date = "2023-12-06";
    WordType wordTypeIsPerfume = WordType.PERFUME;
    WordType wordTypeIsBrand = WordType.BRAND;
    WordType wordTypeIsNote = WordType.NOTE;

    @Test
    public void 검색어_자동완성() throws Exception{
        //given
        Brand brand = new Brand(brandName);
        Perfume perfume = new Perfume(perfumeName);
        perfume.setBrand(brand);
        Word wordByPerfume = new Word(wordAlias, perfumeName, wordTypeIsPerfume);
        wordByPerfume.setEntity(perfume);
        Word wordByBrand = new Word(wordAlias, brandName, wordTypeIsBrand);
        wordByBrand.setEntity(brand);
                
        //when
        Long savedIdByBrand = brandService.saveBrand(brand);
        Long savedIdByPerfume = perfumeService.savePerfume(perfume);
        Long savedIdByPerfumeWord = wordService.saveWord(wordByPerfume);
        Long savedIdByBrandWord = wordService.saveWord(wordByBrand);

        ResponseData responseData = autoCompleteService.findKeywords(keyword);
        AutoCompleteWordDto autoCompleteWordDto = (AutoCompleteWordDto) responseData.data();
        List<AutoCompleteWord> autoCompleteWords = autoCompleteWordDto.getItems();

        //then
        em.flush();

        boolean perfumeNameIsExists = autoCompleteWords.stream()
                .anyMatch(autoCompleteWord -> perfumeName.equals(autoCompleteWord.getKeyword()));
        assertTrue("The keyword '" + perfumeName + "' should exist in the list", perfumeNameIsExists);

        boolean brandNameIsExists = autoCompleteWords.stream()
                .anyMatch(autoCompleteWord -> brandName.equals(autoCompleteWord.getKeyword()));
        assertTrue("The keyword '" + brandName + "' should exist in the list", brandNameIsExists);
    }
    
    
    @Test
    public void 가중치_정렬_확인() throws Exception{
        //given
        Note note = new Note(noteName);
        Brand brand = new Brand(brandName);
        Perfume perfume = new Perfume(perfumeName);
        perfume.setBrand(brand);

        Word wordByPerfume = new Word(wordAlias, perfumeName, wordTypeIsPerfume);
        wordByPerfume.setEntity(perfume);
        wordByPerfume.setDbDate(date);

        Word wordByBrand = new Word(wordAlias, brandName, wordTypeIsBrand);
        wordByBrand.setEntity(brand);
        wordByBrand.setDbDate(date);

        Word wordByNote = new Word(wordAlias, noteName, wordTypeIsNote);
        wordByNote.setEntity(note);
        wordByNote.setDbDate(date);
                
        //when
        Long savedIdByBrand = brandService.saveBrand(brand);
        Long savedIdByPerfume = perfumeService.savePerfume(perfume);
        Long savedIdByNote = noteService.saveNote(note);
        Long savedIdByPerfumeWord = wordService.saveWord(wordByPerfume);
        Long savedIdByBrandWord = wordService.saveWord(wordByBrand);
        Long savedIdByNoteWord = wordService.saveWord(wordByNote);

        wordService.increaseWeight(wordByPerfume);
        wordService.increaseWeight(wordByPerfume);
        wordService.increaseWeight(wordByPerfume);

        wordService.increaseWeight(wordByBrand);
        wordService.increaseWeight(wordByBrand);

        wordService.increaseWeight(wordByNote);


        ResponseData responseData = autoCompleteService.findKeywords(keyword);
        AutoCompleteWordDto autoCompleteWordDto = (AutoCompleteWordDto) responseData.data();
        List<AutoCompleteWord> autoCompleteWords = autoCompleteWordDto.getItems();

        //then
        em.flush();
        assertEquals(perfumeName, autoCompleteWords.get(0).getKeyword());
        assertEquals(brandName, autoCompleteWords.get(1).getKeyword());
        assertEquals(noteName, autoCompleteWords.get(2).getKeyword());
    }
}
