package com.perfumepedia.PerfumePedia.repository;

import com.perfumepedia.PerfumePedia.domain.Word;
import com.perfumepedia.PerfumePedia.domain.WordType;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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

    //
    public List<Word> findByTypeAndTypeId(Long typeId, WordType wordType) {
        String jpql = "select w from Word w where " + "w." +
                wordType.name().toLowerCase() +
                ".id = :id";

        return em.createQuery(jpql, Word.class)
                .setParameter("id", typeId)
                .getResultList();
    }

    // findByTypeAndTypeId의 여러가지 버전 중 위의 방법을 택함

//    // Join 사용
//    public List<Word> findByTypeAndTypeId(Long typeId, WordType wordType){
//        String jpql = "SELECT w FROM Word w " +
//                "LEFT JOIN w.brand b " +
//                "LEFT JOIN w.note n " +
//                "LEFT JOIN w.perfume p " +
//                "WHERE w.wordType = :wordType " +
//                "AND (b.id = :id OR n.id = :id OR p.id = :id)";
//
//        return em.createQuery(jpql, Word.class)
//                .setParameter("wordType", wordType)
//                .setParameter("id", typeId)
//                .getResultList();
//    }

//    // 쿼리 분리
//    public List<Word> findByTypeAndTypeId(Long typeId, WordType wordType) {
//        List<Word> result = new ArrayList<>();
//
//        if (wordType == WordType.BRAND) {
//            result = em.createQuery("SELECT w FROM Word w WHERE w.wordType = :wordType AND w.brand.id = :id", Word.class)
//                    .setParameter("wordType", wordType)
//                    .setParameter("id", typeId)
//                    .getResultList();
//        } else if (wordType == WordType.NOTE) {
//            result = em.createQuery("SELECT w FROM Word w WHERE w.wordType = :wordType AND w.note.id = :id", Word.class)
//                    .setParameter("wordType", wordType)
//                    .setParameter("id", typeId)
//                    .getResultList();
//        } else if (wordType == WordType.PERFUME) {
//            result = em.createQuery("SELECT w FROM Word w WHERE w.wordType = :wordType AND w.perfume.id = :id", Word.class)
//                    .setParameter("wordType", wordType)
//                    .setParameter("id", typeId)
//                    .getResultList();
//        }
//
//        return result;
//    }

}
