package it.reactive.torneoDemo.Repository.Dao.GiocatoreDao;

import it.reactive.torneoDemo.Repository.RowMapper.GiocatoreRowMapper;
import it.reactive.torneoDemo.Repository.RowMapper.SquadraRowMapper;
import it.reactive.torneoDemo.Utility.Costanti;
import it.reactive.torneoDemo.exception.GiocatoreNonPresenteException;
import it.reactive.torneoDemo.exception.SquadraNonPresenteException;
import it.reactive.torneoDemo.model.GiocatoreModel;
import it.reactive.torneoDemo.model.SquadraModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
@Profile(Costanti.TORNEO_DAO_SPRING_JDBC_QUERY)
public class GiocatoreImpDaoQuery implements GiocatoreDao{
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Override
    public GiocatoreModel updateammonizioni(Integer idGiocatore) {

        GiocatoreModel giocatoreModel = getGiocatorebyId(idGiocatore);
        Map<String,Object> params = new HashMap<>();
        int ammonizioni=giocatoreModel.getNumeroAmmonizioni();
        giocatoreModel.setNumeroAmmonizioni(++ammonizioni);
        String updateSql = "UPDATE giocatore SET numero_ammonizioni = :numeroAmmonizioni WHERE id = :idGiocatore";
        params.put("numeroAmmonizioni",ammonizioni);
        namedParameterJdbcTemplate.update(updateSql, params);
        return giocatoreModel;
    }

    @Override
    public GiocatoreModel getGiocatorebyId(Integer idGiocatore) {
        String sql = "Select * from giocatore where id=:idGiocatore";
        Map<String,Object> params=new HashMap<>();
        params.put("idGiocatore",idGiocatore);
        List<GiocatoreModel> giocatoreModelList = namedParameterJdbcTemplate.query(sql,params, new GiocatoreRowMapper()
        );
        if (giocatoreModelList.isEmpty()) {
            throw new GiocatoreNonPresenteException();
        }

        return giocatoreModelList.get(0);
    }
}
