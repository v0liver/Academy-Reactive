package it.reactive.torneoDemo.Repository.Dao.GiocatoreDao;

import it.reactive.torneoDemo.Repository.RowMapper.GiocatoreRowMapper;
import it.reactive.torneoDemo.Utility.Costanti;
import it.reactive.torneoDemo.exception.GiocatoreNonPresenteException;
import it.reactive.torneoDemo.model.GiocatoreModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
@Repository
@Profile(Costanti.TORNEO_DAO_SPRING_JDBC_QUERY_FOR_X)
public class GiocatoreImpDaoQueryForX implements GiocatoreDao{
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public GiocatoreModel updateammonizioni(Integer idGiocatore) {
        GiocatoreModel giocatoreModel = getGiocatorebyId(idGiocatore);
        Map<String,Object> params = new HashMap<>();
        int ammonizioni=giocatoreModel.getNumeroAmmonizioni();
        giocatoreModel.setNumeroAmmonizioni(++ammonizioni);
        String updateSql = "update giocatore set numero_ammonizioni = :numeroAmmonizioni where id = :idGiocatore";
        params.put("numeroAmmonizioni",ammonizioni);
        params.put("idGiocatore",idGiocatore);
        namedParameterJdbcTemplate.update(updateSql, params);
        return giocatoreModel;
    }

    @Override
    public GiocatoreModel getGiocatorebyId(Integer idGiocatore) {
        String sql = "Select * from giocatore where id=:idGiocatore";
        Map<String,Object> params = new HashMap<>();
        params.put("idGiocatore",idGiocatore);
        GiocatoreModel giocatoreModel = null;
        try {
            giocatoreModel = namedParameterJdbcTemplate.queryForObject(sql,
                    params,
                    new GiocatoreRowMapper());
        } catch (EmptyResultDataAccessException e) {
            throw new GiocatoreNonPresenteException();
        }


        return giocatoreModel;
    }
}
