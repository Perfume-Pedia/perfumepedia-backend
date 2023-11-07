package com.perfumepedia.PerfumePedia.MockObject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.io.ClassPathResource;

import java.util.ArrayList;
import java.util.List;

public class MockPerfumeDto {
    private List<MockPerfume> items;
    private String last_item_id = "123";
    private int size;

    public MockPerfumeDto(String last_item_id, int size) throws Exception{
        this.last_item_id = last_item_id;
        this.size = size;
        items = new ArrayList<>();

        setItems();
    }

    void setItems() throws Exception{
        String jsonFilePath = "static/json/mockPerfume.json";

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode rootNode = objectMapper.readTree(new ClassPathResource(jsonFilePath).getFile());

        JsonNode perfumesNode = rootNode.get("mockPerfumes");

        boolean whenFindLastUuid = last_item_id.equals("0")? true: false;

        for (JsonNode perfumeNode : perfumesNode) {
            if(size == 0)
                break;

            String uuid = perfumeNode.get("uuid").asText();

            if (uuid.equals(last_item_id)) {
                whenFindLastUuid = true;
                continue;
            }

            if(whenFindLastUuid){
                String perfumeName = perfumeNode.get("perfume_name").asText();
                String brandName = perfumeNode.get("brand_name").asText();
                String imagePath = perfumeNode.get("image_path").asText();

                MockPerfume mockPerfume = new MockPerfume();

                mockPerfume.setUuid(uuid);
                mockPerfume.setPerfume_name(perfumeName);
                mockPerfume.setBrand_name(brandName);
                mockPerfume.setImage_path(imagePath);

                items.add(mockPerfume);

                size--;
                last_item_id = uuid;
            }



        }
    }

    public List<MockPerfume> getItems(){
        return items;
    }

    public String getLast_item_id(){
        return last_item_id;
    }
}
