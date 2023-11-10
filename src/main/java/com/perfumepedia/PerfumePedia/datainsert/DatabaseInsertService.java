package com.perfumepedia.PerfumePedia.datainsert;

import java.util.List;

public class DatabaseInsertService {

    // JSON 파일 경로 지정
    String jsonFileName = "static/json/perfume_json.json";

    ReadJsonFile jsonReader = new ReadJsonFile();

    // Json 데이터 읽기
    List<CollectionForm> perfumes = jsonReader.readJsonFile(jsonFileName);

}
