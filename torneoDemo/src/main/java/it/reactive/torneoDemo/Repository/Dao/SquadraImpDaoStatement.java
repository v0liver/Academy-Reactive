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
        String query = "insert into squadra (nome,colori_sociali) values ('" + nomeSquadra + "','" + coloriSociali + "')";
        try {
            Statement st = con.createStatement();
            int nRow = st.executeUpdate(query);
            if (nRow == 1) {
                ResultSet rs = st.executeQuery("select * from squadra where nome='" + nomeSquadra + "'");
                rs.next();
                int idSquadra = rs.getInt("id");
                SquadraModel squadraModel = new SquadraModel();
                squadraModel.setIdSquadra(idSquadra);
                squadraModel.setNome(nomeSquadra);
                squadraModel.setColoriSociali(coloriSociali);
                return squadraModel;
            } else {
                throw new SquadraDuplicataException();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public SquadraModel salvaSquadraSquadraGiocatori(SquadreDiGiocatoriDTO squadreDiGiocatoriDTO) {
        String nomeSquadra = squadreDiGiocatoriDTO.getNome();
        String coloriSociali = squadreDiGiocatoriDTO.getColoriSociali();

        String query = "insert into squadra (nome,colori_sociali) values ('" + nomeSquadra + "','" + coloriSociali + "')";
        try {
            Statement st = con.createStatement();
            int nRow = st.executeUpdate(query);
            if (nRow == 1) {
                ResultSet rs = st.executeQuery("select * from squadra where nome='" + nomeSquadra + "'");
                rs.next();
                int idSquadra = rs.getInt("id");
                SquadraModel squadraModel = new SquadraModel();
                squadraModel.setIdSquadra(idSquadra);
                squadraModel.setNome(nomeSquadra);
                squadraModel.setColoriSociali(coloriSociali);
                Set<GiocatoreModel> giocatoreModelSet = new HashSet<>();
                for (GiocatoreDto giocatoreDto : squadreDiGiocatoriDTO.getListaGiocatori()) {
                    ResultSet resultSetGiocatoreDuplicato = st.executeQuery("select * from giocatore where nome_cognome ='" + giocatoreDto.getNomeCognome() + "'");
                    if (!resultSetGiocatoreDuplicato.next()) {
                        String query2 = "insert into giocatore (nome_cognome,id_squadra) values ('" + giocatoreDto.getNomeCognome() + "','" + idSquadra + "')";
                        nRow = st.executeUpdate(query2);
                    }else throw new GiocatoreDuplicatoException();
                }
                rs = st.executeQuery("select g.id id_giocatore,g.nome_cognome,numero_ammonizioni from giocatore g join squadra sq on g.id_squadra=sq.id where g.id_squadra='" + idSquadra + "'");
                while (rs.next()) {
                    int id_giocatore = rs.getInt("id_giocatore");
                    String nome_cognome = rs.getString("nome_cognome");
                    int numero_ammonizioni = rs.getInt("numero_ammonizioni");
                    GiocatoreModel giocatoreModel = new GiocatoreModel();
                    giocatoreModel.setIdGiocatore(id_giocatore);
                    giocatoreModel.setNomeCognome(nome_cognome);
                    //giocatoreModel.setSquadraModel(squadraModel);

                    giocatoreModelSet.add(giocatoreModel);
                }
                squadraModel.setGiocatori(giocatoreModelSet);
                return squadraModel;
            } else {
                throw new SquadraDuplicataException();
            }
        } catch (SQLException e) {
            if (e.getSQLState().equals("23505")){// 23505 è lo stato SQL standard per violazione di chiave univoca su Postgres (23000 sugli altri sistemi di database)
                throw new SquadraDuplicataException();
            }else {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<SquadraModel> ricercaSquadra(boolean completo) {
        List<SquadraModel> listSquadraModel = new ArrayList<>();
        if (!completo) {
            try {
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from squadra sq");

                while (rs.next()){
                    int id_squadra = rs.getInt("id");
                    String colorisociali = rs.getString("colori_sociali");
                    String nome = rs.getString("nome");
                    SquadraModel squadraModel = new SquadraModel();
                    squadraModel.setNome(nome);
                    squadraModel.setColoriSociali(colorisociali);
                    squadraModel.setIdSquadra(id_squadra);
                    listSquadraModel.add(squadraModel);
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else {
            try {
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from squadra sq");

                while (rs.next()){
                    int id_squadra = rs.getInt("id");
                    String colorisociali = rs.getString("colori_sociali");
                    String nome = rs.getString("nome");
                    SquadraModel squadraModel = new SquadraModel();
                    squadraModel.setNome(nome);
                    squadraModel.setColoriSociali(colorisociali);
                    squadraModel.setIdSquadra(id_squadra);

                    Statement stGiocatori = con.createStatement();
                    ResultSet rsGiocatori = stGiocatori.executeQuery("select g.id,g.nome_cognome from giocatore g join squadra sq on g.id_squadra=sq.id where g.id='"+id_squadra+"'");
                    Set <GiocatoreModel> giocatoreModelSet = new HashSet<>();
                    while (rsGiocatori.next()){
                        GiocatoreModel giocatoreModel = new GiocatoreModel();
                        int id_Giocatore = rsGiocatori.getInt("id");
                        String nomeCognomeGiocatore = rsGiocatori.getString("nome_cognome");
                        giocatoreModel.setIdGiocatore(id_Giocatore);
                        giocatoreModel.setNomeCognome(nomeCognomeGiocatore);
                        giocatoreModelSet.add(giocatoreModel);
                    }
                    squadraModel.setGiocatori(giocatoreModelSet);
                    listSquadraModel.add(squadraModel);
                }
                return listSquadraModel;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        return listSquadraModel;
    }

    @Override
    public SquadraModel aggiungiGiocatore(int id, GiocatoreDto giocatoreDto) {
        try {
            Statement st = con.createStatement();
            String nomeCognome = giocatoreDto.getNomeCognome();
            st.executeUpdate("insert into giocatore (id_squadra,nome_cognome) values ('"+id+"','"+nomeCognome+"')");
            ResultSet rs = st.executeQuery("select * from squadra where id='"+id+"'");
            SquadraModel squadraModel = new SquadraModel();
            while (rs.next()){
                String colorisociali = rs.getString("colori_sociali");
                String nome = rs.getString("nome");

                squadraModel.setNome(nome);
                squadraModel.setColoriSociali(colorisociali);
                squadraModel.setIdSquadra(id);

                Statement stGiocatori = con.createStatement();
                ResultSet rsGiocatori = stGiocatori.executeQuery("select g.id,g.nome_cognome from giocatore g join squadra sq on g.id_squadra=sq.id where g.id_squadra='"+id+"'");
                Set <GiocatoreModel> giocatoreModelSet = new HashSet<>();
                while (rsGiocatori.next()){
                    GiocatoreModel giocatoreModel = new GiocatoreModel();
                    int id_Giocatore = rsGiocatori.getInt("id");
                    String nomeCognomeGiocatore = rsGiocatori.getString("nome_cognome");
                    giocatoreModel.setIdGiocatore(id_Giocatore);
                    giocatoreModel.setNomeCognome(nomeCognomeGiocatore);
                    giocatoreModelSet.add(giocatoreModel);
                }
                squadraModel.setGiocatori(giocatoreModelSet);

            }
            return squadraModel;
        }

        catch (SQLException e) {
            if (e.getSQLState().equals("23505")){// 23505 è lo stato SQL standard per violazione di chiave univoca su Postgres (23000 sugli altri sistemi di database)
                throw new GiocatoreDuplicatoException();
            }else {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public SquadraModel aggiungiTifoseria(int idSquadra, TifoseriaDTO tifoseriaDTO) {
        try {
            Statement st = con.createStatement();
            String nomeTifoseria = tifoseriaDTO.getNomeTifoseria();
            int idTifoseria;
            ResultSet rs = st.executeQuery("select t.id from tifoseria t join squadra sq on sq.id=t.id_squadra  where sq.id='"+idSquadra+"'");
            if (rs.next()){
                idTifoseria = rs.getInt("id");
                st.executeUpdate("UPDATE tifoseria SET nome_tifoseria ='"+nomeTifoseria+"' where id='"+idTifoseria+"'");
            }else {
                st.executeUpdate("insert into tifoseria (id_squadra,nome_tifoseria) values ('" + idSquadra + "','" + nomeTifoseria + "')");
                rs= st.executeQuery("select t.id from tifoseria t join squadra sq on sq.id=t.id_squadra  where sq.id='"+idSquadra+"'");
                rs.next();
                idTifoseria=rs.getInt("id");
            }

            rs = st.executeQuery("select * from squadra where id='"+idSquadra+"'");
            SquadraModel squadraModel = new SquadraModel();
            while (rs.next()){
                String colorisociali = rs.getString("colori_sociali");
                String nome = rs.getString("nome");
                squadraModel.setNome(nome);
                squadraModel.setColoriSociali(colorisociali);
                squadraModel.setIdSquadra(idSquadra);
                TifoseriaModel tifoseriaModel = new TifoseriaModel();
                tifoseriaModel.setIdTifoseria(idTifoseria);
                tifoseriaModel.setNomeTifoseria(nomeTifoseria);
                squadraModel.setTifoseria(tifoseriaModel);
                Statement stGiocatori = con.createStatement();
                ResultSet rsGiocatori = stGiocatori.executeQuery("select g.id,g.nome_cognome from giocatore g join squadra sq on g.id_squadra=sq.id where g.id_squadra='"+idSquadra+"'");
                Set <GiocatoreModel> giocatoreModelSet = new HashSet<>();
                while (rsGiocatori.next()){
                    GiocatoreModel giocatoreModel = new GiocatoreModel();
                    int id_Giocatore = rsGiocatori.getInt("id");
                    String nomeCognomeGiocatore = rsGiocatori.getString("nome_cognome");
                    giocatoreModel.setIdGiocatore(id_Giocatore);
                    giocatoreModel.setNomeCognome(nomeCognomeGiocatore);
                    giocatoreModelSet.add(giocatoreModel);
                }
                squadraModel.setGiocatori(giocatoreModelSet);

            }
            return squadraModel;
        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void rimuoviSquadra(int idSquadra) {
        try {
            Statement st = con.createStatement();
            st.executeUpdate("delete from giocatore where id_squadra='"+idSquadra+"'");
            st =con.createStatement();
            st.executeUpdate("delete from tifoseria where id_squadra='"+idSquadra+"'");
            st = con.createStatement();
            int nRow= st.executeUpdate("delete from squadra where id='"+idSquadra+"'");
            if (nRow==0){
                throw new SquadraNonPresenteException();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
