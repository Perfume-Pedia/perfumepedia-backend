package com.perfumepedia.PerfumePedia.MockObject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;

public class MockJsonReadFile {

    private JsonNode perfumesNode;

    public MockJsonReadFile(){
        String jsonFilePath = "static/json/mockPerfume.json";

        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(jsonFilePath);

        if (inputStream != null) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode rootNode = objectMapper.readTree(inputStream);

                perfumesNode = rootNode.get("mockPerfumes");
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public JsonNode getPerfumesNode() {
        return perfumesNode;
    }
}
