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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

@Repository
@Profile(Costanti.TORNEO_DAO_JDBC_STATEMENT)
public class TorneoImpDaoStatement implements TorneoDao {
    @Autowired
    DataSource dataSource;
    @Autowired
    PlatformTransactionManager transactionManager;
    @Autowired
    SquadraDao squadraDao;



    @Override
    public TorneoModel aggiungiTorneo(TorneoDTO torneoDTO) {
        String nomeTorneo = torneoDTO.getNomeTorneo();
        String query = "insert into torneo (nome_torneo) values ('" + nomeTorneo + "')";

        Connection con = null;
        try {
            con = DataSourceUtils.getConnection(((DataSourceTransactionManager) transactionManager).getDataSource());
            Statement st = con.createStatement();
            int nRow = st.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = st.getGeneratedKeys();
            rs.next();
            int idTorneo = rs.getInt(1);

            return getTorneoById(idTorneo);

        } catch (SQLException e) {
            if ("23505".equals(e.getSQLState())) {
                throw new TorneoDuplicatoException();
            } else throw new RuntimeException(e);
        } finally {
            if (con != null) {
                DataSourceUtils.releaseConnection(con, ((DataSourceTransactionManager) transactionManager).getDataSource());
            }
        }
    }


    @Override
    public TorneoModel censitaSquadraAlTorneo(Integer idTorneo, Integer idSquadra) {
        String query = "insert into squadra_torneo (id_squadra,id_torneo) values (" + idSquadra + "," + idTorneo + ")";

        Connection con = null;
        try {
            con = DataSourceUtils.getConnection(((DataSourceTransactionManager) transactionManager).getDataSource());
            Statement st = con.createStatement();
            st.executeUpdate(query);
            return getTorneoById(idTorneo);

        } catch (SQLException e) {
            if ("23505".equals(e.getSQLState())) {
                throw new SquadraDuplicataException();
            } else throw new RuntimeException(e);
        } finally {
            if (con != null) {
                DataSourceUtils.releaseConnection(con, ((DataSourceTransactionManager) transactionManager).getDataSource());
            }
        }
    }

    @Override
    public List<TorneoModel> getTorneoEndSquadre() {
        Connection con = null;
        List<TorneoModel> torneoModels = new ArrayList<>();
        try {
            con = DataSourceUtils.getConnection(((DataSourceTransactionManager) transactionManager).getDataSource());
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from torneo ");

            if (rs.next()) {
                String nome = rs.getString("nome_torneo");
                int idTorneo = rs.getInt("id");
                TorneoModel torneoModel = new TorneoModel();
                torneoModel.setIdTorneo(idTorneo);
                torneoModel.setNomeTorneo(nome);
                torneoModel.setSquadre(getSquadreByIdTorneo(idTorneo));
                torneoModels.add(torneoModel);

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
        return torneoModels;
    }

    @Override
    public List<TorneoModel> eliminaTorneoConSquadreAndGiocatori(int idTorneo) {
        List<SquadraModel> squadraModelList = getSquadreByIdTorneo(idTorneo);
        String sql = "Delete from torneo where id=" + idTorneo;
        if (squadraModelList.isEmpty()) {
            Connection con = null;
            try {
                con = DataSourceUtils.getConnection(((DataSourceTransactionManager) transactionManager).getDataSource());
                Statement st = con.createStatement();

                int nRow = st.executeUpdate(sql);
                if (nRow == 0) {
                    throw new TorneoNonTrovatoException();
                }
                return getTorneoEndSquadre();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                if (con != null) {
                    DataSourceUtils.releaseConnection(con, ((DataSourceTransactionManager) transactionManager).getDataSource());
                }
            }
        } else if (squadraModelList.size() == 1) {
            SquadraModel squadraModel = squadraModelList.get(0);
            if (getTorneiByIdSquadra(squadraModel.getIdSquadra()).size() > 1) {
                throw new TorneoConPiuSquadreException();
            } else {

                Connection con = null;
                try {
                    con = DataSourceUtils.getConnection(((DataSourceTransactionManager) transactionManager).getDataSource());
                    Statement st = con.createStatement();
                    st.executeUpdate("DELETE FROM squadra_torneo WHERE id_squadra = " + squadraModel.getIdSquadra());
                    squadraDao.rimuoviSquadra(squadraModel.getIdSquadra());
                    int nRow = st.executeUpdate(sql);
                    if (nRow == 0) {
                        throw new TorneoNonTrovatoException();
                    }
                    return getTorneoEndSquadre();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    if (con != null) {
                        DataSourceUtils.releaseConnection(con, ((DataSourceTransactionManager) transactionManager).getDataSource());
                    }

                }

            }

        }else{throw new TorneoConPiuSquadreException();}

    }


    @Override
    public TorneoModel getTorneoById(Integer idTorneo) {
        Connection con = null;
        try {
            con = DataSourceUtils.getConnection(((DataSourceTransactionManager) transactionManager).getDataSource());
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from torneo where id='" + idTorneo + "'");
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
        Connection con = null;
        List<SquadraModel> squadraModelList = new ArrayList<>();
        try {
            con = DataSourceUtils.getConnection(((DataSourceTransactionManager) transactionManager).getDataSource());
            Statement st = con.createStatement();
            ResultSet rsSquadre = st.executeQuery("SELECT s.nome, s.id FROM squadra_torneo st JOIN squadra s ON s.id = st" +
                    ".id_squadra WHERE st.id_torneo = " + idTorneo);
            while (rsSquadre.next()) {
                SquadraModel squadraModel = new SquadraModel();
                squadraModel.setNome(rsSquadre.getString("nome"));
                squadraModel.setIdSquadra(rsSquadre.getInt("id"));
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
    public List<TorneoModel> getTorneiByIdSquadra(Integer idSuadra) {

        Connection con = null;
        List<TorneoModel> torneoModelList = new ArrayList<>();
        try {
            con = DataSourceUtils.getConnection(((DataSourceTransactionManager) transactionManager).getDataSource());
            Statement st = con.createStatement();
            ResultSet rsSquadre = st.executeQuery("SELECT t.nome_torneo, t.id FROM squadra_torneo st JOIN torneo t ON t.id = st" +
                    ".id_squadra WHERE st.id_squadra = " + idSuadra);
            while (rsSquadre.next()) {
                TorneoModel torneoModel = new TorneoModel();
                torneoModel.setNomeTorneo(rsSquadre.getString("nome_torneo"));
                torneoModel.setIdTorneo(rsSquadre.getInt("id"));
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


