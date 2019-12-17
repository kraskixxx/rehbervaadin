package com.uniyaz.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PERSON")
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 150)
    private String ad;

    @Column(length = 150)
    private String soyad;

    @Column(length = 50)
    private String telNo;

    public Person() {

    }

    public Person(String ad, String soyad, String telNo) {
        this.ad = ad;
        this.soyad = soyad;
        this.telNo = telNo;
    }

    public Person(int id, String ad, String soyad, String telNo) {
        this.id = id;
        this.ad = ad;
        this.soyad = soyad;
        this.telNo = telNo;
    }

    @Override
    public String toString() {
        return this.getId() >0 ? this.getAd() : "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }
}
