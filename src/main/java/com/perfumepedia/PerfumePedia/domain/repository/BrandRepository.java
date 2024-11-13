package com.perfumepedia.PerfumePedia.domain.repository;

import com.perfumepedia.PerfumePedia.domain.entity.Brand;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class BrandRepository{

    private EntityManager em;

    public void save(Brand brand){
        em.persist(brand);
    }

    public Optional<Brand> findById(Long id){
        return Optional.ofNullable(em.find(Brand.class, id));
    }

    public Optional<Brand> findByName(String name){
        return em.createQuery("select b from Brand b where b.name= :name", Brand.class)
                .setParameter("name", name)
                .getResultList()
                .stream()
                .findAny();
    }
}
