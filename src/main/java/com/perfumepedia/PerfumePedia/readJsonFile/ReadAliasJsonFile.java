package com.perfumepedia.PerfumePedia.readJsonFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.perfumepedia.PerfumePedia.datainsert.AliasForm;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class ReadAliasJsonFile {
    public List<AliasForm> readAliasFile(String jsonFileName) {
        // ObjectMapper 객체 생성
        ObjectMapper mapper = new ObjectMapper();

        try {
            // JSON 파일을 PerfumeAlias 객체의 List로 읽어옴
            InputStream inputStream = ReadAliasJsonFile.class.getClassLoader().getResourceAsStream(jsonFileName);

            if (inputStream != null) {
                return mapper.readValue(inputStream, new TypeReference<List<AliasForm>>() {});
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
