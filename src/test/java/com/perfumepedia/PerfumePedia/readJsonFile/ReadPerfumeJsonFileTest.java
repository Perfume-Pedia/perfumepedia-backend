package com.perfumepedia.PerfumePedia.readJsonFile;

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
public class ReadPerfumeJsonFileTest {

    String jsonFileName = "static/json/perfume_json.json";

    ReadPerfumeJsonFile readPerfumeJsonFile = new ReadPerfumeJsonFile();

    @Test
    public void 브렌드_이름_확인() throws Exception {
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

    @Test
    public void 수집_향수_개수_확인() {
        List<CollectionForm> perfumes = readPerfumeJsonFile.readJsonFile(jsonFileName);
        long colletPerfume = 306;
        long perfumesSize = perfumes.size();
        assertEquals(colletPerfume, perfumesSize);
    }

    @Test
    public void 유효성_확인() {
        List<CollectionForm> perfumes = readPerfumeJsonFile.readJsonFile(jsonFileName);

        for(int i = 0; i < perfumes.size(); i++) {
            CollectionForm collect = perfumes.get(i);

            // brand가  null 또는 빈 문자열인지 확인
            assertNotNull(collect.getBrand());
            assertNotEquals(" ", collect.getBrand().trim());


            // brand_url, perfume_url, price, image 는 현재 null값 임


            // perfume(name) 이 null 또는 빈 문자열인지 확인
            assertNotNull(collect.getName());
            assertNotEquals(" ", collect.getName().trim());



//            // image 이 null 또는 빈 문자열인지 확인
//            assertNotNull(collect.getImage());
//            assertNotEquals(" ", collect.getImage().trim());




        }
    }
}
