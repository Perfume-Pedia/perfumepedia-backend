package com.perfumepedia.PerfumePedia.domain.entity;


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

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "PRICE")
    private int price;

    @Column(name = "URL")
    private String url;

    @ManyToOne()
    @JoinColumn(name = "BRAND_ID")
    private Brand brand;

    @Embedded
    @AttributeOverride(name = "name", column = @Column(name = "IMAGE_NAME"))
    @AttributeOverride(name = "path", column = @Column(name = "IMAGE_PATH"))
    private Image image;

    @Embedded
    @AttributeOverride(name = "createdAt", column = @Column(name = "CREATED_AT"))
    @AttributeOverride(name = "updatedAt", column = @Column(name = "UPDATED_AT"))
    private DBDate dbDate;

    // 생성자
    public Perfume(String name){
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("name 은 null 이거나 빈 값일 수 없습니다.");
        }
        this.name = name;
    }

    /**
     * Set 메소드<p>
     * price, url, image, dbDate, discontinue
     */
    public void setPrice(int price){
        this.price = price;
    }

    public void setUrl(String url){
        this.url = url;
    }

    public void setImage(String name, String path){
        this.image = new Image(name, path);
    }

    public void setDbDate(String yearMonthDay){
        this.dbDate = new DBDate(yearMonthDay);
    }



    /**
     * 관계형 메소드 brand 객체와 단방향 연결
     * @param brand
     */
    public void setBrand(Brand brand) {
        this.brand = brand;
    }


    /**
     * Get 메소드<p>
     * id, name, price, url,
     * brand, image, dbDate, discontinue
     */
    public Long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public int getPrice(){
        return price;
    }

    public String getUrl(){
        return url;
    }

    public Brand getBrand() {
        return brand;
    }

    public Image getImage(){
        return image;
    }

    public DBDate getDbDate(){
        return dbDate;
    }

}
