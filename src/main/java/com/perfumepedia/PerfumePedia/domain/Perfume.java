package com.perfumepedia.PerfumePedia.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Perfume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PERFUME_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    public Perfume(String name){
        this.name = name;
    }

    /**
     * Get 메소드<p>
     *     name
     */
    public String getName(){
        return name;
    }

}
