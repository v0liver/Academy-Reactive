package it.reactive.torneoDemo.Repository.Dao;
import it.reactive.torneoDemo.DTO.giocatore.GiocatoreDto;
import it.reactive.torneoDemo.DTO.squadra.SquadraDTO;
import it.reactive.torneoDemo.DTO.squadra.SquadreDiGiocatoriDTO;
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
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
@Profile(Costanti.TORNEO_DAO_JDBC_PREPAREDSTATEMENT)
public class SquadraImpDaoPreparedStatement implements SquadraDao {

    @Autowired
    Connection con;

    @Override
    public SquadraModel salvaSquadra(SquadraDTO squadraDTO) {
        String query = "insert into squadra (nome, colori_sociali) values (?, ?)";
        try (PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, squadraDTO.getNome());
            ps.setString(2, squadraDTO.getColoriSociali());

            int nRow = ps.executeUpdate();
            if (nRow == 1) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        int idSquadra = rs.getInt(1);
                        con.commit();
                        return getSquadraById(idSquadra);
                    }
                }
            } else {
                throw new SquadraDuplicataException();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public SquadraModel aggiungiGiocatore(int idSquadra, GiocatoreDto giocatoreDto){
        String nomeCognome = giocatoreDto.getNomeCognome();
        String queryCheck = "select * from giocatore where nome_cognome = ?";
        try (PreparedStatement ps = con.prepareStatement(queryCheck)) {
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
        }
    }

    @Override
    public SquadraModel getSquadraById(int idSquadra) {
        String query = "select * from squadra where id = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
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
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Set<GiocatoreModel> getGiocatoriBySquadraId(int idSquadra) {
        Set<GiocatoreModel> giocatori = new HashSet<>();
        String query = "select g.id, g.nome_cognome from giocatore g join squadra sq on g.id_squadra = sq.id where g.id_squadra = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
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
        }
        return giocatori;
    }
    @Override
    public TifoseriaModel getTifoseriaBySquadraId(int idSquadra) {
        String query = "select * from tifoseria where id_squadra = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
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
        }
        return null;
    }
    @Override
    public void rimuoviSquadra(int idSquadra) {
        try {
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
        }
    }

    @Override
    public List<SquadraModel> ricercaSquadra(boolean completo) {
        List<SquadraModel> listSquadraModel = new ArrayList<>();
        String query = "select * from squadra sq";
        try (PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listSquadraModel;
    }

    @Override
    public SquadraModel aggiungiTifoseria(int idSquadra, TifoseriaDTO tifoseriaDTO) {
        String nomeTifoseria = tifoseriaDTO.getNomeTifoseria();
        String queryCheck = "select * from tifoseria where nome_tifoseria = ? and id_squadra = ?";
        try (PreparedStatement ps = con.prepareStatement(queryCheck)) {
            ps.setString(1, nomeTifoseria);
            ps.setInt(2, idSquadra);
            ResultSet rsTifoseria = ps.executeQuery();
                if (rsTifoseria.next()) {
                    throw new RuntimeException("Tifoseria già associata alla squadra");
                }

                String queryInsert = "insert into tifoseria (id_squadra, nome_tifoseria) values (?, ?)";
                try (PreparedStatement psInsert = con.prepareStatement(queryInsert)) {
                    psInsert.setInt(1, idSquadra);
                    psInsert.setString(2, nomeTifoseria);
                    psInsert.executeUpdate();
                }

                return getSquadraById(idSquadra);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
