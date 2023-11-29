package com.perfumepedia.PerfumePedia.repository;

import com.perfumepedia.PerfumePedia.domain.Word;
import com.perfumepedia.PerfumePedia.domain.WordType;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class WordRepository{

    private final EntityManager em;

    public void save(Word word){
        em.persist(word);
    }

    public Optional<Word> findById(Long id){
        return Optional.ofNullable(em.find(Word.class, id));
    }

    public Optional<Word> findByAliasAndName(String alias, String name){
        return em.createQuery("select w from Word w where w.alias= :alias AND w.name= :name", Word.class)
                .setParameter("alias", alias)
                .setParameter("name", name)
                .getResultList()
                .stream()
                .findAny();
    }

    public List<Word> findByAlias(String alias){
        return em.createQuery("select w from Word w where w.alias = :alias " +
                        "order by w.weight desc", Word.class)
                .setParameter("alias", alias)
                .setMaxResults(5)
                .getResultList();
    }

    public List<Word> findByTypeAndTypeId(Long typeId, WordType wordType){
        String jpql = "select w from Word w where w.wordType = :wordType" +
                " AND (w.brand = :id OR w.note = :id OR w.perfume = :id)";
        return em.createQuery(jpql, Word.class)
                .setParameter("id", typeId)
                .setParameter("type", wordType)
                .getResultList();
    }
}
