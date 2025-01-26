package it.reactive.torneoDemo.Repository.Dao.TorneoDao;

import it.reactive.torneoDemo.DTO.torneo.TorneoDTO;
import it.reactive.torneoDemo.Utility.Costanti;
import it.reactive.torneoDemo.exception.SquadraDuplicataException;
import it.reactive.torneoDemo.exception.TorneoNonTrovatoException;
import it.reactive.torneoDemo.model.SquadraModel;
import it.reactive.torneoDemo.model.TorneoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Profile(Costanti.TORNEO_DAO_SPRING_JDBC_QUERY)
public class TorneoImpDaoQuery implements TorneoDao{
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public TorneoModel aggiungiTorneo(TorneoDTO torneoDTO) {
        String query = "insert into torneo (nome_torneo) values (:nomeTorneo) returning id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("nomeTorneo", torneoDTO.getNomeTorneo());

        Integer idTorneo = jdbcTemplate.queryForObject(query, params, Integer.class);
        if (idTorneo == null) {
            throw new RuntimeException("Errore durante l'inserimento del torneo.");
        }
        return getTorneoById(idTorneo);
    }

    @Override
    public TorneoModel censitaSquadraAlTorneo(Integer idTorneo, Integer idSquadra) {
        String query = "insert into squadra_torneo (id_squadra, id_torneo) values (:idSquadra, :idTorneo)";
        Map<String, Object> params = new HashMap<>();
        params.put("idSquadra", idSquadra);
        params.put("idTorneo", idTorneo);

        try {
            jdbcTemplate.update(query, params);
        } catch (Exception e) {
            throw new SquadraDuplicataException();
        }
        return getTorneoById(idTorneo);
    }

    @Override
    public List<TorneoModel> getTorneoEndSquadre() {
        String query = "select * from torneo";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(TorneoModel.class));
    }

    @Override
    public List<TorneoModel> eliminaTorneoConSquadreAndGiocatori(int idTorneo) {
        String deleteTorneoQuery = "delete from torneo where id = :idTorneo";
        Map<String, Object> params = new HashMap<>();
        params.put("idTorneo", idTorneo);

        int rowsAffected = jdbcTemplate.update(deleteTorneoQuery, params);
        if (rowsAffected == 0) {
            throw new TorneoNonTrovatoException();
        }
        return getTorneoEndSquadre();
    }

    @Override
    public TorneoModel getTorneoById(Integer idTorneo) {
        String query = "select * from torneo where id = :idTorneo";
        Map<String, Object> params = new HashMap<>();
        params.put("idTorneo", idTorneo);

        return jdbcTemplate.queryForObject(query, params, new BeanPropertyRowMapper<>(TorneoModel.class));
    }

    @Override
    public List<SquadraModel> getSquadreByIdTorneo(Integer idTorneo) {
        String query = "select s.nome, s.id from squadra_torneo st join squadra s on s.id = st.id_squadra where st.id_torneo = :idTorneo";
        Map<String, Object> params = new HashMap<>();
        params.put("idTorneo", idTorneo);

        return jdbcTemplate.query(query, params, new BeanPropertyRowMapper<>(SquadraModel.class));
    }

    @Override
    public List<TorneoModel> getTorneiByIdSquadra(Integer idSquadra) {
        String query = "select t.nome_torneo, t.id from squadra_torneo st join torneo t on t.id = st.id_torneo where st.id_squadra = :idSquadra";
        Map<String, Object> params = new HashMap<>();
        params.put("idSquadra", idSquadra);

        return jdbcTemplate.query(query, params, new BeanPropertyRowMapper<>(TorneoModel.class));
    }
}
