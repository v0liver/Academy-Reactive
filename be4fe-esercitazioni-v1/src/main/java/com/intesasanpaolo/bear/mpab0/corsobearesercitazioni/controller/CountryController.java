package com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.controller;

import com.intesasanpaolo.bear.core.controller.CoreController;
import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.command.CountryCommand;
import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.resource.CountryResource;
import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.service.CountryService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "country")
public class CountryController extends CoreController {
    @Autowired
    BeanFactory beanFactory;

    @GetMapping("/{id}")
    public ResponseEntity<CountryResource> getCountryJpa(String id) throws BeansException,
            Exception {


        return ResponseEntity.ok(beanFactory.getBean(CountryCommand.class,id).execute());
    }
}
