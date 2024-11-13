package com.perfumepedia.PerfumePedia.domain.entity;


import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Embeddable;
import lombok.NoArgsConstructor;


import java.sql.Date;

@Embeddable
@Access(AccessType.FIELD)
@NoArgsConstructor(force = true)
public class DBDate {
    final private Date createdAt;
    private Date updatedAt;

    public DBDate(String yearMonthDay){
        createdAt = makeSqlDate(yearMonthDay);
        updatedAt = makeSqlDate(yearMonthDay);
    }

    /**
     * YYYY-MM-DD를 이용해 SQL DATE 생성
     * @return a {@code java.sql.Date} object representing the given date
     */
    private Date makeSqlDate(String yearMonthDay){
        return Date.valueOf(yearMonthDay);
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
    public void setUpdatedAt(Date date) {
        this.updatedAt = date;
    }
}
