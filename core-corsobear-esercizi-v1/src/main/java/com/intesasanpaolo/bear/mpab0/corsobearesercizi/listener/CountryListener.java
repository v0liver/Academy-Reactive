package com.intesasanpaolo.bear.mpab0.corsobearesercizi.listener;

import com.intesasanpaolo.bear.eventlistener.BaseEventListener;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.command.CountryCommandJdbc;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.dto.CountryLanguageDTO;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.factory.CountryFactory;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.model.CountryModel;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.resource.CountryResource;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.service.CountryService;
import org.apache.kafka.common.header.Headers;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CountryListener extends BaseEventListener {
    @Value("${KAFKA_TOPIC_DEMO}")
    private String TOPIC;
    @Autowired
    CountryService countryService;
    @Autowired
    BeanFactory beanFactory;
    @Autowired
    CountryFactory countryFactory;

    @Override
    public void onReceived(byte[] payload, Headers headers) {
        List<CountryResource> countryResourceList = new ArrayList<>();
        String lingua;
        try {
            for (CountryModel countryModel : beanFactory.getBean(CountryCommandJdbc.class).execute()) {
                countryResourceList.add( countryFactory.fromModelToResource(countryModel));

            }
            JsonDeserializer<CountryLanguageDTO> js = new JsonDeserializer<>(CountryLanguageDTO.class);
            CountryLanguageDTO pr = js.deserialize(TOPIC,headers, payload);
             lingua = pr.getMessaggio();
             js.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        for (CountryResource countryResource : countryResourceList) {
            if (countryResource.getLanguage().equals(lingua)){
                logger.info("Country con lingua "+new String(payload)+": "+countryResource.toString());
            }

        }
    }
}
