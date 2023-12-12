package com.perfumepedia.PerfumePedia.datainsert;

import com.perfumepedia.PerfumePedia.domain.Note;
import com.perfumepedia.PerfumePedia.domain.NoteType;
import com.perfumepedia.PerfumePedia.domain.Perfume;
import com.perfumepedia.PerfumePedia.domain.PerfumeNote;
import com.perfumepedia.PerfumePedia.repository.PerfumeRepository;
import com.perfumepedia.PerfumePedia.service.PerfumeNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerfumeNoteData {

    private final PerfumeNoteService perfumeNoteService;
    private final PerfumeData perfumeData;
    private final NoteDataForPerfumeNote noteDataForPerfumeNote;
    private final PerfumeRepository perfumeRepository;

    @Autowired
    public PerfumeNoteData(PerfumeNoteService perfumeNoteService, PerfumeData perfumeData,  NoteDataForPerfumeNote noteDataForPerfumeNote, PerfumeRepository perfumeRepository) {
        this.perfumeNoteService = perfumeNoteService;
        this.perfumeData = perfumeData;
        this.noteDataForPerfumeNote = noteDataForPerfumeNote;
        this.perfumeRepository =perfumeRepository;
    }


    public void insertPerfumeNoteData(CollectionForm collect) {
        Perfume perfume = perfumeRepository.findByName(collect.getName()).get();


        List<Note> topNote = noteDataForPerfumeNote.insertTopNoteForPerfumeNote(collect);
        List<Note> midNote = noteDataForPerfumeNote.insertMidNoteForPerfumeNote(collect);
        List<Note> baseNote = noteDataForPerfumeNote.insertBaseNoteForPerfumeNote(collect);
        List<Note> singleNote = noteDataForPerfumeNote.insertSingleNoteForPerfumeNote(collect);

        for (Note topNotes : topNote) {
            PerfumeNote perfumeNote = new PerfumeNote(NoteType.TOP);

            perfumeNote.setPerfume(perfume);
            perfumeNote.setNote(topNotes);
            perfumeNote.setDbDate(collect.getUpdate_at());

            // PerfumeNote 객체 저장
            perfumeNoteService.savePerfumeNote(perfumeNote);
        }

        for (Note midNotes : midNote) {
            PerfumeNote perfumeNote = new PerfumeNote(NoteType.MIDDLE);

            perfumeNote.setPerfume(perfume);
            perfumeNote.setNote(midNotes);
            perfumeNote.setDbDate(collect.getUpdate_at());

            // PerfumeNote 객체 저장
            perfumeNoteService.savePerfumeNote(perfumeNote);
        }

        for (Note baseNotes : baseNote) {
            PerfumeNote perfumeNote = new PerfumeNote(NoteType.BASE);

            perfumeNote.setPerfume(perfume);
            perfumeNote.setNote(baseNotes);
            perfumeNote.setDbDate(collect.getUpdate_at());

            // PerfumeNote 객체 저장
            perfumeNoteService.savePerfumeNote(perfumeNote);
        }
        for (Note singleNotes : singleNote) {
            PerfumeNote perfumeNote = new PerfumeNote(NoteType.SINGLE);

            perfumeNote.setPerfume(perfume);
            perfumeNote.setNote(singleNotes);
            perfumeNote.setDbDate(collect.getUpdate_at());

            // PerfumeNote 객체 저장
            perfumeNoteService.savePerfumeNote(perfumeNote);
        }

    }
}

