package com.uniyaz;

import java.io.Serializable;

public class Person implements Serializable {
    private Integer id;
    private String ad;
    private String soyad;
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
