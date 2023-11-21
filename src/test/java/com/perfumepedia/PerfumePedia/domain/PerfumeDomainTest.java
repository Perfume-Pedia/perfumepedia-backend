package com.perfumepedia.PerfumePedia.domain;


import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@RequiredArgsConstructor
public class PerfumeDomainTest {

    @Test
    @DisplayName("매개 변수로 빈 값을 전달했을 경우 테스트")
    public void 기본생성자_제한() throws Exception{
        //given
        String perfumeNameIdEmpty = "";
        String perfumeNameIsNull = null;

        // When
        Throwable exceptionByEmpty = assertThrows(IllegalArgumentException.class, () -> new Perfume(perfumeNameIdEmpty));
        Throwable exceptionByNull = assertThrows(IllegalArgumentException.class, () -> new Perfume(perfumeNameIsNull));

        // Then
        assertEquals("name 은 null 이거나 빈 값일 수 없습니다.", exceptionByEmpty.getMessage());
        assertEquals("name 은 null 이거나 빈 값일 수 없습니다.", exceptionByNull.getMessage());
    }

    @Test
    @DisplayName("매개변수가 있는 생성자 호출 정상 테스트")
    public void 매개변수가_있는_생성자_정상() throws Exception{
        //given
        String name = "향수이름";
                
        //when
        Perfume perfume = new Perfume(name);
        
        //then
        assertNotNull(perfume);
        assertEquals(name, perfume.getName());
        assertNull("id는 자동 생성됩니다.", perfume.getId());
    }
    
    @Test
    @DisplayName("price, url Set 메소드 테스트")
    public void Price_Url_초기화() throws Exception{
        //given
        String name = "perfume name";
        int price = 123456;
        String url = "test.url.com";

        //when
        Perfume perfume = new Perfume(name);
        perfume.setPrice(price);
        perfume.setUrl(url);

        //then
        assertNotNull(perfume.getPrice());
        assertNotNull(perfume.getUrl());

        assertEquals(price, perfume.getPrice());
        assertEquals(url, perfume.getUrl());
    }

    @Test
    @DisplayName("image, dbDate, discontinue Set 메소드 테스트")
    public void Image_DBDate_Discontinue_초기화() throws Exception{
        //given
        String perfumeName = "perfume name";
        String imageName = "image name";
        String imagePath = "test.image.path";
        String perfumeDbDate = "2023-11-16";

        //when
        Perfume perfume = new Perfume(perfumeName);
        perfume.setImage(imageName, imagePath);
        perfume.setDbDate(perfumeDbDate);

        //then
        assertNotNull(perfume.getImage());
        assertEquals(perfume.getImage().getName(), imageName);
        assertEquals(perfume.getImage().getPath(), imagePath);

        assertNotNull(perfume.getDbDate());
        assertEquals(perfume.getDbDate().getCreatedAt(), Date.valueOf(perfumeDbDate));
        assertEquals(perfume.getDbDate().getUpdatedAt(), Date.valueOf(perfumeDbDate));

        assertNotNull(perfume.getDiscontinue());
        assertEquals(perfume.getDiscontinue(), Discontinue.CONTINUE);
    }

    @Test
    @DisplayName("Brand 관계형 메소드 테스트")
    public void Brand_관계_설정() throws Exception{
        //given
        Brand brand = new Brand("test brand name");
        Perfume perfume = new Perfume("test perfume name");

        //when
        perfume.setBrand(brand);

        //then
        assertNotNull(perfume.getBrand());
        assertEquals(perfume.getBrand(), brand);
    }

}
