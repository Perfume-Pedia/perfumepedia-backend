package com.perfumepedia.PerfumePedia.datainsert;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.perfumepedia.PerfumePedia.datainsert.CollectionForm;

public class ReadJsonFile {
    public static void main(String[] args) {
        // JSON 파일 경로 지정
        String jsonFileName = "perfume_json.json";

        // ObjectMapper 객체 생성
        ObjectMapper mapper = new ObjectMapper();

        try {
            // JSON 파일을 PerfumeForm 객체의 List로 읽어옴
            InputStream inputStream = ReadJsonFile.class.getClassLoader().getResourceAsStream(jsonFileName);

            if (inputStream != null) {
                List<CollectionForm> perfumes = mapper.readValue(inputStream, new TypeReference<List<CollectionForm>>() {});

                for (CollectionForm perfume : perfumes) {
                    // 출력 또는 다른 작업 수행
                    System.out.println("Brand: " + perfume.getBrand());
                    System.out.println("Name: " + perfume.getName());

                    // 각 필드가 배열인 경우 처리
                    System.out.println("Top Notes: " + convertListToString(perfume.getTop_nt()));
                    System.out.println("Mid Notes: " + convertListToString(perfume.getMid_nt()));
                    System.out.println("Base Notes: " + convertListToString(perfume.getBase_nt()));
                    System.out.println("Single Notes: " + convertListToString(perfume.getSingle_nt()));

                    System.out.println();
                }
            } else {
                System.out.println("JSON file not found!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String convertListToString(List<String> list) {
        if (list != null) {
            // 리스트를 문자열로 변환하되, null인 경우 "null"로 표시
            return String.join(", ", list);
        } else {
            return "null";
        }
    }
}
