package it.reactive.torneoDemo.Repository.Dao.SquadraDao;

import it.reactive.torneoDemo.DTO.giocatore.GiocatoreDto;
import it.reactive.torneoDemo.DTO.squadra.SquadraDTO;
import it.reactive.torneoDemo.DTO.tifoseria.TifoseriaDTO;
import it.reactive.torneoDemo.Repository.RowMapper.GiocatoreRowMapper;
import it.reactive.torneoDemo.Repository.RowMapper.SquadraRowMapper;
import it.reactive.torneoDemo.Repository.RowMapper.TifoseriaRowMapper;
import it.reactive.torneoDemo.Utility.Costanti;
import it.reactive.torneoDemo.exception.*;
import it.reactive.torneoDemo.model.GiocatoreModel;
import it.reactive.torneoDemo.model.SquadraModel;
import it.reactive.torneoDemo.model.TifoseriaModel;
import org.hibernate.loader.plan.exec.query.spi.NamedParameterContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.*;

@Repository
@Profile(Costanti.TORNEO_DAO_SPRING_JDBC_QUERY)
public class SquadraImpDaoQuery implements SquadraDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public SquadraModel salvaSquadra(SquadraDTO squadraDTO) {
        String sql = "insert into squadra (nome, colori_sociali) values (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int idSquadra = 0;
        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, squadraDTO.getNome());
                ps.setString(2, squadraDTO.getColoriSociali());
                return ps;
            }, keyHolder);

            idSquadra = (int) keyHolder.getKeyList().get(0).get("id");
        } catch (DuplicateKeyException e) {
            throw new SquadraDuplicataException();
        }
        return getSquadraById(idSquadra);
    }

    @Override
    public SquadraModel getSquadraById(int idSquadra) {
        String sql = "Select * from squadra where id=?";
        List<SquadraModel> squadraModelList = jdbcTemplate.query(sql, new SquadraRowMapper(), idSquadra
        );
        if (squadraModelList.isEmpty()) {
            throw new SquadraNonPresenteException();
        }
        SquadraModel squadraModel = squadraModelList.get(0);
        squadraModel.setGiocatori(getGiocatoriBySquadraId(squadraModel.getIdSquadra()));
        squadraModel.setTifoseria(getTifoseriaBySquadraId(idSquadra));
        return squadraModel;
    }

    @Override
    public Set<GiocatoreModel> getGiocatoriBySquadraId(int idSquadra) {
        String sql = "select * from giocatore where id_squadra=?";
        return new HashSet<>(jdbcTemplate.query(sql, new GiocatoreRowMapper(),
                idSquadra));
    }

    @Override
    public List<SquadraModel> ricercaSquadra(boolean completo) {
        String sql = "Select * from squadra";
        List<SquadraModel> squadraModelList = jdbcTemplate.query(sql, new SquadraRowMapper());
        for (SquadraModel squadraModel : squadraModelList) {
            Set<GiocatoreModel> giocatoreModelSet = getGiocatoriBySquadraId(squadraModel.getIdSquadra());
            squadraModel.setGiocatori(giocatoreModelSet);
        }
        return squadraModelList;
    }

    @Override
    public SquadraModel aggiungiGiocatore(int idSquadra, GiocatoreDto giocatoreDto) {
        String sql = "insert into giocatore (nome_cognome, id_squadra) values (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        final int idSQuadra = idSquadra;
        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, giocatoreDto.getNomeCognome());
                ps.setInt(2, idSQuadra);
                return ps;
            }, keyHolder);


        } catch (DuplicateKeyException e) {
            throw new GiocatoreDuplicatoException();
        }
        return getSquadraById(idSquadra);
    }


    @Override
    public SquadraModel aggiungiTifoseria(int idSquadra, TifoseriaDTO tifoseriaDTO) {
        try {
            if (getTifoseriaBySquadraId(idSquadra) != null) {
                String sql = "Update  tifoseria Set nome_tifoseria = :nomeTifoseria where id_squadra=:idSquadra";
                Map<String, Object> params = new HashMap<>();
                params.put("nomeTifoseria", tifoseriaDTO.getNomeTifoseria());
                params.put("idSquadra", idSquadra);
                namedParameterJdbcTemplate.update(sql, params);
            } else {
                String sql = "Insert into tifoseria (nome_tifoseria,id_squadra) values (:nomeTifoseria,:idSquadra)";
                Map<String, Object> params = new HashMap<>();
                params.put("nomeTifoseria", tifoseriaDTO.getNomeTifoseria());
                params.put("idSquadra", idSquadra);
                namedParameterJdbcTemplate.update(sql, params);
            }
        } catch (DuplicateKeyException e) {
            throw new TifoseriaGiaAssegnataException();
        }
        return getSquadraById(idSquadra);
    }

    @Override
    public void rimuoviSquadra(int idSquadra) {
        getSquadraById(idSquadra);
        String sql = "delete from giocatore where id_squadra = :idSquadra";
        Map<String, Object> params = new HashMap<>();
        params.put("idSquadra", idSquadra);
        namedParameterJdbcTemplate.update(sql, params);

        sql = "delete from tifoseria where id_squadra = :idSquadra";
        namedParameterJdbcTemplate.update(sql, params);

        sql = "delete from squadra where id = :idSquadra";
        namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public TifoseriaModel getTifoseriaBySquadraId(int idSquadra) {
        String sql = "Select * from tifoseria where id_squadra = ?";
        List<TifoseriaModel> tifoseriaModel = jdbcTemplate.query(sql, new TifoseriaRowMapper(), idSquadra);
        if (tifoseriaModel.isEmpty()) {
            return null;
        }
        return tifoseriaModel.get(0);
    }
}
