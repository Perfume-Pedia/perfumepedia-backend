package com.perfumepedia.PerfumePedia.insertService;

import com.perfumepedia.PerfumePedia.dataForm.CollectionForm;
import com.perfumepedia.PerfumePedia.domain.Note;
import com.perfumepedia.PerfumePedia.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoteForPerfumeNote {

    private final NoteRepository noteRepository;

    @Autowired
    public NoteForPerfumeNote(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }


    // 퍼퓸 노트를 위한 탑노트 리스트
    public List<Note> insertTopNoteForPerfumeNote(CollectionForm collect) {
        List<Note> topNotes = new ArrayList<>();

        for (String top_nt : collect.getTop_nt()) {
            if (isValidString(top_nt)) {
                Note note = noteRepository.findByName(top_nt).get();
                topNotes.add(note);
            }
        }
        return topNotes;
    }


    // 퍼퓸 노트를 위한 미들노트 리스트
    public List<Note> insertMidNoteForPerfumeNote(CollectionForm collect) {
        List<Note> midNotes = new ArrayList<>();

        for (String mid_nt : collect.getMid_nt()) {
            if (isValidString(mid_nt)) {
                Note note = noteRepository.findByName(mid_nt).get();
                midNotes.add(note);
            }
        }
        return midNotes;
    }


    // 퍼퓸 노트를 위한 베이스노트 리스트
    public List<Note> insertBaseNoteForPerfumeNote(CollectionForm collect) {
        List<Note> baseNotes = new ArrayList<>();

        for (String base_nt : collect.getBase_nt()) {
            if (isValidString(base_nt)) {
                Note note = noteRepository.findByName(base_nt).get();
                baseNotes.add(note);
            }
        }
        return baseNotes;
    }


    //  퍼퓸 노트를 위한 싱글노트 리스트
    public List<Note> insertSingleNoteForPerfumeNote(CollectionForm collect) {
        List<Note> singleNotes = new ArrayList<>();

        for (String single_nt : collect.getSingle_nt()) {
            if (isValidString(single_nt)) {
                Note note = noteRepository.findByName(single_nt).get();
                singleNotes.add(note);
            }
        }
        return singleNotes;
    }


    // 유효성 검사
    private boolean isValidString(String value) {
        return value != null && !value.trim().isEmpty();
    }

}

