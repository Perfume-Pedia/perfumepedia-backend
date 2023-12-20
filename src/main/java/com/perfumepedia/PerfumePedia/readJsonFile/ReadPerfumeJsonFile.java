package com.perfumepedia.PerfumePedia.readJsonFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.perfumepedia.PerfumePedia.dataForm.CollectionForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReadPerfumeJsonFile {
    public List<CollectionForm> readJsonFile(String jsonFileName) {
        // ObjectMapper 객체 생성
        ObjectMapper mapper = new ObjectMapper();

        try {
            // JSON 파일을 PerfumeForm 객체의 List로 읽어옴
//            InputStream inputStream = ReadPerfumeJsonFile.class.getClassLoader().getResourceAsStream(jsonFileName);
            InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(jsonFileName);

            if (inputStream != null) {
                return mapper.readValue(inputStream, new TypeReference<List<CollectionForm>>() {});
            } else {
                System.out.println("JSON file not found!");
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
