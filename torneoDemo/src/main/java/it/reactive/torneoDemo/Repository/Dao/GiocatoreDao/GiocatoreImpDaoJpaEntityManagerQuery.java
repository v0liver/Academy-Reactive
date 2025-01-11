package it.reactive.torneoDemo.Repository.Dao.GiocatoreDao;

import it.reactive.torneoDemo.Utility.Costanti;
import it.reactive.torneoDemo.exception.GiocatoreNonPresenteException;
import it.reactive.torneoDemo.model.GiocatoreModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;
@Repository
@Profile(Costanti.TORNEO_DAO_SPRING_JPA_ENTITY_MANAGER_QUERY)
public class GiocatoreImpDaoJpaEntityManagerQuery implements GiocatoreDao{
    @Autowired
    EntityManager entityManager;
    @Override
    public GiocatoreModel updateammonizioni(Integer idGiocatore) {
        Optional<GiocatoreModel> giocatoreModel = Optional.ofNullable(getGiocatorebyId(idGiocatore));
        if (giocatoreModel.isPresent()) {
            giocatoreModel.get().setNumeroAmmonizioni(giocatoreModel.get().getNumeroAmmonizioni() + 1);
            return giocatoreModel.get();
        }else throw new GiocatoreNonPresenteException();
    }

    @Override
    public GiocatoreModel getGiocatorebyId(Integer idGiocatore) {
        return entityManager.find(GiocatoreModel.class,idGiocatore);
    }
}
