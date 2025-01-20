package com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.connector;

import com.intesasanpaolo.bear.event.BaseEventConnector;
import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.dto.MessaggioDTO;
import org.springframework.stereotype.Service;

@Service
public class CountryConnectorKafka extends BaseEventConnector<MessaggioDTO,Boolean,String,Void>{
}
