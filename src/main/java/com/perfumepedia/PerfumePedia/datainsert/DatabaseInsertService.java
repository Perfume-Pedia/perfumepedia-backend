package com.perfumepedia.PerfumePedia.datainsert;


import java.util.List;


public class DatabaseInsertService {

    // JSON 파일 경로 지정
    String jsonFileName = "static/json/perfume_json.json";

    ReadJsonFile jsonReader = new ReadJsonFile();

    // Json 데이터 읽기
    List<CollectionForm> perfumes = jsonReader.readJsonFile(jsonFileName);

    // brandData 객체 생성
    BrandData brandData = new BrandData();
    // perfumeData 객체 생성
    PerfumeData perfumeData = new PerfumeData();
    // noteData 객체 생성
    NoteData noteData = new NoteData();
    // perfumeNoteData 객체 생성
   PerfumeNoteData perfumeNoteData = new PerfumeNoteData();
    public void insertDataToDatabase() {

        for (CollectionForm collectionForms : perfumes) {

            brandData.insertBrandAndWordData(collectionForms);

            perfumeData.insertPerfumeAndWordData(collectionForms);

            noteData.insertNoteAndWordData(collectionForms);

            perfumeNoteData.insertPerfumeNoteData(collectionForms);
        }


    }
}