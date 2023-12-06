package com.perfumepedia.PerfumePedia.repository;

import com.perfumepedia.PerfumePedia.domain.Brand;
import com.perfumepedia.PerfumePedia.domain.Perfume;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PerfumeRepository{

    private final EntityManager em;

    public void save(Perfume perfume){
        em.persist(perfume);
    }

    public Optional<Perfume> findById(Long id){
        return Optional.ofNullable(em.find(Perfume.class, id));
    }

    public Optional<Perfume> findByName(String name){
        return em.createQuery("select p from Perfume p where p.name= :name", Perfume.class)
                .setParameter("name", name)
                .getResultList()
                .stream()
                .findAny();
    }

    public List<Perfume> findByBrand(Brand brand){
        return em.createQuery("select p from Perfume p where p.brand = :id", Perfume.class)
                .setParameter("id", brand)
                .getResultList();
    }

}
