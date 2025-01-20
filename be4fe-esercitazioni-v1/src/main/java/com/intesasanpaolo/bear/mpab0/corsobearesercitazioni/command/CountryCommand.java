package com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.command;

import com.intesasanpaolo.bear.core.command.BaseCommand;
import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.resource.CountryResource;
import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CountryCommand extends BaseCommand<CountryResource> {
    String id;

    public CountryCommand(String id) {
        this.id = id;
    }

    @Autowired
    CountryService countryService;


    @Override
    protected CountryResource doExecute() throws Exception {
        CountryResource countryResource = countryService.getCountryById(id);
        return countryResource;
    }
}
