package it.reactive.torneoDemo.Repository.Dao.GiocatoreDao;

import it.reactive.torneoDemo.Utility.Costanti;
import it.reactive.torneoDemo.exception.GiocatoreNonPresenteException;
import it.reactive.torneoDemo.model.GiocatoreModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@Profile(Costanti.TORNEO_DAO_SPRING_JPA_ENTITY_MANAGER_BASE)
public class GiocatoreImpDaoJpaEntityManagerBase implements GiocatoreDao{
    @Autowired
    EntityManager entityManager;

    @Override
    public GiocatoreModel updateammonizioni(Integer idGiocatore) {
        GiocatoreModel giocatoreModel = getGiocatorebyId(idGiocatore);
        giocatoreModel.setNumeroAmmonizioni(giocatoreModel.getNumeroAmmonizioni()+1);
        entityManager.merge(giocatoreModel);
        return giocatoreModel;
    }

    @Override
    public GiocatoreModel getGiocatorebyId(Integer idGiocatore) {
        GiocatoreModel giocatoreModel= entityManager.find(GiocatoreModel.class,idGiocatore);
        if (giocatoreModel==null){
            throw new GiocatoreNonPresenteException();
        }
        return giocatoreModel;
    }
}
