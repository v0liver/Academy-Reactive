package com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.connector.transformer;

import com.intesasanpaolo.bear.connector.rest.model.RestConnectorResponse;
import com.intesasanpaolo.bear.connector.rest.transformer.IRestResponseTransformer;
import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.model.CountryModel;
import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.resource.CountryResource;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class CountryRestResponseTransformer implements IRestResponseTransformer<CountryModel, CountryResource> {
    @Override
    public CountryResource transform(RestConnectorResponse<CountryModel> restConnectorResponse) {
        CountryResource countryResource = new CountryResource();
        CountryModel countryModel = restConnectorResponse.getResponse().getBody();
        countryResource.setChiave(String.valueOf(countryModel.getChiave()));
        countryResource.setLingua(countryModel.getLanguage());
        countryResource.setOraAggiornamento(Instant.now());
        return countryResource;
    }
}
