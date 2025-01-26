package it.reactive.torneoDemoMongo.repository;


import it.reactive.torneoDemoMongo.resource.TrasferimentiResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Repository
public class Trasferimenti {

    @Value("${urlTrasferimenti}")
    private String urlTrasferimenti;

    public Set<TrasferimentiResource> trasferimenti(String nome) {
        String url = urlTrasferimenti + nome;

        RestTemplate restTemplate = new RestTemplate();
        Set<TrasferimentiResource> trasferimentiSet = new HashSet<>();

        TrasferimentiResource[] trasferimentiArray = restTemplate.getForObject(url, TrasferimentiResource[].class);

        if (trasferimentiArray != null) {
            Collections.addAll(trasferimentiSet, trasferimentiArray);
        }

        return trasferimentiSet;
    }
}
