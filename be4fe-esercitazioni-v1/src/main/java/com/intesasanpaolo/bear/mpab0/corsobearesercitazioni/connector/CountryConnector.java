package com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.connector;

import com.intesasanpaolo.bear.connector.bear.BearInternalConnector;
import com.intesasanpaolo.bear.connector.rest.transformer.IRestRequestTransformer;
import com.intesasanpaolo.bear.connector.rest.transformer.IRestResponseTransformer;
import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.model.CountryModel;
import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.resource.CountryResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CountryConnector extends BearInternalConnector<String, CountryResource,Long,CountryModel > {

}
