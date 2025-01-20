package com.intesasanpaolo.bear.mpab0.corsobearesercizi.controller;
import com.intesasanpaolo.bear.core.controller.CoreController;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.command.*;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.factory.CountryFactory;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.model.CountryModel;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.resource.CountryResource;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "Country")
public class CountryController extends CoreController {
    @Autowired
    BeanFactory beanFactory;
    @Autowired
    CountryFactory countryFactory;


    //@GetMapping(value = "/countries")
    public ResponseEntity<List<CountryResource>> getCountriesCommand() throws BeansException,Exception {
        List<CountryResource> countryResourceList = new ArrayList<>();
        for (CountryModel countryModel : beanFactory.getBean(CountryCommand.class).execute()) {
            countryResourceList.add( countryFactory.fromModelToResource(countryModel));

        }

        return ResponseEntity.ok(countryResourceList);
    }

    //@GetMapping(value = "/countries")
    public ResponseEntity<List<CountryResource>> getCountriesService() throws BeansException,Exception {
        List<CountryResource> countryResourceList = new ArrayList<>();
        for (CountryModel countryModel : beanFactory.getBean(CountryCommandService.class).execute()) {
            countryResourceList.add( countryFactory.fromModelToResource(countryModel));

        }

        return ResponseEntity.ok(countryResourceList);
    }

   // @GetMapping(value = "/countries")
    public ResponseEntity<List<CountryResource>> getCountriesServiceParam(long id,String info) throws BeansException,Exception {
        List<CountryResource> countryResourceList = new ArrayList<>();
        for (CountryModel countryModel : beanFactory.getBean(CountryCommandServiceParam.class,id,info).execute()) {
            countryResourceList.add( countryFactory.fromModelToResource(countryModel));

        }

        return ResponseEntity.ok(countryResourceList);
    }

    @GetMapping(value = "/countries")
    public ResponseEntity<List<CountryResource>> getCountriesServiceJdbc() throws BeansException,
            Exception {
        List<CountryResource> countryResourceList = new ArrayList<>();
        for (CountryModel countryModel : beanFactory.getBean(CountryCommandJdbc.class).execute()) {
            countryResourceList.add( countryFactory.fromModelToResource(countryModel));

        }

        return ResponseEntity.ok(countryResourceList);
    }

    @PostMapping(value = "/country/{id}")
    public ResponseEntity<CountryResource> getCountryJpa(@PathVariable long id) throws BeansException,
            Exception {
       CountryResource countryResource = countryFactory.fromModelToResource(beanFactory.getBean(CountryCommandJpa.class,id).execute()) ;

        return ResponseEntity.ok(countryResource);
    }


    //@GetMapping(value = "/countries")
    public ResponseEntity<List<CountryResource>> getCountries(){
        CountryResource countryResource = new CountryResource();
        countryResource.setChiave(25);
        countryResource.setContinent("Asia");
        countryResource.setLanguage("Giapponese");
        countryResource.setName("Giappone");

        CountryResource countryResource2 = new CountryResource();
        countryResource2.setChiave(26);
        countryResource2.setContinent("America");
        countryResource2.setLanguage("Brasiliano");
        countryResource2.setName("Brasile");
        List<CountryResource> countryResourceList = new ArrayList<>();
        countryResourceList.add(countryResource);
        countryResourceList.add(countryResource2);

        return ResponseEntity.ok(countryResourceList);
    }
}
