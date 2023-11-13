package com.perfumepedia.PerfumePedia.Domain;

import com.perfumepedia.PerfumePedia.domain.Brand;
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
public class BrandDomainTest {

    @Test
    public void 기본생성자_오류() throws Exception{
        // When & Then
        assertThrows(IllegalAccessException.class, () -> {
            Brand.class.getDeclaredConstructor().newInstance();
        });
    }

    @Test
    public void 매개변수가_있는_생성자_정상() throws Exception{
        //given
        String brandName = "딥디크";

        //when
        Brand brand = new Brand(brandName);

        //then
        assertNotNull(brand);
        assertNull("Id는 자동 쟁성됩니다.",brand.getId());
        assertEquals(brandName, brand.getName());
        assertNull(brand.getUrl());
        assertNotNull(brand.getDBDate());
        assertNotNull(brand.getDBDate().getCreatedAt());
        assertNotNull(brand.getDBDate().getUpdatedAt());
    }

    @Test
    public void URL_초기화() throws Exception{
        //given
        String brandName = "딥디크";
        String brandUrl = "testUrl";

        //when
        Brand brand = new Brand(brandName);
        brand.setUrl(brandUrl);

        //then
        assertNotNull(brand.getUrl());
        assertEquals(brand.getUrl(), brandUrl);

    }
}
