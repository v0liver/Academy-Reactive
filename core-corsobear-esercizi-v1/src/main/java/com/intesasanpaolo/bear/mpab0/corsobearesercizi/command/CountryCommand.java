package com.intesasanpaolo.bear.mpab0.corsobearesercizi.command;

import com.intesasanpaolo.bear.core.command.BaseCommand;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.model.CountryModel;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CountryCommand extends BaseCommand<List<CountryModel>> {

    @Override
    protected List<CountryModel> doExecute() {
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
}
