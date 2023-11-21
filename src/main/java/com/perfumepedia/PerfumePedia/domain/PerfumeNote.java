package com.perfumepedia.PerfumePedia.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PerfumeNote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PERFUME_NOTE_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PERFUME_ID")
    private Perfume perfume;

    @ManyToOne
    @JoinColumn(name = "NOTE_ID")
    private Note note;

    @Enumerated(EnumType.STRING)
    @Column(name = "NOTE_TYPE")
    private NoteType noteType;

    @Embedded
    @AttributeOverride(name = "createdAt", column = @Column(name = "CREATED_AT"))
    @AttributeOverride(name = "updatedAt", column = @Column(name = "UPDATED_AT"))
    private DBDate dbDate;

    /**
     * 생성자 - 매개변수 필수
     * @param noteType
     */
    public PerfumeNote(NoteType noteType){
        if(noteType == null){
            throw new IllegalArgumentException("noteType 은 null 일 수 없습니다.");
        }
        this.noteType = noteType;
    }

    /**
     * Set 메소드 <p>dbDate
     */
    public void setDbDate(String yearMonthDay){
        this.dbDate = new DBDate(yearMonthDay);
    }

    /**
     * 관계형 메소드 perfume 객체와 단방향 연결
     * @param perfume
     */
    public void setPerfume(Perfume perfume){
        this.perfume = perfume;
    }

    /**
     * 관계형 메소드 note 객체와 단방향 연결
     * @param note
     */
    public void setNote(Note note){
        this.note = note;
    }

    /**
     * Get 메소드 <p>
     * id, perfume, note, noteType, dbDate
     */
    public Long getId(){
        return id;
    }

    public Perfume getPerfume(){
        return perfume;
    }

    public Note getNote(){
        return note;
    }

    public NoteType getNoteType(){
        return noteType;
    }

    public DBDate getDbDate(){
        return dbDate;
    }
}
