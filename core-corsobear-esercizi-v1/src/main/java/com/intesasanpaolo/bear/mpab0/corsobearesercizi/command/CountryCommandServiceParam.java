package com.intesasanpaolo.bear.mpab0.corsobearesercizi.command;

import com.intesasanpaolo.bear.core.command.BaseCommand;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.model.CountryModel;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CountryCommandServiceParam extends BaseCommand<List<CountryModel>> {
    private long id;
    private String info;

    public CountryCommandServiceParam(long id, String info) {
        this.id = id;
        this.info = info;
    }

    @Autowired
    CountryService countryService;

    @Override
    protected List<CountryModel> doExecute() {
      return countryService.getCountriesParam(id,info);
    }
}
