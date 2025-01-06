package it.reactive.torneoDemo.Repository.Dao;

import it.reactive.torneoDemo.DTO.giocatore.GiocatoreDto;
import it.reactive.torneoDemo.DTO.squadra.SquadraDTO;
import it.reactive.torneoDemo.DTO.squadra.SquadreDiGiocatoriDTO;
import it.reactive.torneoDemo.DTO.tifoseria.TifoseriaDTO;
import it.reactive.torneoDemo.Utility.Costanti;
import it.reactive.torneoDemo.exception.SquadraDuplicataException;
import it.reactive.torneoDemo.model.SquadraModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Repository
@Profile(Costanti.TORNEO_DAO_SPRING_JDBC_QUERY_PSC)
public class SquadraImpDaoPreparedStatementCreator implements SquadraDao{
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public SquadraModel salvaSquadra(SquadraDTO squadraDTO) {
        String nomeSquadra = squadraDTO.getNome();
        String coloriSociali = squadraDTO.getColoriSociali();
        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps=con.prepareStatement("insert into squadra (nome,colori_sociali) values (?,?)");
                ps.setString(1,nomeSquadra);
                ps.setString(2,coloriSociali);
                return ps;
            }
        };
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int nRow=jdbcTemplate.update(psc,keyHolder);
        if (nRow == 1) {
            psc = new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                    PreparedStatement ps = con.prepareStatement("select * from squadra where nome=?");
                    ps.setString(1,nomeSquadra);
                    return ps;
                }
            };
            ResultSetExtractor<SquadraModel> rse = new ResultSetExtractor<SquadraModel>() {
                @Override
                public SquadraModel extractData(ResultSet rs) throws SQLException, DataAccessException {
                    rs.next();
                    int idSquadra = rs.getInt("id");
                    SquadraModel squadraModel = new SquadraModel();
                    squadraModel.setIdSquadra(idSquadra);
                    squadraModel.setNome(nomeSquadra);
                    squadraModel.setColoriSociali(coloriSociali);
                    return squadraModel;
                }
            };
            SquadraModel squadraModel = jdbcTemplate.query(psc,rse);
            return squadraModel;
        } else {
            throw new SquadraDuplicataException();
        }

    }

    @Override
    public SquadraModel salvaSquadraSquadraGiocatori(SquadreDiGiocatoriDTO squadreDiGiocatoriDTO) {
        return null;
    }

    @Override
    public List<SquadraModel> ricercaSquadra(boolean completo) {
        return Collections.emptyList();
    }

    @Override
    public SquadraModel aggiungiGiocatore(int id, GiocatoreDto giocatoreDto) {
        return null;
    }

    @Override
    public SquadraModel aggiungiTifoseria(int idSquadra, TifoseriaDTO tifoseriaDTO) {
        return null;
    }

    @Override
    public void rimuoviSquadra(int idSquadra) {

    }
}
