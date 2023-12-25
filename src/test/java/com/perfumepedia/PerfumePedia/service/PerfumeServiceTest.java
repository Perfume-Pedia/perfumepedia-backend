package com.perfumepedia.PerfumePedia.service;

import com.perfumepedia.PerfumePedia.domain.Brand;
import com.perfumepedia.PerfumePedia.domain.Discontinue;
import com.perfumepedia.PerfumePedia.domain.Perfume;
import com.perfumepedia.PerfumePedia.repository.PerfumeRepository;
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
public class PerfumeServiceTest {

    @Autowired PerfumeService perfumeService;
    @Autowired PerfumeRepository perfumeRepository;
    @Autowired BrandService brandService;
    @Autowired EntityManager em;


    String perfumeName = "test perfume name";
    String brandName = "test brand name";
    String perfumeUrl = "www.test.url";
    int price = 200000;
    String imageName = "test image name";
    String imagePath = "path/image";
    String date = "2023-11-30";


    @Test
    public void 향수_추가() throws Exception{
        //given
        Perfume perfume = new Perfume(perfumeName);
        Brand brand = new Brand(brandName);

        perfume.setBrand(brand);
        perfume.setUrl(perfumeUrl);
        perfume.setPrice(price);
        perfume.setImage(imageName, imagePath);
        perfume.setDbDate(date);

        //when
        Long savedBrandId = brandService.saveBrand(brand);
        Long savedId = perfumeService.savePerfume(perfume);

        //then
        em.flush();

        assertNotNull(perfume.getId());
        assertEquals(savedId, perfume.getId());

        assertNotNull(brand.getId());
        assertEquals(savedBrandId, perfume.getBrand().getId());

        assertEquals(perfumeUrl, perfume.getUrl());
        assertEquals(perfumeName, perfume.getName());
        assertEquals(price, perfume.getPrice());
        assertEquals(imageName, perfume.getImage().getName());
        assertEquals(imagePath, perfume.getImage().getPath());
        assertEquals(date, perfume.getDbDate().getCreatedAt().toString());
        assertEquals(date, perfume.getDbDate().getUpdatedAt().toString());
    }

    @Test
    public void 향수_업데이트() throws Exception{
        //given
        Perfume perfume1 = new Perfume(perfumeName);
        Perfume perfume2 = new Perfume(perfumeName);
        Brand brand = new Brand(brandName);

        perfume1.setBrand(brand);
        perfume1.setImage(imageName, imagePath);
        perfume1.setPrice(price);
        perfume1.setUrl(perfumeUrl);
        perfume1.setDbDate(date);

        perfume2.setBrand(brand);
        perfume2.setImage(imageName+"change", imagePath+"/change");
        perfume2.setPrice(price+1);
        perfume2.setUrl(perfumeUrl+"/change");
        perfume2.setDbDate("2023-12-01");
        perfume2.setDiscontinue();

        //when
        Long savedBrandId = brandService.saveBrand(brand);
        Long savedPerfume1Id = perfumeService.savePerfume(perfume1);
        Long savedPerfume2Id = perfumeService.savePerfume(perfume2);

        //then
        em.flush();
        assertNotNull(perfume1.getId());
        assertNull(perfume2.getId());
        assertEquals(savedPerfume1Id, savedPerfume2Id);

        assertEquals(imageName, perfume1.getImage().getName());
        assertNotEquals(perfume1.getImage().getName(), perfume2.getImage().getName());

        assertEquals(imagePath, perfume1.getImage().getPath());
        assertNotEquals(perfume1.getImage().getPath(), perfume2.getImage().getPath());

        assertNotEquals(price, perfume1.getPrice());
        assertEquals(price+1, perfume1.getPrice());
        assertEquals(perfume1.getPrice(), perfume2.getPrice());

        assertNotEquals(perfumeUrl, perfume1.getUrl());
        assertEquals(perfumeUrl+"/change", perfume1.getUrl());
        assertEquals(perfume1.getUrl(), perfume2.getUrl());

        assertNotEquals(date, perfume1.getDbDate().getUpdatedAt().toString());
        assertEquals(date, perfume1.getDbDate().getCreatedAt().toString());
        assertEquals("2023-12-01", perfume1.getDbDate().getUpdatedAt().toString());

        assertNotEquals(Discontinue.CONTINUE, perfume1.getDiscontinue());
        assertEquals(Discontinue.DISCONTINUE, perfume1.getDiscontinue());
        assertEquals(perfume1.getDiscontinue(), perfume2.getDiscontinue());
    }
}
