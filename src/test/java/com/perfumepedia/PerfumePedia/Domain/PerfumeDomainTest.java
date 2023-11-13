package com.perfumepedia.PerfumePedia.Domain;


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
        assertNull(perfume.getId());
    }
}
