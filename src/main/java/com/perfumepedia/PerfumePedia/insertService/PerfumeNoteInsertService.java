package com.perfumepedia.PerfumePedia.insertService;

import com.perfumepedia.PerfumePedia.dataForm.CollectionForm;
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
public class PerfumeNoteInsertService {

    private final PerfumeNoteService perfumeNoteService;
    private final PerfumeInsertService perfumeInsertService;
    private final NoteForPerfumeNote noteForPerfumeNote;
    private final PerfumeRepository perfumeRepository;

    @Autowired
    public PerfumeNoteInsertService(PerfumeNoteService perfumeNoteService, PerfumeInsertService perfumeInsertService, NoteForPerfumeNote noteForPerfumeNote, PerfumeRepository perfumeRepository) {
        this.perfumeNoteService = perfumeNoteService;
        this.perfumeInsertService = perfumeInsertService;
        this.noteForPerfumeNote = noteForPerfumeNote;
        this.perfumeRepository =perfumeRepository;
    }


    public void insertPerfumeNoteData(CollectionForm collect) {
        Perfume perfume = perfumeRepository.findByName(collect.getName()).get();

        List<Note> topNote = noteForPerfumeNote.insertTopNoteForPerfumeNote(collect);
        List<Note> midNote = noteForPerfumeNote.insertMidNoteForPerfumeNote(collect);
        List<Note> baseNote = noteForPerfumeNote.insertBaseNoteForPerfumeNote(collect);
        List<Note> singleNote = noteForPerfumeNote.insertSingleNoteForPerfumeNote(collect);

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

