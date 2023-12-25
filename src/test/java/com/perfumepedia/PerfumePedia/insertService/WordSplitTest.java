package com.perfumepedia.PerfumePedia.insertService;


import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import static org.junit.Assert.*;

@SpringBootTest
@Transactional
@RequiredArgsConstructor
public class WordSplitTest {

    WordSplit wordSplit = new WordSplit();

    @Test
    public void 앞뒤_공백_제거_확인() throws Exception{
        //given
        String keyword = " 앞뒤공백별칭테스트 ";

        //when
        List<String> words = wordSplit.splitName(keyword);

        //then
        boolean hasGapFront = words.stream().anyMatch(word -> word.matches("\\p{Z}"));
        assertTrue(!hasGapFront);
    }

    @Test
    public void 공백만_있는_별칭_여부_확인() throws Exception{
        //given
        String keyword = " 중간 공백 별칭 테스트 ";

        //when
        List<String> words = wordSplit.splitName(keyword);

        //then
        boolean hasGapFront = words.stream().anyMatch(word -> word.matches("\\p{Z}"));
        assertTrue(!hasGapFront);
    }

    @Test
    public void 중복_별칭_여부_확인() throws Exception{
        //given
        String keyword = " 같은 별칭 같은 별칭 ";
        String duplicateWord1 = "같은";
        String duplicateWord2 = "별칭";
        String duplicateWord3 = "은별";
        String duplicateWord4 = "같은별칭";


        //when
        List<String> words = wordSplit.splitName(keyword);

        //then
        long countByDuplicateWord1 = words.stream().filter(word -> duplicateWord1.equals(word)).count();
        long countByDuplicateWord2 = words.stream().filter(word -> duplicateWord2.equals(word)).count();
        long countByDuplicateWord3 = words.stream().filter(word -> duplicateWord3.equals(word)).count();
        long countByDuplicateWord4 = words.stream().filter(word -> duplicateWord4.equals(word)).count();

        assertEquals(1L, countByDuplicateWord1);
        assertEquals(1L, countByDuplicateWord2);
        assertEquals(1L, countByDuplicateWord3);
        assertEquals(1L, countByDuplicateWord4);
    }

}
