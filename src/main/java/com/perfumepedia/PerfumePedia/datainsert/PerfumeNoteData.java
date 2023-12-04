package com.perfumepedia.PerfumePedia.datainsert;

import com.perfumepedia.PerfumePedia.domain.Note;
import com.perfumepedia.PerfumePedia.domain.NoteType;
import com.perfumepedia.PerfumePedia.domain.Perfume;
import com.perfumepedia.PerfumePedia.domain.PerfumeNote;
import com.perfumepedia.PerfumePedia.service.PerfumeNoteService;

import java.util.List;

public class PerfumeNoteData {
    private PerfumeNoteService perfumeNoteService;
    PerfumeData perfumeData = new PerfumeData();
    NoteData noteData = new NoteData();


    public void insertPerfumeNoteData(CollectionForm collect) {
        Perfume perfume = perfumeData.collectDataToPerfume(collect);
        List<Note> topNote = noteData.collectDataToTopNote(collect);
        List<Note> midNote = noteData.collectDataToMidNote(collect);
        List<Note> baseNote = noteData.collectDataToNote(collect);
        List<Note> singleNote = noteData.collectDataToBaseNote(collect);

        for (Note topNotes : topNote) {
            PerfumeNote perfumeNote = new PerfumeNote(NoteType.TOP);

            perfumeNote.setPerfume(perfume);
            perfumeNote.setNote(topNotes);
            perfumeNote.setDbDate(collect.getUpdate_at());

            // PerfumeNote 객체 저장
            perfumeNoteService.savePerfumeNote(perfumeNote);
        }

        for (Note midNotes : midNote) {
            PerfumeNote perfumeNote = new PerfumeNote(NoteType.TOP);

            perfumeNote.setPerfume(perfume);
            perfumeNote.setNote(midNotes);
            perfumeNote.setDbDate(collect.getUpdate_at());

            // PerfumeNote 객체 저장
            perfumeNoteService.savePerfumeNote(perfumeNote);
        }

        for (Note baseNotes : baseNote) {
            PerfumeNote perfumeNote = new PerfumeNote(NoteType.MIDDLE);

            perfumeNote.setPerfume(perfume);
            perfumeNote.setNote(baseNotes);
            perfumeNote.setDbDate(collect.getUpdate_at());

            // PerfumeNote 객체 저장
            perfumeNoteService.savePerfumeNote(perfumeNote);
        }
        for (Note singleNotes : singleNote) {
            PerfumeNote perfumeNote = new PerfumeNote(NoteType.BASE);

            perfumeNote.setPerfume(perfume);
            perfumeNote.setNote(singleNotes);
            perfumeNote.setDbDate(collect.getUpdate_at());

            // PerfumeNote 객체 저장
            perfumeNoteService.savePerfumeNote(perfumeNote);
        }

        for (Note baseNotes : baseNote) {
            PerfumeNote perfumeNote = new PerfumeNote(NoteType.SINGLE);

            perfumeNote.setPerfume(perfume);
            perfumeNote.setNote(baseNotes);
            perfumeNote.setDbDate(collect.getUpdate_at());

            // PerfumeNote 객체 저장
            perfumeNoteService.savePerfumeNote(perfumeNote);
        }


    }
}

