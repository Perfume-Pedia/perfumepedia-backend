package com.perfumepedia.PerfumePedia.insertService;

import com.perfumepedia.PerfumePedia.dataForm.CollectionForm;
import com.perfumepedia.PerfumePedia.domain.Note;
import com.perfumepedia.PerfumePedia.domain.Word;
import com.perfumepedia.PerfumePedia.domain.WordType;
import com.perfumepedia.PerfumePedia.repository.NoteRepository;
import com.perfumepedia.PerfumePedia.service.NoteService;
import com.perfumepedia.PerfumePedia.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoteForNote {

    private final NoteService noteService;
    private final NoteRepository noteRepository;
    private final WordService wordService;

    @Autowired
    public NoteForNote(NoteService noteService, NoteRepository noteRepository, WordService wordService) {
        this.noteService = noteService;
        this.noteRepository = noteRepository;
        this.wordService = wordService;
    }

    // Add your methods related to NoteData here



    public void insertNoteAndWordData(CollectionForm collect) {

        List<Note> notes = collectDataToNote(collect);

        WordSplit wordSplit = new WordSplit();

        for (Note note : notes) {
            Long noteId = noteService.saveNote(note);
            // note를 쪼개기 위해 noteId 생성

            // 저장되어 있는 노트의 id으로 note를 불러옴
            Note savedNote = noteRepository.findById(noteId).orElse(null);

            if (savedNote != null) {
                // 노트의 name을 쪼갬
                List<String> noteNameParts = wordSplit.splitName(savedNote.getName());

                for (String alias : noteNameParts) {
                    // word 객체 생성, 저장
                    Word word = new Word(alias, savedNote.getName() , WordType.NOTE);
                    word.setEntity(savedNote);
                    word.setDbDate(collect.getUpdate_at());

                    // 저장
                    wordService.saveWord(word);
                }
            }
        }
    }


    // 모든 노트타입의 데이터를 notes리스트에 추가
    public List<Note> collectDataToNote(CollectionForm collect) {
        List<Note> notes = new ArrayList<>();

        notes.addAll(collectDataToTopNote(collect));
        notes.addAll(collectDataToMidNote(collect));
        notes.addAll(collectDataToBaseNote(collect));
        notes.addAll(collectDataToSingleNote(collect));

        return notes;
    }

    // top_nt 데이터를 Note 객체로 변환하여 리스트에 추가
    public List<Note> collectDataToTopNote(CollectionForm collect) {
    List<Note> TopNotes = new ArrayList<>();

        for (String top_nt : collect.getTop_nt()) {
            if (isValidString(top_nt)) {
                Note note = new Note(top_nt);
                note.setDbDate(collect.getUpdate_at());
                TopNotes.add(note);

            }
        }
        return TopNotes;
    }


    // Mid_nt 데이터를 Note 객체로 변환하여 리스트에 추가
    public List<Note> collectDataToMidNote(CollectionForm collect) {
    List<Note> midNotes = new ArrayList<>();

        for (String mid_nt : collect.getMid_nt()) {
            if (isValidString(mid_nt)) {
                Note note = new Note(mid_nt);
                note.setDbDate(collect.getUpdate_at());
                midNotes.add(note);
            }
        }
        return midNotes;
    }


    // Base_nt 데이터를 Note 객체로 변환하여 리스트에 추가
    public List<Note> collectDataToBaseNote(CollectionForm collect) {
    List<Note> BaseNotes = new ArrayList<>();

        for (String base_nt : collect.getBase_nt()) {
            if (isValidString(base_nt)) {
                Note note = new Note(base_nt);
                note.setDbDate(collect.getUpdate_at());
                BaseNotes.add(note);
            }
        }
        return BaseNotes;
    }


    // Single_nt 데이터를 Note 객체로 변환하여 리스트에 추가
    public List<Note> collectDataToSingleNote(CollectionForm collect) {

    List<Note> singleNotes = new ArrayList<>();

        for (String single_nt : collect.getSingle_nt()) {
            if (isValidString(single_nt)) {
                Note note = new Note(single_nt);
                note.setDbDate(collect.getUpdate_at());
                singleNotes.add(note);
            }
        }
        return singleNotes;
    }


    private boolean isValidString(String value) {
        return value != null && !value.trim().isEmpty();
    }

}
