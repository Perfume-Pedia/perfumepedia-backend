package com.perfumepedia.PerfumePedia.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@DynamicUpdate
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WORD_ID")
    private Long id;

    @Column(name = "ALIAS", nullable = false)
    private String alias;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "WEIGHT", nullable = false)
    private int weight;

    @Column(name = "WORD_TYPE")
    private WordType wordType;

    @ManyToOne()
    @JoinColumn(name = "BRAND_ID")
    private Brand brand;

    @ManyToOne()
    @JoinColumn(name = "PERFUME_ID")
    private Perfume perfume;

    @ManyToOne
    @JoinColumn(name = "NOTE_ID")
    private Note note;

    @Embedded
    @AttributeOverride(name = "createdAt", column = @Column(name = "CREATED_AT"))
    @AttributeOverride(name = "updatedAt", column = @Column(name = "UPDATED_AT"))
    private DBDate dbDate;

    /**
     * 생성자 - 매개변수 필수
     * <p> 향수 수집 데이터(perfume_main.json)을 이용해 word 객체를 생성하는 경우 사용
     * @param alias  String
     * @param name  String
     * @param wordType  WordType{BRAND, PERFUME, NOTE}
     */
    public Word(String alias, String name, WordType wordType){
        if (alias == null || alias.isEmpty()) {
            throw new IllegalArgumentException("alias 는 null 이거나 빈 값일 수 없습니다.");
        } else if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("name 은 null 이거나 빈 값일 수 없습니다.");
        } else if (wordType == null) {
            throw new IllegalArgumentException("wordType 은 null 일 수 없습니다.");
        }

        this.alias = alias;
        this.name = name;
        this.wordType = wordType;
        this.weight = 0;
    }

    /**
     * 생성자 - 매개변수 필수
     * <p> 별칭 데이터(perfume_alias.json)을 통해 word 객체를 생성하는 경우 사용
     * @param alias String
     * @param name String
     * @param baseWord Word: 기존 Database에 저장된 word객체 사용<p>
     *                 name이 같은 word객체를 baseWord로 사용
     */
    public Word(String alias, String name, Word baseWord){
        if (alias == null || alias.isEmpty()) {
            throw new IllegalArgumentException("alias 는 null 이거나 빈 값일 수 없습니다.");
        } else if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("name 은 null 이거나 빈 값일 수 없습니다.");
        } else if (baseWord == null){
            throw new IllegalArgumentException("baseWord 는 null 일 수 없습니다.");
        }

        this.alias = alias;
        this.name = name;
        this.wordType = baseWord.getWordType();
        this.weight = 0;
    }

    /**
     * Set 메소드 - DBDate
     * @param yearMonthDay String 년월일
     */
    public void setDbDate(String yearMonthDay){
        this.dbDate = new DBDate(yearMonthDay);
    }

    /**
     * 관계형 메소드 perfume 객체와 단방향 연결
     * @param perfume
     */
    public void setEntity(Perfume perfume){
        this.perfume = perfume;
        brand = null;
        note = null;
    }

    /**
     * 관계형 메소드 brand 객체와 단방향 연결
     * @param brand
     */
    public void setEntity(Brand brand){
        this.brand = brand;
        perfume = null;
        note = null;
    }

    /**
     * 관계형 메소드 note 객체와 단방향 연결
     * @param note
     */
    public void setEntity(Note note){
        this.note = note;
        perfume = null;
        brand = null;
    }

    /**
     * 가중치 증가함수
     */
    public void increaseWeight(){
        this.weight++;
    }

    /**
     * Get 메소드 <p>
     * id, alias, name, weight, type, brand, perfume, note, dbDate
     */
    public Long getId(){
        return id;
    }

    public String getAlias(){
        return alias;
    }

    public String getName(){
        return name;
    }

    public int getWeight(){
        return weight;
    }

    public WordType getWordType(){
        return wordType;
    }

    public Brand getBrand(){
        return brand;
    }

    public Perfume getPerfume(){
        return perfume;
    }

    public Note getNote(){
        return note;
    }

    public DBDate getDbDate() {
        return dbDate;
    }

    public Long getTypeId(){
        return Optional.ofNullable(brand).map(Brand::getId)
                .or(() -> Optional.ofNullable(perfume).map(Perfume::getId))
                .or(() -> Optional.ofNullable(note).map(Note::getId))
                .orElse(0L);
    }
}
