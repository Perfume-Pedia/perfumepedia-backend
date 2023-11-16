package com.perfumepedia.PerfumePedia.Domain;


import com.perfumepedia.PerfumePedia.domain.Brand;
import com.perfumepedia.PerfumePedia.domain.Image;
import com.perfumepedia.PerfumePedia.domain.Perfume;
import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@RequiredArgsConstructor
public class PerfumeDomainTest {

    @Test
    public void 기본생성자_제한() throws Exception{
        // When & Then
        assertThrows(IllegalAccessException.class, () -> {
            Perfume.class.getDeclaredConstructor().newInstance();
        });
    }
    
    @Test
    public void 매개변수가_있는_생성자_정상() throws Exception{
        //given
        String name = "향수이름";
                
        //when
        Perfume perfume = new Perfume(name);
        
        //then
        assertNotNull(perfume);
        assertEquals(name, perfume.getName());
    }

    @Test
    public void ID_자동생성_확인() throws Exception{
        //given
        String name = "향수이름";

        //when
        Perfume perfume = new Perfume(name);

        //then
        assertNull("id는 자동 생성됩니다.", perfume.getId());
    }
    
    @Test
    public void Price_Url_초기화() throws Exception{
        //given
        String name = "향수이름";
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
    public void Image_DBDate_Discontinue_초기화() throws Exception{
        //given
        Image image = new Image("image name", "image path");

        //when


        //then

    }

    @Test
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
