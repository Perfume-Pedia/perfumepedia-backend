package com.perfumepedia.PerfumePedia.service;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@RequiredArgsConstructor
public class WordServiceTest {

    @Autowired WordService wordService;
    @Autowired BrandService brandService;
    @Autowired PerfumeService perfumeService;
    @Autowired NoteService noteService;
    @Autowired EntityManager em;

}
