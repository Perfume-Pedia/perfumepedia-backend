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

    // 생성자
    public Perfume(String name){
        this.name = name;
    }


    /**
     * Set 메소드<p>
     * price, url, brand
     */
    public void setPrice(int price){
        this.price = price;
    }

    public void setUrl(String url){
        this.url = url;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    /**
     * Get 메소드<p>
     * id, name, price, url,
     * brand
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
}
