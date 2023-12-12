package com.perfumepedia.PerfumePedia.datainsert;


import com.perfumepedia.PerfumePedia.readJsonFile.ReadAliasJsonFile;
import com.perfumepedia.PerfumePedia.readJsonFile.ReadPerfumeJsonFile;
import com.perfumepedia.PerfumePedia.service.NoteService;
import com.perfumepedia.PerfumePedia.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseInsertService {

    // JSON 파일 경로 지정
//    String jsonFileName = "static/json/perfume_json.json";
//
//    ReadJsonFile jsonReader = new ReadJsonFile();
//
//    // Json 데이터 읽기
//    List<CollectionForm> perfumes;
//
//
//    // brandData 객체 생성
//    private final BrandData brandData;
//    private final PerfumeData perfumeData;
//    private final NoteDataForNote noteData;
//    private final PerfumeNoteData perfumeNoteData;
//
//    @Autowired
//    public DatabaseInsertService(
//            ReadJsonFile jsonReader,
//            BrandData brandData,
//            PerfumeData perfumeData,
//            NoteDataForNote noteData,
//            PerfumeNoteData perfumeNoteData
//    ) {
//        // Json 데이터 읽기
//        this.perfumes = jsonReader.readJsonFile(jsonFileName);
//
//        this.brandData = brandData;
//        this.perfumeData = perfumeData;
//        this.noteData = noteData;
//        this.perfumeNoteData = perfumeNoteData;
//    }

    String JsonFileName = "static/json/perfume_json.json";
    String aliasFileName = "static/json/perfume_alias.json";

    ReadPerfumeJsonFile jsonReader = new ReadPerfumeJsonFile();
    ReadAliasJsonFile readAliasJsonFile = new ReadAliasJsonFile();

    List<CollectionForm> perfumes;
    List<AliasForm> alias;

    private final BrandData brandData;
    private final PerfumeData perfumeData;
    private final NoteDataForNote noteData;
    private final PerfumeNoteData perfumeNoteData;
    private final NoteService noteService;
    private final WordService wordService;
    private final WordData wordData;

    @Autowired
    public DatabaseInsertService(
            ReadPerfumeJsonFile jsonReader,
            ReadAliasJsonFile readAliasJsonFile,
            NoteService noteService,
            WordService wordService,
            BrandData brandData,
            PerfumeData perfumeData,
            NoteDataForNote noteData,
            PerfumeNoteData perfumeNoteData,
            WordData wordData

    ) {
        this.perfumes = jsonReader.readJsonFile(JsonFileName);
        this.alias = readAliasJsonFile.readAliasFile(aliasFileName);
        this.jsonReader = jsonReader;
        this.readAliasJsonFile = readAliasJsonFile;
        this.noteService = noteService;
        this.wordService = wordService;
        this.brandData = brandData;
        this.perfumeData = perfumeData;
        this.noteData = noteData;
        this.perfumeNoteData = perfumeNoteData;
        this.wordData = wordData;
    }


    public void insertDataToDatabase() {
        for (CollectionForm collectionForms : perfumes) {
            brandData.insertBrandAndWordData(collectionForms);
            noteData.insertNoteAndWordData(collectionForms);
            perfumeData.insertPerfumeAndWordData(collectionForms);
            perfumeNoteData.insertPerfumeNoteData(collectionForms);
        }

        wordData.insertAliasData(aliasFileName);
    }
}