package it.reactive.torneoDemo.Repository.Dao.TorneoDao;

import it.reactive.torneoDemo.DTO.torneo.TorneoDTO;
import it.reactive.torneoDemo.Repository.Dao.SquadraDao.SquadraDao;
import it.reactive.torneoDemo.Utility.Costanti;
import it.reactive.torneoDemo.exception.*;
import it.reactive.torneoDemo.model.GiocatoreModel;
import it.reactive.torneoDemo.model.SquadraModel;
import it.reactive.torneoDemo.model.TorneoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
@Profile(Costanti.TORNEO_DAO_SPRING_JDBC_QUERY_PSC)
public class TorneoImpDaoPreparedStatementCreator implements TorneoDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    PlatformTransactionManager transactionManager;

    @Autowired
    SquadraDao squadraDao;

    @Override
    public TorneoModel aggiungiTorneo(TorneoDTO torneoDTO) {
        String nomeTorneo = torneoDTO.getNomeTorneo();
        String query = "insert into torneo (nome_torneo) values (?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        try {
            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                    PreparedStatement ps = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
                    ps.setString(1, nomeTorneo);
                    return ps;
                }
            }, keyHolder);

            int idTorneo = (int) keyHolder.getKeyList().get(0).get("id");
            return getTorneoById(idTorneo);
        } catch (DuplicateKeyException e) {
            throw new TorneoDuplicatoException();
        }
    }

    @Override
    public TorneoModel censitaSquadraAlTorneo(Integer idTorneo, Integer idSquadra) {
        String query = "insert into squadra_torneo (id_squadra, id_torneo) values (?, ?)";

        try {
            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                    PreparedStatement ps = con.prepareStatement(query);
                    ps.setInt(1, idSquadra);
                    ps.setInt(2, idTorneo);
                    return ps;
                }
            });
            return getTorneoById(idTorneo);
        } catch (DuplicateKeyException e) {
            throw new SquadraDuplicataException();
        }
    }

    @Override
    public List<TorneoModel> getTorneoEndSquadre() {
        String query = "select * from torneo";
        List<TorneoModel> torneoModels = new ArrayList<>();
        jdbcTemplate.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                return con.prepareStatement(query);
            }
        }, new ResultSetExtractor<List<TorneoModel>>() {
            @Override
            public List<TorneoModel> extractData(ResultSet rs) throws SQLException, DataAccessException {
                while (rs.next()) {
                    String nome = rs.getString("nome_torneo");
                    int idTorneo = rs.getInt("id");
                    TorneoModel torneoModel = new TorneoModel();
                    torneoModel.setIdTorneo(idTorneo);
                    torneoModel.setNomeTorneo(nome);
                    torneoModel.setSquadre(getSquadreByIdTorneo(idTorneo));
                    torneoModels.add(torneoModel);
                }
                return torneoModels;
            }
        });
        return torneoModels;
    }

    @Override
    public List<TorneoModel> eliminaTorneoConSquadreAndGiocatori(int idTorneo) {
        List<SquadraModel> squadraModelList = getSquadreByIdTorneo(idTorneo);
        String queryDeleteTorneo = "delete from torneo where id = ?";


        try {
            if (squadraModelList.isEmpty()) {
                jdbcTemplate.update(queryDeleteTorneo, idTorneo);
                return getTorneoEndSquadre();
            } else if (squadraModelList.size() == 1) {
                SquadraModel squadraModel = squadraModelList.get(0);
                if (getTorneiByIdSquadra(squadraModel.getIdSquadra()).size() > 1) {
                    throw new TorneoConPiuSquadreException();
                } else {
                    jdbcTemplate.update("delete from squadra_torneo where id_squadra = ?", squadraModel.getIdSquadra());
                    squadraDao.rimuoviSquadra(squadraModel.getIdSquadra());
                    jdbcTemplate.update(queryDeleteTorneo, idTorneo);
                    return getTorneoEndSquadre();
                }
            } else {
                throw new TorneoConPiuSquadreException();
            }
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public TorneoModel getTorneoById(Integer idTorneo) {
        String query = "select * from torneo where id = ?";
        return jdbcTemplate.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, idTorneo);
                return ps;
            }
        }, new ResultSetExtractor<TorneoModel>() {
            @Override
            public TorneoModel extractData(ResultSet rs) throws SQLException, DataAccessException {
                if (rs.next()) {
                    String nome = rs.getString("nome_torneo");
                    TorneoModel torneoModel = new TorneoModel();
                    torneoModel.setIdTorneo(idTorneo);
                    torneoModel.setNomeTorneo(nome);
                    torneoModel.setSquadre(getSquadreByIdTorneo(idTorneo));
                    return torneoModel;
                } else {
                    throw new TorneoNonTrovatoException();
                }
            }
        });
    }

    @Override
    public List<SquadraModel> getSquadreByIdTorneo(Integer idTorneo) {
        String query = "select s.nome, s.id from squadra_torneo st join squadra s on s.id = st.id_squadra where st.id_torneo = ?";
        List<SquadraModel> squadraModelList = new ArrayList<>();
        jdbcTemplate.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, idTorneo);
                return ps;
            }
        }, new ResultSetExtractor<List<SquadraModel>>() {
            @Override
            public List<SquadraModel> extractData(ResultSet rs) throws SQLException, DataAccessException {
                while (rs.next()) {
                    SquadraModel squadraModel = new SquadraModel();
                    squadraModel.setNome(rs.getString("nome"));
                    squadraModel.setIdSquadra(rs.getInt("id"));
                    squadraModelList.add(squadraModel);
                }
                return squadraModelList;
            }
        });
        return squadraModelList;
    }

    @Override
    public List<TorneoModel> getTorneiByIdSquadra(Integer idSquadra) {
        String query = "select t.nome_torneo, t.id from squadra_torneo st join torneo t on t.id = st.id_torneo where st.id_squadra = ?";
        List<TorneoModel> torneoModelList = new ArrayList<>();
        jdbcTemplate.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, idSquadra);
                return ps;
            }
        }, new ResultSetExtractor<List<TorneoModel>>() {
            @Override
            public List<TorneoModel> extractData(ResultSet rs) throws SQLException, DataAccessException {
                while (rs.next()) {
                    TorneoModel torneoModel = new TorneoModel();
                    torneoModel.setNomeTorneo(rs.getString("nome_torneo"));
                    torneoModel.setIdTorneo(rs.getInt("id"));
                    torneoModelList.add(torneoModel);
                }
                return torneoModelList;
            }
        });
        return torneoModelList;
    }
}
