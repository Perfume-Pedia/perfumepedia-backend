package com.perfumepedia.PerfumePedia.datainsert;

import java.util.ArrayList;
import java.util.List;

public class WordSplit {

    // String 을 매개변수로 받아 단어를 쪼갬
    public List<String> splitName(String word) {
        List<String> result = new ArrayList<>();

        for (int i = 0; i < word.length(); i++) {
            for (int j = i + 1; j <= word.length(); j++) {
                String subString = word.substring(i, j).trim();

                if(!subString.isEmpty()) {
                    result.add(subString);
                }
            }
        }

        return result;
    }


}

/**
 * 당근 인턴 --> 당근 인턴
 * 당근인턴  --> 당근 인턴
 *
* */