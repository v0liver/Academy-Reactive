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
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DataSourceUtils;
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
@Profile(Costanti.TORNEO_DAO_JDBC_PREPAREDSTATEMENT)
public class TorneoImpDaoPreparedStatement implements TorneoDao {
    @Autowired
    DataSource dataSource;
    @Autowired
    PlatformTransactionManager transactionManager;
    @Autowired
    SquadraDao squadraDao;

    @Override
    public TorneoModel aggiungiTorneo(TorneoDTO torneoDTO) {
        String nomeTorneo = torneoDTO.getNomeTorneo();
        String query = "INSERT INTO torneo (nome_torneo) VALUES (?)";

        Connection con = null;
        try {
            con = DataSourceUtils.getConnection(((DataSourceTransactionManager) transactionManager).getDataSource());
            PreparedStatement ps = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, nomeTorneo);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int idTorneo = rs.getInt(1);
            return getTorneoById(idTorneo);

        } catch (SQLException e) {
            if ("23505".equals(e.getSQLState())) {
                throw new TorneoDuplicatoException();
            } else {
                throw new RuntimeException(e);
            }
        } finally {
            if (con != null) {
                DataSourceUtils.releaseConnection(con, ((DataSourceTransactionManager) transactionManager).getDataSource());
            }
        }
    }

    @Override
    public TorneoModel censitaSquadraAlTorneo(Integer idTorneo, Integer idSquadra) {
        String query = "INSERT INTO squadra_torneo (id_squadra, id_torneo) VALUES (?, ?)";

        Connection con = null;
        try {
            con = DataSourceUtils.getConnection(((DataSourceTransactionManager) transactionManager).getDataSource());
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, idSquadra);
            ps.setInt(2, idTorneo);
            ps.executeUpdate();
            return getTorneoById(idTorneo);
        } catch (SQLException e) {
            if ("23505".equals(e.getSQLState())) {
                throw new SquadraDuplicataException();
            } else {
                throw new RuntimeException(e);
            }
        } finally {
            if (con != null) {
                DataSourceUtils.releaseConnection(con, ((DataSourceTransactionManager) transactionManager).getDataSource());
            }
        }
    }

    @Override
    public List<TorneoModel> getTorneoEndSquadre() {
        String query = "SELECT * FROM torneo";
        Connection con = null;
        List<TorneoModel> torneoModels = new ArrayList<>();
        try {
            con = DataSourceUtils.getConnection(((DataSourceTransactionManager) transactionManager).getDataSource());
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String nome = rs.getString("nome_torneo");
                int idTorneo = rs.getInt("id");
                TorneoModel torneoModel = new TorneoModel();
                torneoModel.setIdTorneo(idTorneo);
                torneoModel.setNomeTorneo(nome);
                torneoModel.setSquadre(getSquadreByIdTorneo(idTorneo));
                torneoModels.add(torneoModel);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (con != null) {
                DataSourceUtils.releaseConnection(con, ((DataSourceTransactionManager) transactionManager).getDataSource());
            }
        }
        return torneoModels;
    }

    @Override
    public List<TorneoModel> eliminaTorneoConSquadreAndGiocatori(int idTorneo) {
        List<SquadraModel> squadraModelList = getSquadreByIdTorneo(idTorneo);
        String queryDeleteTorneo = "DELETE FROM torneo WHERE id = ?";
        Connection con = null;
        try {
            con = DataSourceUtils.getConnection(((DataSourceTransactionManager) transactionManager).getDataSource());

            if (squadraModelList.isEmpty()) {
                PreparedStatement ps = con.prepareStatement(queryDeleteTorneo);
                ps.setInt(1, idTorneo);
                int nRow = ps.executeUpdate();
                if (nRow == 0) {
                    throw new TorneoNonTrovatoException();
                }
                return getTorneoEndSquadre();
            } else if (squadraModelList.size() == 1) {
                SquadraModel squadraModel = squadraModelList.get(0);
                if (getTorneiByIdSquadra(squadraModel.getIdSquadra()).size() > 1) {
                    throw new TorneoConPiuSquadreException();
                } else {
                    con.setAutoCommit(false);
                    try {
                        PreparedStatement ps = con.prepareStatement("DELETE FROM squadra_torneo WHERE id_squadra = ?");
                        ps.setInt(1, squadraModel.getIdSquadra());
                        ps.executeUpdate();

                        squadraDao.rimuoviSquadra(squadraModel.getIdSquadra());

                        ps = con.prepareStatement(queryDeleteTorneo);
                        ps.setInt(1, idTorneo);
                        int nRow = ps.executeUpdate();
                        if (nRow == 0) {
                            throw new TorneoNonTrovatoException();
                        }
                        con.commit();
                        return getTorneoEndSquadre();
                    } catch (SQLException e) {
                        con.rollback();
                        throw e;
                    }
                }
            } else {
                throw new TorneoConPiuSquadreException();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (con != null) {
                DataSourceUtils.releaseConnection(con, ((DataSourceTransactionManager) transactionManager).getDataSource());
            }
        }
    }

    @Override
    public TorneoModel getTorneoById(Integer idTorneo) {
        String query = "SELECT * FROM torneo WHERE id = ?";
        Connection con = null;
        try {
            con = DataSourceUtils.getConnection(((DataSourceTransactionManager) transactionManager).getDataSource());
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, idTorneo);
            ResultSet rs = ps.executeQuery();
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (con != null) {
                DataSourceUtils.releaseConnection(con, ((DataSourceTransactionManager) transactionManager).getDataSource());
            }
        }
    }

    @Override
    public List<SquadraModel> getSquadreByIdTorneo(Integer idTorneo) {
        String query = "SELECT s.nome, s.id FROM squadra_torneo st JOIN squadra s ON s.id = st.id_squadra WHERE st.id_torneo = ?";
        Connection con = null;
        List<SquadraModel> squadraModelList = new ArrayList<>();
        try {
            con = DataSourceUtils.getConnection(((DataSourceTransactionManager) transactionManager).getDataSource());
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, idTorneo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SquadraModel squadraModel = new SquadraModel();
                squadraModel.setNome(rs.getString("nome"));
                squadraModel.setIdSquadra(rs.getInt("id"));
                squadraModelList.add(squadraModel);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (con != null) {
                DataSourceUtils.releaseConnection(con, ((DataSourceTransactionManager) transactionManager).getDataSource());
            }
        }
        return squadraModelList;
    }

    @Override
    public List<TorneoModel> getTorneiByIdSquadra(Integer idSquadra) {
        String query = "SELECT t.nome_torneo, t.id FROM squadra_torneo st JOIN torneo t ON t.id = st.id_torneo WHERE st.id_squadra = ?";
        Connection con = null;
        List<TorneoModel> torneoModelList = new ArrayList<>();
        try {
            con = DataSourceUtils.getConnection(((DataSourceTransactionManager) transactionManager).getDataSource());
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, idSquadra);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TorneoModel torneoModel = new TorneoModel();
                torneoModel.setNomeTorneo(rs.getString("nome_torneo"));
                torneoModel.setIdTorneo(rs.getInt("id"));
                torneoModelList.add(torneoModel);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (con != null) {
                DataSourceUtils.releaseConnection(con, ((DataSourceTransactionManager) transactionManager).getDataSource());
            }
        }
        return torneoModelList;
    }
}
