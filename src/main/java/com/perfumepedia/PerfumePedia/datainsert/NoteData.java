package com.perfumepedia.PerfumePedia.datainsert;

import com.perfumepedia.PerfumePedia.domain.Note;
import com.perfumepedia.PerfumePedia.domain.Word;
import com.perfumepedia.PerfumePedia.domain.WordType;
import com.perfumepedia.PerfumePedia.repository.NoteRepository;
import com.perfumepedia.PerfumePedia.service.NoteService;
import com.perfumepedia.PerfumePedia.service.WordService;

import java.util.ArrayList;
import java.util.List;

public class NoteData {

    private NoteRepository noteRepository;
    private NoteService noteService;
    private WordService wordService;

    public void insertNoteAndWordData(CollectionForm collect) {

        List<Note> notes = collectDataToNote(collect);

        WordSplit wordSplit = new WordSplit();

        for (Note note : notes) {
            // note를 쪼개기 위해 noteId 생성
            Long noteId = noteService.saveNote(note);

            // 저장되어 있는 노트의 id으로 note를 불러옴
            Note savedNote = noteRepository.findById(noteId).orElse(null);

            if (savedNote != null) {
                // 노트의 name을 쪼갬
                List<String> noteNameParts = wordSplit.splitName(savedNote.getName());

                for (String alias : noteNameParts) {
                    // word 객체 생성, 저장
                    Word word = new Word(alias, collect.getName(), WordType.NOTE);
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

    List<Note> TopNotes = new ArrayList<>();
    List<Note> midNotes = new ArrayList<>();
    List<Note> BaseNotes = new ArrayList<>();
    List<Note> singleNotes = new ArrayList<>();

    // top_nt 데이터를 Note 객체로 변환하여 리스트에 추가
    public List<Note> collectDataToTopNote(CollectionForm collect) {

        for (String top_nt : collect.getTop_nt()) {
            Note note = new Note(top_nt);
            note.setDbDate(collect.getUpdate_at());

        }
        return TopNotes;
    }


    // Mid_nt 데이터를 Note 객체로 변환하여 리스트에 추가
    public List<Note> collectDataToMidNote(CollectionForm collect) {

        for (String mid_nt : collect.getMid_nt()) {
            Note note = new Note(mid_nt);
            note.setDbDate(collect.getUpdate_at());
        }
        return midNotes;
    }


    // Base_nt 데이터를 Note 객체로 변환하여 리스트에 추가
    public List<Note> collectDataToBaseNote(CollectionForm collect) {

        for (String base_nt : collect.getBase_nt()) {
            Note note = new Note(base_nt);
            note.setDbDate(collect.getUpdate_at());
        }
        return BaseNotes;
    }

    // Single_nt 데이터를 Note 객체로 변환하여 리스트에 추가
    public List<Note> collectDataToSingleNote(CollectionForm collect) {

        for (String single_nt : collect.getSingle_nt()) {
            Note note = new Note(single_nt);
            note.setDbDate(collect.getUpdate_at());
        }

        return singleNotes;

    }

}
