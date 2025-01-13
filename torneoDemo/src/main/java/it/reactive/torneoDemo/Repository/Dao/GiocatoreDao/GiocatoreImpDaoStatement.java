package it.reactive.torneoDemo.Repository.Dao.GiocatoreDao;

import it.reactive.torneoDemo.Utility.Costanti;
import it.reactive.torneoDemo.model.GiocatoreModel;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;

@Repository
@Profile(Costanti.TORNEO_DAO_JDBC_STATEMENT)
public class GiocatoreImpDaoStatement implements GiocatoreDao{
    @Override
    public GiocatoreModel updateammonizioni(Integer idGiocatore) {
        return null;
    }

    @Override
    public GiocatoreModel getGiocatorebyId(Integer idGiocatore) {
        return null;
    }
}
