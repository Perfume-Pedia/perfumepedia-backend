package com.perfumepedia.PerfumePedia.Domain;

import com.perfumepedia.PerfumePedia.domain.Brand;
import com.perfumepedia.PerfumePedia.domain.DBDate;
import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("Brand 객체: 기본 생성자 호출 오류 테스트")
    public void 기본생성자_오류() throws Exception{
        // When & Then
        assertThrows(IllegalAccessException.class, () -> {
            Brand.class.getDeclaredConstructor().newInstance();
        });
    }

    @Test
    @DisplayName("Brand 객체: 매개변수가 있는 생성자 호출 정살 테스트")
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
        assertNull(brand.getDBDate());
    }

    @Test
    @DisplayName("Brand 객체: 매개변수로 빈값을 전달했을 경우 테스트")
    public void 매개변수가_빈값인_생성자_오류() throws Exception{
        //given
        String brandNameIsEmpty = "";
        String brandNameIsNull = null;

        //when &then
        assertThrows(IllegalArgumentException.class, () -> {
            new Brand(brandNameIsEmpty);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Brand(brandNameIsNull);
        });
    }

    @Test
    @DisplayName("Brand 객체: url, dbDate set 메소드 테스트")
    public void url_dbDate_초기화() throws Exception{
        //given
        String brandName = "딥디크";
        String brandUrl = "testUrl";
        String brandDbDate = "2023-11-16";

        //when
        Brand brand = new Brand(brandName);
        brand.setUrl(brandUrl);
        brand.setDbDate(brandDbDate);

        DBDate dbDate = new DBDate(brandDbDate);

        //then
        assertNotNull(brand.getUrl());
        assertEquals(brand.getUrl(), brandUrl);

        assertNotNull(brand.getDBDate());
        assertEquals(brand.getDBDate().getCreatedAt(), dbDate.getCreatedAt());
        assertEquals(brand.getDBDate().getCreatedAt(), dbDate.getUpdatedAt());
    }

}
