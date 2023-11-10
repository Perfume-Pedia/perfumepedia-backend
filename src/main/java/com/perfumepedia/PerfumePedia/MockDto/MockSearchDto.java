package com.perfumepedia.PerfumePedia.MockDto;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.perfumepedia.PerfumePedia.MockObject.MockJsonReadFile;
import com.perfumepedia.PerfumePedia.MockObject.MockSearch;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MockSearchDto {
    private List<MockSearch> items;
    private String last_item_id = "123";
    private int size;
    private String keyword;

    public MockSearchDto(String last_item_id, int size, String keyword) throws Exception{
        this.last_item_id = last_item_id;
        this.size = size;
        this.keyword = keyword;
        items = new ArrayList<>();

    }

    public void setItems() throws Exception{
        MockJsonReadFile mockJsonReadFile = new MockJsonReadFile();
        JsonNode perfumesNode = mockJsonReadFile.getPerfumesNode();

        boolean whenFindLastUuid = last_item_id.equals("0") ? true : false;

        for (JsonNode perfumeNode : perfumesNode) {
            if (size == 0)
                break;

            String uuid = perfumeNode.get("uuid").asText();

            if (uuid.equals(last_item_id)) {
                whenFindLastUuid = true;
                continue;
            }

            String brandName = perfumeNode.get("brand_name").asText();

            if (whenFindLastUuid && brandName.equals(keyword)) {
                String perfumeName = perfumeNode.get("perfume_name").asText();
                String imagePath = perfumeNode.get("image_path").asText();

                MockSearch mockSearch = new MockSearch();

                mockSearch.setUuid(uuid);
                mockSearch.setPerfume_name(perfumeName);
                mockSearch.setBrand_name(brandName);
                mockSearch.setImage_path(imagePath);

                items.add(mockSearch);

                size--;
                last_item_id = uuid;
            }
        }
    }

    public List<MockSearch> getItems(){
        return items;
    }

    public String getLast_item_id(){
        return last_item_id;
    }
}
