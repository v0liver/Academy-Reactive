package com.intesasanpaolo.bear.mpab0.corsobearesercizi.factory;

import com.intesasanpaolo.bear.mpab0.corsobearesercizi.model.CountryModel;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.resource.CountryResource;
import org.springframework.stereotype.Component;

@Component
public class CountryFactory {

    public CountryResource fromModelToResource(CountryModel countryModel){
        String[] infoSplit = countryModel.getInfo().split("-");
        CountryResource countryResource = new CountryResource();
        countryResource.setChiave(countryModel.getId());
        countryResource.setName(infoSplit[0]);
        countryResource.setLanguage(infoSplit[1]);
        countryResource.setContinent(infoSplit[2]);
        return  countryResource;
    }
}
