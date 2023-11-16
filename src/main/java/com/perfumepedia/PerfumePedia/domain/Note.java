package com.perfumepedia.PerfumePedia.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NOTE_ID")
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Embedded
    @AttributeOverride(name = "createdAt", column = @Column(name = "CREATED_AT"))
    @AttributeOverride(name = "updatedAt", column = @Column(name = "UPDATED_AT"))
    private DBDate dbDate;

    public Note(String name){
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("name 은 null 이거나 빈 값일 수 없습니다.");
        }
        this.name = name;
    }

    /**
     * Set 메소드 <p>
     * dbDate
     */
    public void setDbDate(String yearMonthDay){
        this.dbDate = new DBDate(yearMonthDay);
    }

    /**
     * Get 메소드 <p>
     * id, name, dbDate
     */
    public Long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public DBDate getDbDate(){
        return dbDate;
    }
}
