package com.perfumepedia.PerfumePedia.repository;

import com.perfumepedia.PerfumePedia.domain.Note;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class NoteRepository {

    private EntityManager em;

    public void save(Note note){
        em.persist(note);
    }

    public Optional<Note> findById(Long id){
        return Optional.ofNullable(em.find(Note.class, id));
    }

    public Optional<Note> findByName(String name){
        return em.createQuery("select n from Note n where n.name= :name", Note.class)
                .setParameter("name", name)
                .getResultList()
                .stream()
                .findAny();
    }
}
