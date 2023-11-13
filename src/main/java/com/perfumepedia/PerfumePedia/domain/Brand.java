package com.perfumepedia.PerfumePedia.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Brand {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BRAND_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "URL")
    private String url;

    @Embedded
    @AttributeOverride(name = "createdAt", column = @Column(name = "CREATED_AT"))
    @AttributeOverride(name = "updatedAt", column = @Column(name = "UPDATED_AT"))
    private DBDate dbDate;

    public Brand(String name){
        this.name = name;
        this.dbDate = new DBDate();
    }

    // set
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Get 메소드: <p>
     * id, name, url, dbDate
     */
    public Long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getUrl(){
        return url;
    }

    public DBDate getDBDate(){
        return dbDate;
    }

}
