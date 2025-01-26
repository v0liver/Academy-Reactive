package it.reactive.torneoDemo.Repository.Dao.TorneoDao;

import it.reactive.torneoDemo.DTO.torneo.TorneoDTO;
import it.reactive.torneoDemo.Utility.Costanti;
import it.reactive.torneoDemo.exception.SquadraDuplicataException;
import it.reactive.torneoDemo.exception.TorneoNonTrovatoException;
import it.reactive.torneoDemo.model.SquadraModel;
import it.reactive.torneoDemo.model.TorneoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Profile(Costanti.TORNEO_DAO_SPRING_JDBC_QUERY_FOR_X)
public class TorneoImpDaoQueryForX implements TorneoDao{
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public TorneoModel aggiungiTorneo(TorneoDTO torneoDTO) {
        String query = "insert into torneo (nome_torneo) values (:nomeTorneo) returning id, nome_torneo";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("nomeTorneo", torneoDTO.getNomeTorneo());

        Map<String, Object> result = jdbcTemplate.queryForMap(query, params);

        TorneoModel torneoModel = new TorneoModel();
        torneoModel.setIdTorneo((Integer) result.get("id"));
        torneoModel.setNomeTorneo((String) result.get("nome_torneo"));
        return torneoModel;
    }

    @Override
    public TorneoModel censitaSquadraAlTorneo(Integer idTorneo, Integer idSquadra) {
        String query = "insert into squadra_torneo (id_squadra, id_torneo) values (:idSquadra, :idTorneo)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idSquadra", idSquadra);
        params.addValue("idTorneo", idTorneo);

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
        return jdbcTemplate.queryForList(query, new MapSqlParameterSource(), TorneoModel.class);
    }

    @Override
    public List<TorneoModel> eliminaTorneoConSquadreAndGiocatori(int idTorneo) {
        String deleteTorneoQuery = "delete from torneo where id = :idTorneo";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idTorneo", idTorneo);

        int rowsAffected = jdbcTemplate.update(deleteTorneoQuery, params);
        if (rowsAffected == 0) {
            throw new TorneoNonTrovatoException();
        }
        return getTorneoEndSquadre();
    }

    @Override
    public TorneoModel getTorneoById(Integer idTorneo) {
        String query = "select * from torneo where id = :idTorneo";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idTorneo", idTorneo);

        return jdbcTemplate.queryForObject(query, params, (rs, rowNum) -> {
            TorneoModel torneo = new TorneoModel();
            torneo.setIdTorneo(rs.getInt("id"));
            torneo.setNomeTorneo(rs.getString("nome_torneo"));
            return torneo;
        });
    }

    @Override
    public List<SquadraModel> getSquadreByIdTorneo(Integer idTorneo) {
        String query = "select s.nome, s.id from squadra_torneo st join squadra s on s.id = st.id_squadra where st" +
                ".id_torneo = :idTorneo";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idTorneo", idTorneo);

        return jdbcTemplate.query(query, params, (rs, rowNum) -> {
            SquadraModel squadra = new SquadraModel();
            squadra.setIdSquadra(rs.getInt("id"));
            squadra.setNome(rs.getString("nome"));
            return squadra;
        });
    }

    @Override
    public List<TorneoModel> getTorneiByIdSquadra(Integer idSquadra) {
        String query = "select t.nome_torneo, t.id from squadra_torneo st join torneo t on t.id = st.id_torneo where " +
                "st.id_squadra = :idSquadra";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idSquadra", idSquadra);

        return jdbcTemplate.query(query, params, (rs, rowNum) -> {
            TorneoModel torneo = new TorneoModel();
            torneo.setIdTorneo(rs.getInt("id"));
            torneo.setNomeTorneo(rs.getString("nome_torneo"));
            return torneo;
        });
    }
}
