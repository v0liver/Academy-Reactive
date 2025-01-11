package it.reactive.torneoDemo.Repository.Dao;

import it.reactive.torneoDemo.Utility.Costanti;
import it.reactive.torneoDemo.model.GiocatoreModel;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;


public interface GiocatoreDao {
    GiocatoreModel updateammonizioni (Integer idGiocatore);
    GiocatoreModel getGiocatorebyId(Integer idGiocatore);
    }

