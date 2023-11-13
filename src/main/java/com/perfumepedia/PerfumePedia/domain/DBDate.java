package com.perfumepedia.PerfumePedia.domain;


import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Embeddable;

import java.sql.Date;

@Embeddable
@Access(AccessType.FIELD)
public class DBDate {
    final private Date createdAt;
    private Date updatedAt;

    public DBDate(){
        createdAt = makeSqlDate();
        updatedAt = makeSqlDate();
    }

    /**
     * Java.util.Date를 이용해 현재 시간으로 SQL DATE 생성
     * @return a {@code java.sql.Date} object representing the given date
     */
    private Date makeSqlDate(){
        return new Date(new java.util.Date().getTime());
    }

    // get
    public Date getCreatedAt(){
        return createdAt;
    }

    // get
    public Date getUpdatedAt(){
        return updatedAt;
    }

    // set
    public void setUpdatedAt() {
        this.updatedAt = makeSqlDate();
    }
}
