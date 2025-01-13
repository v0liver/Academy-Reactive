package it.reactive.torneoDemo.Repository.Dao.GiocatoreDao;

import it.reactive.torneoDemo.Utility.Costanti;
import it.reactive.torneoDemo.model.GiocatoreModel;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile(Costanti.TORNEO_DAO_SPRING_JDBC_QUERY_PSC)
public class GiocatoreImpDaoPreparedStatementCreator implements GiocatoreDao{
    @Override
    public GiocatoreModel updateammonizioni(Integer idGiocatore) {
        return null;
    }

    @Override
    public GiocatoreModel getGiocatorebyId(Integer idGiocatore) {
        return null;
    }
}
