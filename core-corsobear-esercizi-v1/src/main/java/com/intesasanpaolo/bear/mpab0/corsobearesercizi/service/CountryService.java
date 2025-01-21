package com.intesasanpaolo.bear.mpab0.corsobearesercizi.service;

import com.intesasanpaolo.bear.connector.jdbc.JDBCQueryType;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.connector.CountryJpaRepositoryConnector;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.connector.GetCountriesjdbcConnector;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.connector.transformer.GetCountriesJDBCRequestTransformer;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.connector.transformer.GetCountriesJDBCResponseTransformer;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.exception.CountryNonTrovatoException;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.model.CountryModel;
import com.intesasanpaolo.bear.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CountryService extends BaseService {
    @Autowired
    GetCountriesjdbcConnector countriesjdbcConnector;
    @Autowired
    GetCountriesJDBCRequestTransformer getCountriesJDBCRequestTransformer;
    @Autowired
    GetCountriesJDBCResponseTransformer getCountriesJDBCResponseTransformer;
    @Autowired
    CountryJpaRepositoryConnector countryJpaRepositoryConnector;

    public List<CountryModel> getCountries(){
        List<CountryModel> countryModelList = new ArrayList<>();
        CountryModel countryModel = new CountryModel();
        countryModel.setId(25);
        countryModel.setInfo("Spagna-spagnolo-Europa");
        CountryModel countryModel2 = new CountryModel();
        countryModel2.setId(24);
        countryModel2.setInfo("Italia-italiano-Europa");
        countryModelList.add(countryModel2);
        countryModelList.add(countryModel);
        return countryModelList;
    }

    public List<CountryModel> getCountriesParam(long id,String info){
        List<CountryModel> countryModelList = new ArrayList<>();
        CountryModel countryModel = new CountryModel();
        countryModel.setId(25);
        countryModel.setInfo("Spagna-spagnolo-Europa");
        CountryModel countryModel2 = new CountryModel();
        countryModel2.setId(24);
        countryModel2.setInfo("Italia-italiano-Europa");
        CountryModel countryModel3 = new CountryModel();
        countryModel3.setInfo(info);
        countryModel3.setId(id);
        countryModelList.add(countryModel2);
        countryModelList.add(countryModel);
        countryModelList.add(countryModel3);
        return countryModelList;
    }

    public List<CountryModel> getCountriesJdbc(){
       List<CountryModel> countryModelList = countriesjdbcConnector.call("Select * from countries",getCountriesJDBCRequestTransformer,getCountriesJDBCResponseTransformer, JDBCQueryType.FIND);
       return countryModelList;
    }

    public CountryModel getCountryJpa(long id){
        CountryModel countryModel =
                countryJpaRepositoryConnector.findById(id).orElseThrow(()->new CountryNonTrovatoException());
        return countryModel;
    }

//    public List<CountryModel> getCountriesJdbcWithLanguage(String language){
//        List<CountryModel> countryModelList = countriesjdbcConnector.call("Select * from countries",getCountriesJDBCRequestTransformer,getCountriesJDBCResponseTransformer, JDBCQueryType.FIND);
//        List<CountryModel> countryModelListWithLanguage = new ArrayList<>();
//        for (CountryModel countryModel : countryModelList) {
//            if (countryModel.ge)
//        }
//        return countryModelList;
//    }

}
