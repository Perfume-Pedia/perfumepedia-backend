package com.perfumepedia.PerfumePedia.MockDto;

import com.fasterxml.jackson.databind.JsonNode;
import com.perfumepedia.PerfumePedia.MockObject.MockFavPerfume;
import com.perfumepedia.PerfumePedia.MockObject.MockJsonReadFile;
import com.perfumepedia.PerfumePedia.MockObject.MockSearch;

import java.util.ArrayList;
import java.util.List;

public class MockFavPerfumeDto {
    private List<String> uuids;
    private int size;

    private List<MockFavPerfume> items;

    public MockFavPerfumeDto(List<String> uuids) throws Exception{
        items = new ArrayList<>();

        this.uuids = uuids;
        this.size = uuids.size();
    }

    public void setItems() throws Exception{
        MockJsonReadFile mockJsonReadFile = new MockJsonReadFile();
        JsonNode perfumesNode = mockJsonReadFile.getPerfumesNode();

        for (JsonNode perfumeNode : perfumesNode) {
            if(size == 0)
                break;

            String uuid = perfumeNode.get("uuid").asText();

            if(uuids.contains(uuid)){
                String perfumeName = perfumeNode.get("perfume_name").asText();
                String brandName = perfumeNode.get("brand_name").asText();
                String imagePath = perfumeNode.get("image_path").asText();

                MockFavPerfume mockFavPerfume = new MockFavPerfume();

                mockFavPerfume.setUuid(uuid);
                mockFavPerfume.setPerfume_name(perfumeName);
                mockFavPerfume.setBrand_name(brandName);
                mockFavPerfume.setImage_path(imagePath);

                items.add(mockFavPerfume);

                size--;
            }
        }
    }

    public List<MockFavPerfume> getItems(){
        return items;
    }
}
