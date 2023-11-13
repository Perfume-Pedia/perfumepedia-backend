package com.perfumepedia.PerfumePedia.domain;


import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Embeddable;

import java.sql.Date;

@Embeddable
@Access(AccessType.FIELD)
public class DBDate {
    private Date created_at;
    private Date updated_at;

    public DBDate(){
        created_at = makeSqlDate();
        updated_at = makeSqlDate();
    }

    private Date makeSqlDate(){
        return new Date(new java.util.Date().getTime());
    }

    public Date getCreated_at(){
        return created_at;
    }

    public Date getUpdated_at(){
        return updated_at;
    }
}
