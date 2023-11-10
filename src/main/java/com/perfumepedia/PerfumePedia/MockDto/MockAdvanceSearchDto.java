package com.perfumepedia.PerfumePedia.MockDto;


import com.fasterxml.jackson.databind.JsonNode;
import com.perfumepedia.PerfumePedia.MockObject.MockAdvanceSerach;
import com.perfumepedia.PerfumePedia.MockObject.MockJsonReadFile;

import java.util.ArrayList;
import java.util.List;


public class MockAdvanceSearchDto {
    private MockAdvanceSerach data;
    private String uuid;

    public MockAdvanceSearchDto(String uuid) throws Exception{
        this.uuid = uuid;

        setData();
    }

    public void setData() throws Exception{
        MockJsonReadFile mockJsonReadFile = new MockJsonReadFile();
        JsonNode perfumesNodes = mockJsonReadFile.getPerfumesNode();

        for(JsonNode perfumeNode: perfumesNodes){
            String uuid = perfumeNode.get("uuid").asText();

            if(uuid.equals(this.uuid)){

                String brand_name = perfumeNode.get("brand_name").asText();
                String perfume_name = perfumeNode.get("perfume_name").asText();
                String price = perfumeNode.get("price").asText();
                String url = perfumeNode.get("url").asText();
                String image_path = perfumeNode.get("image_path").asText();
                String created_at = perfumeNode.get("created_at").asText();
                List<String> top_note_names = getNoteList(perfumeNode, "top_note");
                List<String> middle_note_names = getNoteList(perfumeNode, "middle_note");
                List<String> base_note_names = getNoteList(perfumeNode, "base_note");
                List<String> single_note_names = getNoteList(perfumeNode, "single_note");
                data = new MockAdvanceSerach();

                data.setUuid(uuid);
                data.setPerfume_name(perfume_name);
                data.setBrand_name(brand_name);
                data.setPrice(price);
                data.setUrl(url);
                data.setImage_path(image_path);
                data.setCreated_at(created_at);
                data.setTop_note_names(top_note_names);
                data.setMiddle_note_names(middle_note_names);
                data.setBase_note_names(base_note_names);
                data.setSingle_note_names(single_note_names);
            }
        }
    }

    private List<String> getNoteList(JsonNode perfumesNode, String noteType){
        List<String> note_names = new ArrayList<>();

        try {
            String[] notes = perfumesNode.get(noteType).asText().split(",");

            for(String note: notes){
                String noteTrim = note.trim();

                note_names.add(noteTrim);
            }
        }catch (Exception e){
            System.out.println(e);
        }

        return note_names;
    }

    public MockAdvanceSerach getData(){return data;}
}
