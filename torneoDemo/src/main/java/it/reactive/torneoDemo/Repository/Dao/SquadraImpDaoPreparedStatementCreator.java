package it.reactive.torneoDemo.Repository.Dao;

import it.reactive.torneoDemo.DTO.giocatore.GiocatoreDto;
import it.reactive.torneoDemo.DTO.squadra.SquadraDTO;
import it.reactive.torneoDemo.DTO.tifoseria.TifoseriaDTO;
import it.reactive.torneoDemo.Utility.Costanti;
import it.reactive.torneoDemo.exception.GiocatoreDuplicatoException;
import it.reactive.torneoDemo.exception.SquadraDuplicataException;
import it.reactive.torneoDemo.exception.SquadraNonPresenteException;
import it.reactive.torneoDemo.model.GiocatoreModel;
import it.reactive.torneoDemo.model.SquadraModel;
import it.reactive.torneoDemo.model.TifoseriaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
@Profile(Costanti.TORNEO_DAO_SPRING_JDBC_QUERY_PSC)
public class SquadraImpDaoPreparedStatementCreator implements SquadraDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public SquadraModel salvaSquadra(SquadraDTO squadraDTO) {
        String query = "INSERT INTO squadra (nome, colori_sociali) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        try {
            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                    PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, squadraDTO.getNome());
                    ps.setString(2, squadraDTO.getColoriSociali());
                    return ps;
                }
            }, keyHolder);

            int idSquadra = (int) keyHolder.getKeyList().get(0).get("id");
            return getSquadraById(idSquadra);
        } catch (DuplicateKeyException e) {
            throw new SquadraDuplicataException();
        }
    }

    @Override
    public SquadraModel aggiungiGiocatore(int idSquadra, GiocatoreDto giocatoreDto) {
        String nomeCognome = giocatoreDto.getNomeCognome();
        String queryCheck = "SELECT * FROM giocatore WHERE nome_cognome = ?";

        boolean giocatoreEsistente = Boolean.TRUE.equals(jdbcTemplate.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(queryCheck);
                ps.setString(1, nomeCognome);
                return ps;
            }
        }, new ResultSetExtractor<Boolean>() {
            @Override
            public Boolean extractData(ResultSet rs) throws SQLException, DataAccessException {
                return rs.next();
            }
        }));

        if (giocatoreEsistente) {
            throw new GiocatoreDuplicatoException();
        }

        String queryInsert = "INSERT INTO giocatore (id_squadra, nome_cognome) VALUES (?, ?)";
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(queryInsert);
                ps.setInt(1, idSquadra);
                ps.setString(2, nomeCognome);
                return ps;
            }
        });

        return getSquadraById(idSquadra);
    }

    @Override
    public SquadraModel getSquadraById(int idSquadra) {
        String query = "SELECT * FROM squadra WHERE id = ?";
        return jdbcTemplate.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, idSquadra);
                return ps;
            }
        }, new ResultSetExtractor<SquadraModel>() {
            @Override
            public SquadraModel extractData(ResultSet rs) throws SQLException, DataAccessException {
                if (rs.next()) {
                    SquadraModel squadraModel = new SquadraModel();
                    squadraModel.setIdSquadra(idSquadra);
                    squadraModel.setNome(rs.getString("nome"));
                    squadraModel.setColoriSociali(rs.getString("colori_sociali"));
                    squadraModel.setTifoseria(getTifoseriaBySquadraId(idSquadra));
                    squadraModel.setGiocatori(getGiocatoriBySquadraId(idSquadra));
                    return squadraModel;
                }
                return null;
            }
        });
    }

    public Set<GiocatoreModel> getGiocatoriBySquadraId(int idSquadra) {
        String query = "SELECT g.id, g.nome_cognome FROM giocatore g JOIN squadra sq ON g.id_squadra = sq.id WHERE g.id_squadra = ?";
        return jdbcTemplate.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, idSquadra);
                return ps;
            }
        }, new ResultSetExtractor<Set<GiocatoreModel>>() {
            @Override
            public Set<GiocatoreModel> extractData(ResultSet rs) throws SQLException, DataAccessException {
                Set<GiocatoreModel> giocatori = new HashSet<>();
                while (rs.next()) {
                    GiocatoreModel giocatoreModel = new GiocatoreModel();
                    giocatoreModel.setIdGiocatore(rs.getInt("id"));
                    giocatoreModel.setNomeCognome(rs.getString("nome_cognome"));
                    giocatori.add(giocatoreModel);
                }
                return giocatori;
            }
        });
    }

    @Override
    public TifoseriaModel getTifoseriaBySquadraId(int idSquadra) {
        String query = "SELECT * FROM tifoseria WHERE id_squadra = ?";
        return jdbcTemplate.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, idSquadra);
                return ps;
            }
        }, new ResultSetExtractor<TifoseriaModel>() {
            @Override
            public TifoseriaModel extractData(ResultSet rs) throws SQLException, DataAccessException {
                if (rs.next()) {
                    TifoseriaModel tifoseriaModel = new TifoseriaModel();
                    tifoseriaModel.setIdTifoseria(rs.getInt("id"));
                    tifoseriaModel.setNomeTifoseria(rs.getString("nome_tifoseria"));
                    return tifoseriaModel;
                }
                return null;
            }
        });
    }

    @Override
    public void rimuoviSquadra(int idSquadra) {
        String queryDeleteGiocatori = "DELETE FROM giocatore WHERE id_squadra = ?";
        jdbcTemplate.update(queryDeleteGiocatori, idSquadra);

        String queryDeleteTifoseria = "DELETE FROM tifoseria WHERE id_squadra = ?";
        jdbcTemplate.update(queryDeleteTifoseria, idSquadra);

        String queryDeleteSquadra = "DELETE FROM squadra WHERE id = ?";
        int nRow = jdbcTemplate.update(queryDeleteSquadra, idSquadra);
        if (nRow == 0) {
            throw new SquadraNonPresenteException();
        }
    }

    @Override
    public List<SquadraModel> ricercaSquadra(boolean completo) {
        String query = "SELECT * FROM squadra";
        return jdbcTemplate.query(query, new ResultSetExtractor<List<SquadraModel>>() {
            @Override
            public List<SquadraModel> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<SquadraModel> listSquadraModel = new ArrayList<>();
                while (rs.next()) {
                    int idSquadra = rs.getInt("id");
                    SquadraModel squadraModel = new SquadraModel();
                    squadraModel.setIdSquadra(idSquadra);
                    squadraModel.setNome(rs.getString("nome"));
                    squadraModel.setColoriSociali(rs.getString("colori_sociali"));
                    if (completo) {
                        squadraModel.setGiocatori(getGiocatoriBySquadraId(idSquadra));
                    }
                    listSquadraModel.add(squadraModel);
                }
                return listSquadraModel;
            }
        });
    }

    @Override
    public SquadraModel aggiungiTifoseria(int idSquadra, TifoseriaDTO tifoseriaDTO) {
        String nomeTifoseria = tifoseriaDTO.getNomeTifoseria();
        String queryCheck = "SELECT * FROM tifoseria WHERE nome_tifoseria = ? AND id_squadra = ?";

        boolean tifoseriaEsistente = Boolean.TRUE.equals(jdbcTemplate.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(queryCheck);
                ps.setString(1, nomeTifoseria);
                ps.setInt(2, idSquadra);
                return ps;
            }
        }, new ResultSetExtractor<Boolean>() {
            @Override
            public Boolean extractData(ResultSet rs) throws SQLException, DataAccessException {
                return rs.next();
            }
        }));

        if (tifoseriaEsistente) {
            throw new RuntimeException("Tifoseria già associata alla squadra");
        }

        String queryInsert = "INSERT INTO tifoseria (id_squadra, nome_tifoseria) VALUES (?, ?)";
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(queryInsert);
                ps.setInt(1, idSquadra);
                ps.setString(2, nomeTifoseria);
                return ps;
            }
        });

        return getSquadraById(idSquadra);
    }
}
