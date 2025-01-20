package com.intesasanpaolo.bear.mpab0.corsobearesercizi.resource;

import com.intesasanpaolo.bear.core.resource.BaseResource;

public class CountryResource extends BaseResource {
   private Long chiave;
   private String name;
   private String language;
   private String continent;

    public long getChiave() {
        return chiave;
    }

    public void setChiave(long chiave) {
        this.chiave = chiave;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }
}
