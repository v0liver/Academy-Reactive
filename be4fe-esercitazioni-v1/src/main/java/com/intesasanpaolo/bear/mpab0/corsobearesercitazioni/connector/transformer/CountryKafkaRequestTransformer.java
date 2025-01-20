package com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.connector.transformer;

import com.intesasanpaolo.bear.event.request.EventRequest;
import com.intesasanpaolo.bear.event.transformer.IEventRequestTransformer;
import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.dto.MessaggioDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Service;

@Service
public class CountryKafkaRequestTransformer implements IEventRequestTransformer<MessaggioDTO,String> {
    @Value("${KAFKA_TOPIC_DEMO}")
    private String TOPIC;

    @Override
    public EventRequest<String> transform(MessaggioDTO om, Object... args) {
        EventRequest<String> event = new EventRequest<>();
        JsonSerializer<MessaggioDTO> js = new JsonSerializer<>();
        event.setTopic(TOPIC);
        event.setPayload(js.serialize(TOPIC, om));
        js.close();
        return event;
    }
}
