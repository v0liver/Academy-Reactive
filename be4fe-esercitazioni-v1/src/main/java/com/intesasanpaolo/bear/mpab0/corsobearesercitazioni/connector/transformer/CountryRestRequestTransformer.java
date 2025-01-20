package com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.connector.transformer;

import com.intesasanpaolo.bear.connector.rest.model.RestConnectorRequest;
import com.intesasanpaolo.bear.connector.rest.transformer.IRestRequestTransformer;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CountryRestRequestTransformer implements IRestRequestTransformer<String,Long> {
    @Override
    public RestConnectorRequest<Long> transform(String om, Object... args) {
        RestConnectorRequest<Long> restConnectorRequest =
                new RestConnectorRequest<>();
        Map m = new HashMap<>();

            m.put("id",Long.valueOf(om));

        //restConnectorRequest.setQueryParams(m);
        restConnectorRequest.setParams(m);
//        restConnectorRequest.setRequest(Long.valueOf(om));
        return restConnectorRequest;
    }
}
