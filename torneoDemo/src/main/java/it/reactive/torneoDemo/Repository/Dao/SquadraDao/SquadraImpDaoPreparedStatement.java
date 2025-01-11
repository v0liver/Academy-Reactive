package it.reactive.torneoDemo.Repository.Dao.SquadraDao;

import it.reactive.torneoDemo.DTO.giocatore.GiocatoreDto;
import it.reactive.torneoDemo.DTO.squadra.SquadraDTO;
import it.reactive.torneoDemo.DTO.tifoseria.TifoseriaDTO;
import it.reactive.torneoDemo.Utility.Costanti;
import it.reactive.torneoDemo.exception.GiocatoreDuplicatoException;
import it.reactive.torneoDemo.exception.SquadraDuplicataException;
import it.reactive.torneoDemo.exception.SquadraNonPresenteException;
import it.reactive.torneoDemo.exception.TifoseriaGiaAssegnataException;
import it.reactive.torneoDemo.model.GiocatoreModel;
import it.reactive.torneoDemo.model.SquadraModel;
import it.reactive.torneoDemo.model.TifoseriaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
@Profile(Costanti.TORNEO_DAO_JDBC_PREPAREDSTATEMENT)
public class SquadraImpDaoPreparedStatement implements SquadraDao {

    @Autowired
    DataSource dataSource;

    @Autowired
    PlatformTransactionManager transactionManager;


