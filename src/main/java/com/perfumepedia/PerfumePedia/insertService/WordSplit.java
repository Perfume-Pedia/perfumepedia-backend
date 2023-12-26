package com.perfumepedia.PerfumePedia.insertService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WordSplit {

    // String 을 매개변수로 받아 단어를 쪼갬
    public List<String> splitName(String word) {
        List<String> result = new ArrayList<>();

        for (int i = 0; i < word.length(); i++) {
            // 공백으로 시작하는 경우 제외
            if(word.substring(i, i+1).isBlank())
                continue;

            for (int j = i + 1; j <= word.length(); j++) {
                // 앞 뒤 공백 제거
                String subString = word.substring(i, j).trim();
                result.add(subString);

                // 중간 공백 여부 확인 -> 정규식 활용
                if(subString.matches(".*\\p{Z}+.*")){
                    // 모든 공백 제거
                    String subBlankedString = subString.replaceAll("\\p{Z}", "");
                    result.add(subBlankedString);
                }
            }
        }

        // 중복제거 리스트 반환
        return result.stream().distinct().collect(Collectors.toList());
    }


}