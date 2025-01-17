package com.intesasanpaolo.bear.mpab0.corsobearesercizi.model;

import javax.persistence.*;

@Entity
@Table(name = "COUNTRIES")
public class CountryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;
    @Column
    private String info;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
