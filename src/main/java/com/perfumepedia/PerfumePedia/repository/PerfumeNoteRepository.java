package com.perfumepedia.PerfumePedia.repository;

import com.perfumepedia.PerfumePedia.domain.Note;
import com.perfumepedia.PerfumePedia.domain.Perfume;
import com.perfumepedia.PerfumePedia.domain.PerfumeNote;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PerfumeNoteRepository{

    private EntityManager em;

    public void save(PerfumeNote perfumeNote){
        em.persist(perfumeNote);
    }

    public Optional<PerfumeNote> findById(Long id){
        return Optional.ofNullable(em.find(PerfumeNote.class, id));
    }

    public List<PerfumeNote> findByPerfume(Perfume perfume){
        return em.createQuery("select pn from PerfumeNote pn where pn.perfume = :id", PerfumeNote.class)
                .setParameter("id", perfume.getId())
                .getResultList();
    }

    public List<PerfumeNote> findByNote(Note note){
        return em.createQuery("select pn from PerfumeNote pn where pn.note = :id", PerfumeNote.class)
                .setParameter("id", note.getId())
                .getResultList();
    }


}
