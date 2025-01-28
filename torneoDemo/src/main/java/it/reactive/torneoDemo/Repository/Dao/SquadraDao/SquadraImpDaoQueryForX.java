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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Profile(Costanti.TORNEO_DAO_SPRING_JDBC_QUERY_FOR_X)
public class SquadraImpDaoQueryForX implements SquadraDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public SquadraModel salvaSquadra(SquadraDTO squadraDTO) {
        String sql = "insert into squadra (nome, colori_sociali) values (?, ?)";
        try {
            jdbcTemplate.update(sql, squadraDTO.getNome(), squadraDTO.getColoriSociali());
        } catch (DuplicateKeyException e) {
            throw new SquadraDuplicataException();
        }
        String selectSql = "select * from squadra where nome = ? and colori_sociali = ?";
        return jdbcTemplate.queryForObject(selectSql, new SquadraRowMapper(), squadraDTO.getNome(), squadraDTO.getColoriSociali());
    }

    @Override
    public SquadraModel getSquadraById(int idSquadra) {
        String sql = "select * from squadra where id = :idsquadra";
        Map<String,Object> params = new HashMap<>();
        params.put("idSquadra",idSquadra);
//        SquadraModel squadraModel = Optional.ofNullable(namedParameterJdbcTemplate.queryForObject(sql,
//                params,new SquadraRowMapper())).orElseThrow(()->new SquadraNonPresenteException());
        //l optional non funziona perche queryForobject lancia un eccezione prima dell optional

        try {
           SquadraModel squadraModel = namedParameterJdbcTemplate.queryForObject(sql,
                    params,new SquadraRowMapper());
           squadraModel.setGiocatori(getGiocatoriBySquadraId(idSquadra));
           squadraModel.setTifoseria(getTifoseriaBySquadraId(idSquadra));
            return squadraModel;
        } catch (EmptyResultDataAccessException e) {
            throw new SquadraNonPresenteException();
        }
    }

    @Override
    public Set<GiocatoreModel> getGiocatoriBySquadraId(int idSquadra) {
        String sql = "select * from giocatore where id_squadra = ?";
        return new HashSet<>(jdbcTemplate.query(sql, new GiocatoreRowMapper(), idSquadra));
    }

    @Override
    public List<SquadraModel> ricercaSquadra(boolean completo) {
        String sql = "select * from squadra";


        List<Map<String, Object>> mappaDiSquadre = jdbcTemplate.queryForList(sql);


        List<SquadraModel> squadre = new ArrayList<>();
        for (Map<String, Object> squadra : mappaDiSquadre) {
            SquadraModel squadraModel = new SquadraModel();
            squadraModel.setNome((String) squadra.get("nome"));
            squadraModel.setColoriSociali((String) squadra.get("colori_sociali"));
            squadraModel.setIdSquadra((Integer) squadra.get("id"));
            squadraModel.setTifoseria(getTifoseriaBySquadraId(squadraModel.getIdSquadra()));
            squadraModel.setGiocatori(getGiocatoriBySquadraId(squadraModel.getIdSquadra()));
            squadre.add(squadraModel);
        }

        return squadre;
    }

    @Override
    public SquadraModel aggiungiGiocatore(int idSquadra, GiocatoreDto giocatoreDto) {
        String sql = "insert into giocatore (nome_cognome, id_squadra) values (?, ?)";
        try {
            jdbcTemplate.update(sql, giocatoreDto.getNomeCognome(), idSquadra);
        } catch (DuplicateKeyException e) {
            throw new GiocatoreDuplicatoException();
        }
        return getSquadraById(idSquadra);
    }

    @Override
    public SquadraModel aggiungiTifoseria(int idSquadra, TifoseriaDTO tifoseriaDTO) {
        try {

            TifoseriaModel tifoseriaModel = getTifoseriaBySquadraId(idSquadra);
            if (tifoseriaModel != null) {
                String updateSql = "update tifoseria set nome_tifoseria = :nomeTifoseria where id_squadra = :idSquadra";
                Map<String, Object> params = new HashMap<>();
                params.put("nomeTifoseria", tifoseriaDTO.getNomeTifoseria());
                params.put("idSquadra", idSquadra);
                namedParameterJdbcTemplate.update(updateSql, params);
            }else {
                String updateSql = "Insert into tifoseria  (nome_tifoseria,id_squadra) values (:nomeTifoseria," +
                        ":idSquadra)";
                Map<String, Object> params = new HashMap<>();
                params.put("nomeTifoseria", tifoseriaDTO.getNomeTifoseria());
                params.put("idSquadra", idSquadra);
                namedParameterJdbcTemplate.update(updateSql, params);
            }
        } catch (DuplicateKeyException e) {
            throw new TifoseriaGiaAssegnataException();
        }
        return getSquadraById(idSquadra);
    }

    @Override
    public void rimuoviSquadra(int idSquadra) {
        getSquadraById(idSquadra);
        String sql = "delete from squadra where id = ?";
        jdbcTemplate.update(sql, idSquadra);
    }

    @Override
    public TifoseriaModel getTifoseriaBySquadraId(int idSquadra) {
        String sql = "select * from tifoseria where id_squadra = :idSquadra";
        Map<String,Object> params = new HashMap<>();
        params.put("idSquadra",idSquadra);


        try {
            TifoseriaModel tifoseriaModel = namedParameterJdbcTemplate.queryForObject(sql,
                    params,new TifoseriaRowMapper());
            return tifoseriaModel;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
