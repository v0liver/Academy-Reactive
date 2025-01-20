package com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.service;

import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.connector.CountryConnector;
import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.connector.transformer.CountryRestRequestTransformer;
import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.connector.transformer.CountryRestResponseTransformer;
import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.resource.CountryResource;
import com.intesasanpaolo.bear.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryService extends BaseService {
    @Autowired
    CountryRestRequestTransformer countryRestRequestTransformer;
    @Autowired
    CountryRestResponseTransformer countryRestResponseTransformer;
    @Autowired
    CountryConnector countryConnector;


    public CountryResource getCountryById(String id){
        CountryResource countryResource = countryConnector.call(id,countryRestRequestTransformer,
                countryRestResponseTransformer);
        return countryResource;
    }
}
