package com.perfumepedia.PerfumePedia.service;


import com.perfumepedia.PerfumePedia.domain.Brand;
import com.perfumepedia.PerfumePedia.domain.DBDate;
import com.perfumepedia.PerfumePedia.repository.BrandRepository;
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
public class BrandServiceTest {

    @Autowired BrandService brandService;
    @Autowired
    BrandRepository brandRepository;
    
    @Test
    @DisplayName("Save 메소드 호출 테스트")
    public void save_호출() throws Exception{
        //given
        Brand brand = new Brand("test brand name");
        brand.setUrl("www.test.url");
        brand.setDbDate("2023-11-29");

        //when
        Long savedId = brandService.saveBrand(brand);
        
        //then
        assertEquals(brand.getId(), savedId);
    }

    @Test
    @DisplayName("Update 메소드 호출")
    public void update_호출() throws Exception{
        //given
        Brand brand1 = new Brand("same brand name");
        brand1.setUrl("www.first.url");
        brand1.setDbDate("2023-11-29");

        Brand brand2 = new Brand("same brand name");
        brand2.setUrl("www.second.url");
        brand2.setDbDate("2023-11-30");

        //when
        Long saveIdByBrand1 = brandService.saveBrand(brand1);
        Long saveIdByBrand2 = brandService.saveBrand(brand2);

        //then
        assertNotNull(brand1.getId());
        assertNull(brand2.getId());

        assertEquals(saveIdByBrand1, saveIdByBrand2);
        assertEquals("www.second.url", brandRepository.findById(saveIdByBrand1).get().getUrl());
        assertEquals(new DBDate("2023-11-29").getCreatedAt(), brandRepository.findById(saveIdByBrand1).get().getDBDate().getCreatedAt());
        assertEquals(new DBDate("2023-11-30").getUpdatedAt(), brandRepository.findById(saveIdByBrand1).get().getDBDate().getUpdatedAt());
    }
}
