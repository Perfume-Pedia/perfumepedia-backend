package com.perfumepedia.PerfumePedia.insertService;


import com.perfumepedia.PerfumePedia.dataForm.AliasForm;
import com.perfumepedia.PerfumePedia.dataForm.CollectionForm;
import com.perfumepedia.PerfumePedia.readJsonFile.ReadAliasJsonFile;
import com.perfumepedia.PerfumePedia.readJsonFile.ReadPerfumeJsonFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsertController {

    // JsonFile 경로 지정
    String JsonFileName = "static/json/perfume_json.json";
    String aliasFileName = "static/json/perfume_alias.json";

    List<CollectionForm> perfumes;
    List<AliasForm> alias;

    private final BrandInsertService brandInsertService;
    private final PerfumeInsertService perfumeInsertService;
    private final NoteForNote noteData;
    private final PerfumeNoteInsertService perfumeNoteInsertService;
    private final WordInsertService wordInsertService;

    @Autowired
    public InsertController(
            ReadPerfumeJsonFile jsonReader,
            ReadAliasJsonFile readAliasJsonFile,
            BrandInsertService brandInsertService,
            PerfumeInsertService perfumeInsertService,
            NoteForNote noteData,
            PerfumeNoteInsertService perfumeNoteInsertService,
            WordInsertService wordInsertService

    ) {
        this.perfumes = jsonReader.readJsonFile(JsonFileName);
        this.alias = readAliasJsonFile.readAliasFile(aliasFileName);
        this.brandInsertService = brandInsertService;
        this.perfumeInsertService = perfumeInsertService;
        this.noteData = noteData;
        this.perfumeNoteInsertService = perfumeNoteInsertService;
        this.wordInsertService = wordInsertService;
    }


    public void insertDataToDatabase() {
        System.out.println("===perfume insert start===");
        for (CollectionForm collectionForms : perfumes) {
            System.out.println("[perfume insert] " + collectionForms.getName());

            brandInsertService.insertBrandAndWordData(collectionForms);
            noteData.insertNoteAndWordData(collectionForms);
            perfumeInsertService.insertPerfumeAndWordData(collectionForms);
            perfumeNoteInsertService.insertPerfumeNoteData(collectionForms);
        }
        System.out.println("---perfume insert end---");

        System.out.println("===alias insert start===");
        wordInsertService.insertAliasData(aliasFileName);
        System.out.println("---alias insert end---");
    }

}