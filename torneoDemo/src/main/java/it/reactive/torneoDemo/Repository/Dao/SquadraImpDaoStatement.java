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
@Profile(Costanti.TORNEO_DAO_JDBC_STATEMENT)
public class SquadraImpDaoStatement implements SquadraDao {

    @Autowired
    Connection con;

    @Override
    public SquadraModel salvaSquadra(SquadraDTO squadraDTO) {
        String nomeSquadra = squadraDTO.getNome();
        String coloriSociali = squadraDTO.getColoriSociali();
        String query = "insert into squadra (nome, colori_sociali) values ('" + nomeSquadra + "','" + coloriSociali + "')";
        try (Statement st = con.createStatement()) {
            int nRow = st.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            if (nRow == 1) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int idSquadra = rs.getInt(1);
                    return getSquadraById(idSquadra);
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
    public SquadraModel aggiungiGiocatore(int idSquadra, GiocatoreDto giocatoreDto) {
        try (Statement st = con.createStatement()) {
            String nomeCognome = giocatoreDto.getNomeCognome();
            ResultSet rsGiocatore = st.executeQuery("select * from giocatore where nome_cognome ='" + nomeCognome + "'");
            if (!rsGiocatore.next()) {
                String query = "insert into giocatore (id_squadra, nome_cognome) values ('" + idSquadra + "','" + nomeCognome + "')";
                st.executeUpdate(query);
            } else {
                throw new GiocatoreDuplicatoException();
            }

            return getSquadraById(idSquadra);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public SquadraModel getSquadraById(int idSquadra) {
        try (Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery("select * from squadra where id='" + idSquadra + "'");
            if (rs.next()) {
                String nome = rs.getString("nome");
                String coloriSociali = rs.getString("colori_sociali");

                SquadraModel squadraModel = new SquadraModel();
                squadraModel.setIdSquadra(idSquadra);
                squadraModel.setNome(nome);
                squadraModel.setColoriSociali(coloriSociali);
                squadraModel.setTifoseria(getTifoseriaBySquadraId(idSquadra));
                Set<GiocatoreModel> giocatori = getGiocatoriBySquadraId(idSquadra);
                squadraModel.setGiocatori(giocatori);

                return squadraModel;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Set<GiocatoreModel> getGiocatoriBySquadraId(int idSquadra) {
        Set<GiocatoreModel> giocatori = new HashSet<>();
        try (Statement st = con.createStatement()) {
            ResultSet rsGiocatori = st.executeQuery("select g.id, g.nome_cognome from giocatore g join squadra sq on g.id_squadra=sq.id where g.id_squadra='" + idSquadra + "'");
            while (rsGiocatori.next()) {
                GiocatoreModel giocatoreModel = new GiocatoreModel();
                giocatoreModel.setIdGiocatore(rsGiocatori.getInt("id"));
                giocatoreModel.setNomeCognome(rsGiocatori.getString("nome_cognome"));
                giocatori.add(giocatoreModel);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return giocatori;
    }

    @Override
    public void rimuoviSquadra(int idSquadra) {
        try (Statement st = con.createStatement()) {
            st.executeUpdate("delete from giocatore where id_squadra='" + idSquadra + "'");
            st.executeUpdate("delete from tifoseria where id_squadra='" + idSquadra + "'");
            int nRow = st.executeUpdate("delete from squadra where id='" + idSquadra + "'");
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
        try (Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery("select * from squadra sq");
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
        try (Statement st = con.createStatement()) {
            ResultSet rsTifoseria = st.executeQuery("select * from tifoseria where nome_tifoseria ='" + nomeTifoseria + "' and id_squadra = " + idSquadra);
            if (rsTifoseria.next()) {
                throw new RuntimeException("Tifoseria già associata alla squadra");
            }

            String query = "insert into tifoseria (id_squadra, nome_tifoseria) values (" + idSquadra + ", '" + nomeTifoseria + "')";
            st.executeUpdate(query);

            return getSquadraById(idSquadra);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public TifoseriaModel getTifoseriaBySquadraId(int idSquadra) {
        String query = "select * from tifoseria where id_squadra = " + idSquadra;
        try (Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                TifoseriaModel tifoseriaModel = new TifoseriaModel();
                tifoseriaModel.setIdTifoseria(rs.getInt("id"));
                tifoseriaModel.setNomeTifoseria(rs.getString("nome_tifoserianome_tifoseria"));
                return tifoseriaModel;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}

