package com.intesasanpaolo.bear.mpab0.corsobearesercizi.connector.transformer;

import com.intesasanpaolo.bear.connector.jdbc.response.JDBCResponse;
import com.intesasanpaolo.bear.connector.jdbc.transformer.IJDBCRequestTransformer;
import com.intesasanpaolo.bear.connector.jdbc.transformer.IJDBCResponseTransformer;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.model.CountryModel;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class GetCountriesJDBCResponseTransformer implements IJDBCResponseTransformer<CountryModel, List<CountryModel>> {
    @Override
    public List<CountryModel> transform(JDBCResponse<CountryModel> jdbcResponse) {
        List<CountryModel> countryModelList = jdbcResponse.getResult();
        return countryModelList;
    }
}
