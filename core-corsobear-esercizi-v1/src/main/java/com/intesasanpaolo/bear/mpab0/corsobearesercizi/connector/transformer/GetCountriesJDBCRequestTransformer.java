package com.intesasanpaolo.bear.mpab0.corsobearesercizi.connector.transformer;

import com.intesasanpaolo.bear.connector.jdbc.JDBCQueryType;
import com.intesasanpaolo.bear.connector.jdbc.request.JDBCRequest;
import com.intesasanpaolo.bear.connector.jdbc.transformer.IJDBCRequestTransformer;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.model.CountryModel;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

@Service
public class GetCountriesJDBCRequestTransformer implements IJDBCRequestTransformer<String,Void> {
    @Override
    public JDBCRequest<Void> transform(String query, Object... args) {
        JDBCRequest<Void> jdbcRequest = new JDBCRequest<>();
        jdbcRequest.setQuery(query);
        jdbcRequest.setRowMapper(new BeanPropertyRowMapper(CountryModel.class));
        jdbcRequest.setType((JDBCQueryType) args[0]);
        return jdbcRequest;
    }
}
