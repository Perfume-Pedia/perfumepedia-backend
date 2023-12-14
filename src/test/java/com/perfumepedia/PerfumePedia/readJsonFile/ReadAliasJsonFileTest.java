package com.perfumepedia.PerfumePedia.readJsonFile;

import lombok.RequiredArgsConstructor;
import com.perfumepedia.PerfumePedia.dataForm.AliasForm;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

import static org.junit.Assert.*;


@SpringBootTest
@Transactional
@RequiredArgsConstructor
public class ReadAliasJsonFileTest {

    String jsonFileName = "static/json/perfume_alias.json";

    ReadAliasJsonFile readAliasJsonFile = new ReadAliasJsonFile();


    @Test
    public void 노트_이름_확인() {

        List<AliasForm> notes = readAliasJsonFile.readAliasFile(jsonFileName);

        String firstNoteName = "5월의 장미";
        AliasForm firstNote = notes.get(0);

        assertEquals(firstNoteName, firstNote.getNotename());
    }


    @Test
    public void 노트_별칭_확인() {

        List<AliasForm> notes = readAliasJsonFile.readAliasFile(jsonFileName);

        String firstAliasName = "5월의 장미";
        AliasForm firstAlias = notes.get(0);

        assertEquals(firstAliasName, firstAlias.getAlias());

    }

}
