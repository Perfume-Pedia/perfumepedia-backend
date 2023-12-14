package com.perfumepedia.PerfumePedia.insertService;

import com.perfumepedia.PerfumePedia.domain.Brand;
import lombok.RequiredArgsConstructor;
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

//@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@RequiredArgsConstructor
public class BrandInsertServiceTest {

    @Test
    public void CollectionForm의_데이터를_Brand_객체에_set() {
        //given
        Brand brand1 = new Brand("르라보");
        brand1.setUrl(null);
        brand1.setDbDate("2023-11-16");
        //when

        //then

    }



}
