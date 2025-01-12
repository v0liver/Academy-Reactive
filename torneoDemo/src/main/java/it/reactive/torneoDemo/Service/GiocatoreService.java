package it.reactive.torneoDemo.Service;

import it.reactive.torneoDemo.Mapper.GiocatoreMapper;
import it.reactive.torneoDemo.Repository.Dao.GiocatoreDao.GiocatoreDao;
import it.reactive.torneoDemo.resource.GiocatoreResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GiocatoreService {
    @Autowired
    GiocatoreDao giocatoreDao;
    @Autowired
    GiocatoreMapper giocatoreMapper;

    @Transactional
    public GiocatoreResource aggiornaAmmonizione(Integer idGiocatore) {
        return giocatoreMapper.fromModelToResource(giocatoreDao.updateammonizioni(idGiocatore));
    }


}
