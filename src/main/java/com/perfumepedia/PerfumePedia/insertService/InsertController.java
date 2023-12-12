package com.perfumepedia.PerfumePedia.insertService;


import com.perfumepedia.PerfumePedia.dataForm.AliasForm;
import com.perfumepedia.PerfumePedia.dataForm.CollectionForm;
import com.perfumepedia.PerfumePedia.readJsonFile.ReadAliasJsonFile;
import com.perfumepedia.PerfumePedia.readJsonFile.ReadPerfumeJsonFile;
import com.perfumepedia.PerfumePedia.service.NoteService;
import com.perfumepedia.PerfumePedia.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsertController {

    // JsonFile 경로 지정
    String JsonFileName = "static/json/perfume_json.json";
    String aliasFileName = "static/json/perfume_alias.json";

    ReadPerfumeJsonFile jsonReader = new ReadPerfumeJsonFile();
    ReadAliasJsonFile readAliasJsonFile = new ReadAliasJsonFile();

    List<CollectionForm> perfumes;
    List<AliasForm> alias;

    private final BrandInsertService brandInsertService;
    private final PerfumeInsertService perfumeInsertService;
    private final NoteForNote noteData;
    private final PerfumeNoteInsertService perfumeNoteInsertService;
    private final NoteService noteService;
    private final WordService wordService;
    private final WordInsertService wordInsertService;

    @Autowired
    public InsertController(
            ReadPerfumeJsonFile jsonReader,
            ReadAliasJsonFile readAliasJsonFile,
            NoteService noteService,
            WordService wordService,
            BrandInsertService brandInsertService,
            PerfumeInsertService perfumeInsertService,
            NoteForNote noteData,
            PerfumeNoteInsertService perfumeNoteInsertService,
            WordInsertService wordInsertService

    ) {
        this.perfumes = jsonReader.readJsonFile(JsonFileName);
        this.alias = readAliasJsonFile.readAliasFile(aliasFileName);
        this.jsonReader = jsonReader;
        this.readAliasJsonFile = readAliasJsonFile;
        this.noteService = noteService;
        this.wordService = wordService;
        this.brandInsertService = brandInsertService;
        this.perfumeInsertService = perfumeInsertService;
        this.noteData = noteData;
        this.perfumeNoteInsertService = perfumeNoteInsertService;
        this.wordInsertService = wordInsertService;
    }


    public void insertDataToDatabase() {
        for (CollectionForm collectionForms : perfumes) {
            brandInsertService.insertBrandAndWordData(collectionForms);
            noteData.insertNoteAndWordData(collectionForms);
            perfumeInsertService.insertPerfumeAndWordData(collectionForms);
            perfumeNoteInsertService.insertPerfumeNoteData(collectionForms);
        }

        wordInsertService.insertAliasData(aliasFileName);
    }

}