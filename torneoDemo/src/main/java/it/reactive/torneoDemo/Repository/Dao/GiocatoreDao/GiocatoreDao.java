package it.reactive.torneoDemo.Repository.Dao.GiocatoreDao;

import it.reactive.torneoDemo.model.GiocatoreModel;


public interface GiocatoreDao {
    GiocatoreModel updateammonizioni(Integer idGiocatore);

    GiocatoreModel getGiocatorebyId(Integer idGiocatore);
}

