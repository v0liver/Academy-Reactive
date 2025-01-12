package it.reactive.torneoDemo.Repository.Dao.GiocatoreDao;

import it.reactive.torneoDemo.Repository.JpaRepository.GiocatoreJpaRepository;
import it.reactive.torneoDemo.Utility.Costanti;
import it.reactive.torneoDemo.exception.GiocatoreNonPresenteException;
import it.reactive.torneoDemo.model.GiocatoreModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile(Costanti.TORNEO_DAO_SPRING_JDBC_QUERY)
public class GiocatoreImpDaoJpaRepository implements GiocatoreDao {
    @Autowired
    GiocatoreJpaRepository giocatoreJpaRepository;

    @Override
    public GiocatoreModel updateammonizioni(Integer idGiocatore) {
        GiocatoreModel giocatoreModel = getGiocatorebyId(idGiocatore);
        giocatoreModel.setNumeroAmmonizioni(giocatoreModel.getNumeroAmmonizioni() + 1);
        giocatoreJpaRepository.save(giocatoreModel);
        return giocatoreModel;
    }

    @Override
    public GiocatoreModel getGiocatorebyId(Integer idGiocatore) {
        return giocatoreJpaRepository.findById(idGiocatore).orElseThrow(() -> new GiocatoreNonPresenteException());
    }
}
