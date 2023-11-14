package com.perfumepedia.PerfumePedia.MockDto;

import com.perfumepedia.PerfumePedia.MockObject.MockAutoComplete;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class MockAutoCompleteDto {
    // Get으로 전달할 변수들
    private List<MockAutoComplete> items;
    private int item_count;

    // 임시저장 함수
    private String keyword;
    private String[] keywords = {"딥디크", "딥다크", "딥비디비딥", "펜할리곤스", "펜이에요", "펜트하우스"};
    private Map<String, ArrayList<String>> keywordMap;

    public MockAutoCompleteDto(String keyword){
        this.keyword = keyword;
        setKeywordMap();

        items = new ArrayList<>();
        setItems();

        item_count = items.size();
    }

    private void setKeywordMap(){
        keywordMap = new HashMap<>();
        for(String key: keywords){
            int length = key.length();

            for(int i=0; i<length; i++){
                for(int j=i+1; j<length+1; j++){
                    String subKey = key.substring(i, j);
                    if(keywordMap.containsKey(subKey)){
                        keywordMap.get(subKey).add(key);
                    }else{
                        keywordMap.put(subKey, new ArrayList<>());
                        keywordMap.get(subKey).add(key);
                    }
                }
            }
        }
    }

    private void setItems(){
        for(String key: keywordMap.get(keyword).stream().distinct().collect(Collectors.toList())){
            MockAutoComplete mockAutoComplete = new MockAutoComplete();
            mockAutoComplete.setKeyword(key);

            items.add(mockAutoComplete);
        }
    }

    public List<MockAutoComplete> getItems(){
        return items;
    }

    public int getItem_count(){
        return item_count;
    }
}
