package com.intesasanpaolo.bear.mpab0.corsobearesercizi.listener;

import com.intesasanpaolo.bear.eventlistener.BaseEventListener;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.command.CountryCommandJdbc;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.factory.CountryFactory;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.model.CountryModel;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.resource.CountryResource;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.service.CountryService;
import org.apache.kafka.common.header.Headers;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CountryListener extends BaseEventListener {
    @Autowired
    CountryService countryService;
    @Autowired
    BeanFactory beanFactory;
    @Autowired
    CountryFactory countryFactory;

    @Override
    public void onReceived(byte[] payload, Headers headers) {
        List<CountryResource> countryResourceList = new ArrayList<>();
        try {
            for (CountryModel countryModel : beanFactory.getBean(CountryCommandJdbc.class).execute()) {
                countryResourceList.add( countryFactory.fromModelToResource(countryModel));

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        for (CountryResource countryResource : countryResourceList) {
            if (countryResource.getLanguage().equals(new String(payload))){
                logger.info("Country con lingua "+new String(payload)+": "+countryResource);
            }

        }
    }
}
