package com.intesasanpaolo.bear.mpab0.corsobearesercizi.connector;

import com.intesasanpaolo.bear.connector.jpa.JPAConnector;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.model.CountryModel;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public interface CountryJpaRepositoryConnector extends JPAConnector<CountryModel,Long> {
}
