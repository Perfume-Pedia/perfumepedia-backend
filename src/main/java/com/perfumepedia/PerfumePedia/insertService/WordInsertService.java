package com.perfumepedia.PerfumePedia.insertService;

import com.perfumepedia.PerfumePedia.dataForm.AliasForm;
import com.perfumepedia.PerfumePedia.readJsonFile.ReadAliasJsonFile;
import com.perfumepedia.PerfumePedia.domain.Note;
import com.perfumepedia.PerfumePedia.domain.Word;
import com.perfumepedia.PerfumePedia.domain.WordType;
import com.perfumepedia.PerfumePedia.repository.NoteRepository;
import com.perfumepedia.PerfumePedia.service.NoteService;
import com.perfumepedia.PerfumePedia.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordInsertService {

    private final NoteService noteService;
    private final NoteRepository noteRepository;
    private final WordService wordService;
    private final ReadAliasJsonFile readAliasJsonFile;


    @Autowired
    public WordInsertService(
            NoteService noteService,
            NoteRepository noteRepository,
            WordService wordService,
            ReadAliasJsonFile readAliasJsonFile)
    {
        this.noteService = noteService;
        this.noteRepository = noteRepository;
        this.wordService = wordService;
        this.readAliasJsonFile = readAliasJsonFile;
    }


    public void insertAliasData(String jsonFileName) {
        List<AliasForm> aliasForms = readAliasJsonFile.readAliasFile(jsonFileName);

        for (AliasForm aliasForm : aliasForms) {
            collectDataToAlias(aliasForm);
        }
    }


    public void collectDataToAlias(AliasForm aliasForm) {
        // noteRepository 사용하여 노트를 찾음
        try {
            Note note = noteRepository.findByName(aliasForm.getNotename())
                    .orElseThrow(() -> new IllegalStateException("해당 노트를 찾을 수 없습니다. NoteName: " + aliasForm.getNotename()));
            // WordSplit 객체 생성
            WordSplit wordSplit = new WordSplit();

            // 별칭 폼에서 별칭을 가져와 단어를 쪼갬
            List<String> aliasParts = wordSplit.splitName(aliasForm.getAlias());

            for (String aliasNote : aliasParts) {
                if (isValidString(aliasNote)) {
                    // 쪼갠 별칭 리스트, 별칭 폼에서 가져온 노트 이름, 노트 타입의 워드를 매개변수로 워드 객체 생성
                    Word word = new Word(aliasNote.trim(), note.getName(), WordType.NOTE);
                    word.setEntity(note);
                    word.setDbDate(note.getDbDate().getUpdatedAt().toString());


                    // WordService를 사용하여 워드를 저장
                    wordService.saveWord(word);
                }
            }
        }catch (IllegalStateException e){
            return;
        }

    }


    private boolean isValidString(String value) {
        return value != null && !value.trim().isEmpty();
    }

}
