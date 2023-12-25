package com.perfumepedia.PerfumePedia.service;

import com.perfumepedia.PerfumePedia.domain.Brand;
import com.perfumepedia.PerfumePedia.domain.Perfume;
import com.perfumepedia.PerfumePedia.domain.Word;
import com.perfumepedia.PerfumePedia.domain.WordType;
import com.perfumepedia.PerfumePedia.repository.WordRepository;
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
public class WordServiceTest {

    @Autowired WordService wordService;
    @Autowired BrandService brandService;
    @Autowired PerfumeService perfumeService;
    @Autowired NoteService noteService;
    @Autowired WordRepository wordRepository;
    @Autowired EntityManager em;

    String wordAlias = "test word alias";
    String wordName = "test word name";
    String perfumeName = "test perfume name";
    String brandName = "test brand name";
    String date = "2023-12-06";
    WordType wordType = WordType.PERFUME;

    @Test
    public void 워드_추가_타입이용() throws Exception{
        //given
        Brand brand = new Brand(brandName);
        Perfume perfume = new Perfume(perfumeName);
        perfume.setBrand(brand);
        Word word = new Word(wordAlias, wordName, wordType);
        word.setEntity(perfume);

        //when
        Long savedIdByBrand = brandService.saveBrand(brand);
        Long savedIdByPerfume = perfumeService.savePerfume(perfume);
        Long savedIdByWord = wordService.saveWord(word);

        //then
        em.flush();
        assertEquals(savedIdByWord, word.getId());
        assertEquals(savedIdByWord, wordRepository.findByAliasAndName(wordAlias, wordName).get().getId());
        assertEquals(savedIdByPerfume, word.getTypeId());
        assertEquals(savedIdByPerfume, wordRepository.findByTypeAndTypeId(savedIdByPerfume,wordType).get(0).getTypeId());
    }
    
    @Test
    public void 워드_추가_워드이용() throws Exception{
        //given
        Brand brand = new Brand(brandName);
        Perfume perfume = new Perfume(perfumeName);
        perfume.setBrand(brand);
        Word baseWord = new Word(wordAlias, wordName, wordType);
        baseWord.setEntity(perfume);

        Word word1 = new Word("alias1", wordName, baseWord);
        Word word2 = new Word("alias2", wordName, baseWord);
        Word word3 = new Word("alias3", wordName, baseWord);
        Word word4 = new Word("alias4", wordName, baseWord);

        word1.setEntity(perfume);
        word2.setEntity(perfume);
        word3.setEntity(perfume);
        word4.setEntity(perfume);

        //when
        Long savedIdByBrand = brandService.saveBrand(brand);
        Long savedIdByPerfume = perfumeService.savePerfume(perfume);
        Long savedIdByBaseWord = wordService.saveWord(baseWord);
        Long savedIdByWord1 = wordService.saveWord(word1);
        Long savedIdByWord2 = wordService.saveWord(word2);
        Long savedIdByWord3 = wordService.saveWord(word3);
        Long savedIdByWord4 = wordService.saveWord(word4);

        //then
        em.flush();
        assertEquals(savedIdByPerfume, word1.getTypeId());
        assertEquals(savedIdByPerfume, word2.getTypeId());
        assertEquals(savedIdByPerfume, word3.getTypeId());
        assertEquals(savedIdByPerfume, word4.getTypeId());

        assertEquals(wordType, baseWord.getWordType());
        assertEquals(wordType, word1.getWordType());
        assertEquals(wordType, word2.getWordType());
        assertEquals(wordType, word3.getWordType());
        assertEquals(wordType, word4.getWordType());
    }

    @Test
    public void 워드_가중치_증가() throws Exception{
        //given
        Brand brand = new Brand(brandName);
        Perfume perfume = new Perfume(perfumeName);
        perfume.setBrand(brand);
        Word word = new Word(wordAlias, wordName, wordType);
        word.setEntity(perfume);
        word.setDbDate(date);

        //when
        Long savedIdByBrand = brandService.saveBrand(brand);
        Long savedIdByPerfume = perfumeService.savePerfume(perfume);
        Long savedIdByWord = wordService.saveWord(word);

        wordService.increaseWeight(word);
        wordService.increaseWeight(word);
        wordService.increaseWeight(word);


        //then
        em.flush();
        assertEquals(3, word.getWeight());

    }
}
