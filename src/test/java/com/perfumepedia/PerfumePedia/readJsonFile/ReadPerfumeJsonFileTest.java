package com.perfumepedia.PerfumePedia.readJsonFile;

import com.perfumepedia.PerfumePedia.dataForm.CollectionForm;
import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import java.util.Arrays;
import java.util.List;

import static java.util.Objects.isNull;
import static org.junit.Assert.*;
import static org.junit.Assert.assertNotEquals;

//@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@RequiredArgsConstructor
public class ReadPerfumeJsonFileTest {

    String jsonFileName = "static/json/perfume_json.json";

    ReadPerfumeJsonFile readPerfumeJsonFile = new ReadPerfumeJsonFile();

    @Test
    public void 브랜드_이름_확인() throws Exception {
        List<CollectionForm> perfumes = readPerfumeJsonFile.readJsonFile(jsonFileName);

        String firstBrandName = "르라보";
        // 첫번째 Json 데이터
        CollectionForm firstPerfume = perfumes.get(0);
        CollectionForm secondPerfume = perfumes.get(1);

        assertEquals(firstBrandName, firstPerfume.getBrand());
        assertEquals(firstBrandName, secondPerfume.getBrand());
    }


    @Test
    public void 향수_이름_확인() {
        List<CollectionForm> perfumes = readPerfumeJsonFile.readJsonFile(jsonFileName);

        String firstPerfumeName = "떼 마차 26";
        CollectionForm firstPerfume = perfumes.get(0);

        assertEquals(firstPerfumeName, firstPerfume.getName());

    }


    @Test
    public void 탑_노트_확인() {

        List<CollectionForm> perfumes = readPerfumeJsonFile.readJsonFile(jsonFileName);

        CollectionForm firstPerfume = perfumes.get(0);
        List<String> firstTopNote = Arrays.asList("무화과");

        assertEquals(firstTopNote, firstPerfume.getTop_nt());

    }

    @Test
    public void 이미지_확인() {

        List<CollectionForm> perfumes = readPerfumeJsonFile.readJsonFile(jsonFileName);

        CollectionForm firstPerfume = perfumes.get(0);
        String firstImage = null;

        assertEquals(firstImage, firstPerfume.getImage());

    }

    @Test
    public void 날짜_확인() {

        List<CollectionForm> perfumes = readPerfumeJsonFile.readJsonFile(jsonFileName);

        CollectionForm firstPerfume = perfumes.get(0);
        String firstdate = "2023-11-16";

        assertEquals(firstdate, firstPerfume.getUpdate_at());

    }

//    @Test
//    public void 수집_향수_개수_확인() {
//        List<CollectionForm> perfumes = readPerfumeJsonFile.readJsonFile(jsonFileName);
//        long colletPerfume = 305;
//        long perfumesSize = perfumes.size();
//        assertEquals(colletPerfume, perfumesSize);
//    }

    // 읽어온 값이 null 이거나 빈 문자열인지 확인
    @Test
    public void 유효성_확인_1() {
        List<CollectionForm> perfumes = readPerfumeJsonFile.readJsonFile(jsonFileName);

        for (int i = 0; i < perfumes.size(); i++) {
            CollectionForm collect = perfumes.get(i);

            // brand가  null 또는 빈 문자열인지 확인
            assertNotNull(collect.getBrand());
            assertNotEquals(" ", collect.getBrand().trim());

            // perfume(name) 이 null 또는 빈 문자열인지 확인
            assertNotNull(collect.getName());
            assertNotEquals(" ", collect.getName().trim());

            // brand_url, perfume_url, price, image 는 현재 null값 임

        }
    }

    // 노트의 각 리스트가 빈 문자열, 공백 문자열을 포함하고 있는지 검사
    @Test
    public void 유효성_확인_2() {
        List<CollectionForm> perfumes = readPerfumeJsonFile.readJsonFile(jsonFileName);

        for (int i = 0; i < perfumes.size(); i++) {
            CollectionForm collect = perfumes.get(i);

            for (String top_nt : collect.getTop_nt()) {
                if (top_nt != null) {
                    assertNotEquals(" ", top_nt.trim());
                    assertNotEquals("", top_nt.trim());
                }
            }

            for (String mid_nt : collect.getMid_nt()) {
                if(mid_nt != null) {
                    assertNotEquals(" ", mid_nt.trim());
                    assertNotEquals("", mid_nt.trim());
                }
            }

            for (String base_nt : collect.getBase_nt()) {
                if(base_nt != null) {
                    assertNotEquals(" ", base_nt.trim());
                    assertNotEquals("", base_nt.trim());
                }
            }

            for (String single_nt : collect.getSingle_nt()) {
                if(single_nt != null) {
                    assertNotEquals(" ", single_nt.trim());
                    assertNotEquals("", single_nt.trim());
                }
            }
        }
    }

    // 각 노트의 존재유무 확인
    @Test
    public void 유효성_확인_3() {
        List<CollectionForm> perfumes = readPerfumeJsonFile.readJsonFile(jsonFileName);

        for (int i = 0; i < perfumes.size(); i++) {
            CollectionForm collect = perfumes.get(i);

            if (!isNull(collect.getTop_nt().get(0)) || !isNull(collect.getMid_nt().get(0)) || !isNull(collect.getBase_nt().get(0))) {
                assertTrue("싱글 노트 비어있어야함. \n" +
                        "향수 인덱스:  "+ i, isNull(collect.getSingle_nt().get(0)));

            } else {
                assertFalse("싱글 노트 값 있어야함. \n" +
                        "향수 인덱스: "+ i, isNull(collect.getSingle_nt().get(0)));
            }



        }
    }





}