    @Override
    public SquadraModel salvaSquadra(SquadraDTO squadraDTO) {
        Connection con = null;
        String query = "insert into squadra (nome, colori_sociali) values (?, ?)";
        try {
            con = DataSourceUtils.getConnection(((DataSourceTransactionManager) transactionManager).getDataSource());
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, squadraDTO.getNome());
            ps.setString(2, squadraDTO.getColoriSociali());

            int nRow = ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int idSquadra = rs.getInt(1);
                    return getSquadraById(idSquadra);
                }
            }

        } catch (SQLException e) {
            if ("23505".equals(e.getSQLState())) {
                throw new SquadraDuplicataException();
            } else throw new RuntimeException(e);
        } finally {
            if (con != null) {
                DataSourceUtils.releaseConnection(con, ((DataSourceTransactionManager) transactionManager).getDataSource());
            }
        }
        return null;
    }

    @Override
    public SquadraModel aggiungiGiocatore(int idSquadra, GiocatoreDto giocatoreDto) {
        Connection con = null;
        String nomeCognome = giocatoreDto.getNomeCognome();
        String queryCheck = "select * from giocatore where nome_cognome = ?";
        try {
            con = DataSourceUtils.getConnection(((DataSourceTransactionManager) transactionManager).getDataSource());
            PreparedStatement ps = con.prepareStatement(queryCheck);
            ps.setString(1, nomeCognome);
            try (ResultSet rsGiocatore = ps.executeQuery()) {
                if (!rsGiocatore.next()) {
                    String queryInsert = "insert into giocatore (id_squadra, nome_cognome) values (?, ?)";
                    try (PreparedStatement psInsert = con.prepareStatement(queryInsert)) {
                        psInsert.setInt(1, idSquadra);
                        psInsert.setString(2, nomeCognome);
                        psInsert.executeUpdate();
                    }
                } else {
                    throw new GiocatoreDuplicatoException();
                }
            }

            return getSquadraById(idSquadra);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (con != null) {
                DataSourceUtils.releaseConnection(con, ((DataSourceTransactionManager) transactionManager).getDataSource());
            }
        }
    }

    @Override
    public SquadraModel getSquadraById(int idSquadra) {
        Connection con = null;
        String query = "select * from squadra where id = ?";
        try {
            con = DataSourceUtils.getConnection(((DataSourceTransactionManager) transactionManager).getDataSource());
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, idSquadra);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    SquadraModel squadraModel = new SquadraModel();
                    squadraModel.setIdSquadra(idSquadra);
                    squadraModel.setNome(rs.getString("nome"));
                    squadraModel.setColoriSociali(rs.getString("colori_sociali"));
                    squadraModel.setTifoseria(getTifoseriaBySquadraId(idSquadra));
                    Set<GiocatoreModel> giocatori = getGiocatoriBySquadraId(idSquadra);
                    squadraModel.setGiocatori(giocatori);

                    return squadraModel;
                }else {
                    throw new SquadraNonPresenteException();
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (con != null) {
                DataSourceUtils.releaseConnection(con, ((DataSourceTransactionManager) transactionManager).getDataSource());
            }
        }
    }

    public Set<GiocatoreModel> getGiocatoriBySquadraId(int idSquadra) {
        Connection con = null;
        Set<GiocatoreModel> giocatori = new HashSet<>();
        String query = "select g.id, g.nome_cognome from giocatore g join squadra sq on g.id_squadra = sq.id where g.id_squadra = ?";
        try {
            con = DataSourceUtils.getConnection(((DataSourceTransactionManager) transactionManager).getDataSource());
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, idSquadra);
            try (ResultSet rsGiocatori = ps.executeQuery()) {
                while (rsGiocatori.next()) {
                    GiocatoreModel giocatoreModel = new GiocatoreModel();
                    giocatoreModel.setIdGiocatore(rsGiocatori.getInt("id"));
                    giocatoreModel.setNomeCognome(rsGiocatori.getString("nome_cognome"));
                    giocatori.add(giocatoreModel);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (con != null) {
                DataSourceUtils.releaseConnection(con, ((DataSourceTransactionManager) transactionManager).getDataSource());
            }
        }
        return giocatori;
    }

    @Override
    public TifoseriaModel getTifoseriaBySquadraId(int idSquadra) {
        Connection con = null;
        String query = "select * from tifoseria where id_squadra = ?";
        try {
            con = DataSourceUtils.getConnection(((DataSourceTransactionManager) transactionManager).getDataSource());
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, idSquadra);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    TifoseriaModel tifoseriaModel = new TifoseriaModel();
                    tifoseriaModel.setIdTifoseria(rs.getInt("id"));
                    tifoseriaModel.setNomeTifoseria(rs.getString("nome_tifoseria"));
                    return tifoseriaModel;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (con != null) {
                DataSourceUtils.releaseConnection(con, ((DataSourceTransactionManager) transactionManager).getDataSource());
            }
        }
        return null;
    }

    @Override
    public void rimuoviSquadra(int idSquadra) {
        Connection con = null;
        try {
            con = DataSourceUtils.getConnection(((DataSourceTransactionManager) transactionManager).getDataSource());
            String queryDeleteGiocatori = "delete from giocatore where id_squadra = ?";
            try (PreparedStatement psGiocatori = con.prepareStatement(queryDeleteGiocatori)) {
                psGiocatori.setInt(1, idSquadra);
                psGiocatori.executeUpdate();
            }

            String queryDeleteTifoseria = "delete from tifoseria where id_squadra = ?";
            PreparedStatement psTifoseria = con.prepareStatement(queryDeleteTifoseria);
            psTifoseria.setInt(1, idSquadra);
            psTifoseria.executeUpdate();


            String queryDeleteSquadra = "delete from squadra where id = ?";
            PreparedStatement psSquadra = con.prepareStatement(queryDeleteSquadra);
            psSquadra.setInt(1, idSquadra);
            int nRow = psSquadra.executeUpdate();
            if (nRow == 0) {
                throw new SquadraNonPresenteException();
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
    public List<SquadraModel> ricercaSquadra(boolean completo) {
        Connection con = null;
        List<SquadraModel> listSquadraModel = new ArrayList<>();
        String query = "select * from squadra sq";
        try {
            con = DataSourceUtils.getConnection(((DataSourceTransactionManager) transactionManager).getDataSource());
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idSquadra = rs.getInt("id");
                SquadraModel squadraModel = new SquadraModel();
                squadraModel.setIdSquadra(idSquadra);
                squadraModel.setNome(rs.getString("nome"));
                squadraModel.setColoriSociali(rs.getString("colori_sociali"));
                squadraModel.setTifoseria(getTifoseriaBySquadraId(idSquadra));

                if (completo) {
                    squadraModel.setGiocatori(getGiocatoriBySquadraId(idSquadra));
                }

                listSquadraModel.add(squadraModel);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (con != null) {
                DataSourceUtils.releaseConnection(con, ((DataSourceTransactionManager) transactionManager).getDataSource());
            }
        }
        return listSquadraModel;
    }

    @Override
    public SquadraModel aggiungiTifoseria(int idSquadra, TifoseriaDTO tifoseriaDTO) {
        Connection con = null;
        String nomeTifoseria = tifoseriaDTO.getNomeTifoseria();
        String queryCheck = "select * from tifoseria where id_squadra = ?";
        try {
            con = DataSourceUtils.getConnection(((DataSourceTransactionManager) transactionManager).getDataSource());
            PreparedStatement ps = con.prepareStatement(queryCheck);
            ps.setInt(1, idSquadra);
            ResultSet rsTifoseria = ps.executeQuery();
            if (rsTifoseria.next()) {
                String queryInsert = "Update tifoseria SET nome_tifoseria = ? where id= ?";
                PreparedStatement psInsert = con.prepareStatement(queryInsert);
                    psInsert.setString(1, nomeTifoseria);
                    psInsert.setInt(2, rsTifoseria.getInt("id"));
                    psInsert.executeUpdate();
                    return getSquadraById(idSquadra);

            }

            String queryInsert = "insert into tifoseria (id_squadra, nome_tifoseria) values (?, ?)";
                PreparedStatement psInsert = con.prepareStatement(queryInsert);
                psInsert.setInt(1, idSquadra);
                psInsert.setString(2, nomeTifoseria);
                psInsert.executeUpdate();
                return getSquadraById(idSquadra);




        } catch (SQLException e) {
            if ("23505".equals(e.getSQLState())) {
                throw new TifoseriaGiaAssegnataException();
            } else throw new RuntimeException(e);
        } finally {
            if (con != null) {
                DataSourceUtils.releaseConnection(con, ((DataSourceTransactionManager) transactionManager).getDataSource());
            }
        }
    }
}
