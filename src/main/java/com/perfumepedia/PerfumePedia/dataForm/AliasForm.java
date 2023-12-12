package com.perfumepedia.PerfumePedia.dataForm;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AliasForm {

    private String alias;
    private String notename;


    @Override
    public String toString() {
        return "PerfumeAlias{" +
                "alias='" + alias + '\'' +
                ", note_name='" + notename + '\'' +
                '}';
    }
}
