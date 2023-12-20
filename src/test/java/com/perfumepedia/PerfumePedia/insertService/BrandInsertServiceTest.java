package com.perfumepedia.PerfumePedia.insertService;

import com.perfumepedia.PerfumePedia.domain.Brand;
import com.perfumepedia.PerfumePedia.domain.Word;
import com.perfumepedia.PerfumePedia.domain.WordType;
import com.perfumepedia.PerfumePedia.readJsonFile.ReadPerfumeJsonFile;
import com.perfumepedia.PerfumePedia.repository.BrandRepository;
import com.perfumepedia.PerfumePedia.service.BrandService;
import com.perfumepedia.PerfumePedia.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.perfumepedia.PerfumePedia.dataForm.CollectionForm;
import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

//@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@RequiredArgsConstructor
public class BrandInsertServiceTest {
    @Autowired
    BrandInsertService brandInsertService;

    String jsonFileName = "static/json/perfume_json.json";
    ReadPerfumeJsonFile readPerfumeJsonFile = new ReadPerfumeJsonFile();
    List<CollectionForm> perfumes = readPerfumeJsonFile.readJsonFile(jsonFileName);

    BrandService BrandService = mock(BrandService.class);
    BrandRepository BrandRepository = mock(BrandRepository.class);
    WordService WordService = mock(WordService.class);

    String brandName = "르라보";
    String alias = "르";
    String  data = "2023-11-16";
    WordType wordType = WordType.BRAND;
    @Test
    public void CollectionForm의_데이터를_Brand_객체에_set() {
        //given
        Brand brand1 = new Brand(brandName);
        brand1.setUrl(null);
        brand1.setDbDate(data);

        //when
        CollectionForm testData = perfumes.get(0);
        // brandInsertService 객체를 생성하기 위해서는 BrandService등 각각의 필요한 메서드 또한 모두 주입해줘야함
        // BrandInsertService brandInsertService = new BrandInsertService(new BrandService(), new BrandRepository(), new WordService());
        // BrandInsertService 객체 생성 시 Mock 객체 주입
        BrandInsertService brandInsertService = new BrandInsertService(BrandService, BrandRepository, WordService);
        Brand brand2 = brandInsertService.collectDataToBrand(testData);

        //then
        assertEquals(brand1.getName(), brand2.getName());
        assertEquals(brand1.getUrl(), brand2.getUrl());
        assertEquals(brand1.getDBDate().getCreatedAt(), brand2.getDBDate().getCreatedAt());

    }


    public void 브렌드_저장_워드_저장_브렌드이용() {

        // given
        Brand brand1 = new Brand(brandName);
        brand1.setUrl(null);
        brand1.setDbDate(data);

        Word word1 = new Word(alias, brandName, wordType );
        word1.setEntity(brand1);
        word1.setDbDate(data);


        // when
        CollectionForm testData = perfumes.get(0);
        BrandInsertService brandInsertService = new BrandInsertService(BrandService, BrandRepository, WordService);
        Brand brand2 = brandInsertService.collectDataToBrand(testData);
        brandInsertService.insertBrandAndWordData(testData);




        // then
    }






    }
