//package com.perfumepedia.PerfumePedia.ReadJsonFileTest;
//
//import com.perfumepedia.PerfumePedia.datainsert.CollectionForm;
//import com.perfumepedia.PerfumePedia.datainsert.ReadJsonFile;
//import org.junit.jupiter.api.Test;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class ReadJsonFileTest {
//
//    String jsonFileName = "static/json/perfume_json.json";
//    ReadJsonFile readJsonFile = new ReadJsonFile();
//
//    @Test
//    void ReadJson_브렌드_이름_확인() {
//
//        List<CollectionForm> perfumes = readJsonFile.readJsonFile(jsonFileName);
//
//        String firstBrandName = "르라보";
//        // 첫번째 Json 데이터
//        CollectionForm firstPerfume = perfumes.get(0);
//
//        assertEquals(firstBrandName, firstPerfume.getBrand());
//    }
//
//
//    @Test
//    void ReadJson_향수_이름_확인() {
//        List<CollectionForm> perfumes = readJsonFile.readJsonFile(jsonFileName);
//
//        String firstPerfumeName = "떼 마차 26";
//        CollectionForm firstPerfume = perfumes.get(0);
////        System.out.println(firstPerfumeName);
////        System.out.println(firstPerfume.getName());
//
//        assertEquals(firstPerfumeName, firstPerfume.getName());
//
//    }
//
//
//    @Test
//    void ReadJson_탑_노트_확인() {
//
//        List<CollectionForm> perfumes = readJsonFile.readJsonFile(jsonFileName);
//
//        CollectionForm firstPerfume = perfumes.get(0);
//        List<String> firstTopNote = Arrays.asList("무화과");
//
//        System.out.println(firstTopNote);
//        System.out.println(firstPerfume.getTop_nt());
//
//        assertEquals(firstTopNote, firstPerfume.getTop_nt());
//
//    }
//
//    @Test
//    void ReadJson_이미지_확인() {
//
//        List<CollectionForm> perfumes = readJsonFile.readJsonFile(jsonFileName);
//
//        CollectionForm firstPerfume = perfumes.get(0);
//        String firstImage = null;
//
//        System.out.println(firstImage);
//        System.out.println(firstPerfume.getImage());
//
//        assertEquals(firstImage, firstPerfume.getImage());
//
//    }
//
//    @Test
//    void ReadJson_날짜_확인() {
//
//        List<CollectionForm> perfumes = readJsonFile.readJsonFile(jsonFileName);
//
//        CollectionForm firstPerfume = perfumes.get(0);
//        String firstdate = "2023-11-16";
//
//        System.out.println(firstdate);//package com.perfumepedia.PerfumePedia.ReadJsonFileTest;
////
////import com.perfumepedia.PerfumePedia.datainsert.CollectionForm;
////import com.perfumepedia.PerfumePedia.datainsert.ReadJsonFile;
////import org.junit.jupiter.api.Test;
////
////import java.util.Arrays;
////import java.util.List;
////
////import static org.junit.jupiter.api.Assertions.*;
////
////public class ReadJsonFileTest {
////
////    String jsonFileName = "static/json/perfume_json.json";
////    ReadJsonFile readJsonFile = new ReadJsonFile();
////
////    @Test
////    void ReadJson_브렌드_이름_확인() {
////
////        List<CollectionForm> perfumes = readJsonFile.readJsonFile(jsonFileName);
////
////        String firstBrandName = "르라보";
////        // 첫번째 Json 데이터
////        CollectionForm firstPerfume = perfumes.get(0);
////
////        assertEquals(firstBrandName, firstPerfume.getBrand());
////    }
////
////
////    @Test
////    void ReadJson_향수_이름_확인() {
////        List<CollectionForm> perfumes = readJsonFile.readJsonFile(jsonFileName);
////
////        String firstPerfumeName = "떼 마차 26";
////        CollectionForm firstPerfume = perfumes.get(0);
//////        System.out.println(firstPerfumeName);
//////        System.out.println(firstPerfume.getName());
////
////        assertEquals(firstPerfumeName, firstPerfume.getName());
////
////    }
////
////
////    @Test
////    void ReadJson_탑_노트_확인() {
////
////        List<CollectionForm> perfumes = readJsonFile.readJsonFile(jsonFileName);
////
////        CollectionForm firstPerfume = perfumes.get(0);
////        List<String> firstTopNote = Arrays.asList("무화과");
////
////        System.out.println(firstTopNote);
////        System.out.println(firstPerfume.getTop_nt());
////
////        assertEquals(firstTopNote, firstPerfume.getTop_nt());
////
////    }
////
////    @Test
////    void ReadJson_이미지_확인() {
////
////        List<CollectionForm> perfumes = readJsonFile.readJsonFile(jsonFileName);
////
////        CollectionForm firstPerfume = perfumes.get(0);
////        String firstImage = null;
////
////        System.out.println(firstImage);
////        System.out.println(firstPerfume.getImage());
////
////        assertEquals(firstImage, firstPerfume.getImage());
////
////    }
////
////    @Test
////    void ReadJson_날짜_확인() {
////
////        List<CollectionForm> perfumes = readJsonFile.readJsonFile(jsonFileName);
////
////        CollectionForm firstPerfume = perfumes.get(0);
////        String firstdate = "2023-11-16";
////
////        System.out.println(firstdate);
////        System.out.println(firstPerfume.getUpdate_at());
////
////        assertEquals(firstdate, firstPerfume.getUpdate_at());
////
////    }
////
////
////
////}
////
//        System.out.println(firstPerfume.getUpdate_at());
//
//        assertEquals(firstdate, firstPerfume.getUpdate_at());
//
//    }
//
//
//
//}
//
