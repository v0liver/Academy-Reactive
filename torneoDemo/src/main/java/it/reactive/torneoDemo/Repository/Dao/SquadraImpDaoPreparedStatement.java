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
import java.util.*;

@Repository
@Profile(Costanti.TORNEO_DAO_JDBC_PREPAREDSTATEMENT)
public class SquadraImpDaoPreparedStatement implements SquadraDao{
    @Autowired
    Connection con;

    @Override
    public SquadraModel salvaSquadra(SquadraDTO squadraDTO) {
        String nomeSquadra = squadraDTO.getNome();
        String coloriSociali = squadraDTO.getColoriSociali();

        try {
            PreparedStatement pt = con.prepareStatement("insert into squadra (nome,colori_sociali) values (?,?)");
            pt.setString(1,nomeSquadra);
            pt.setString(2,coloriSociali);
            int nRow = pt.executeUpdate();
            if (nRow == 1) {
                pt=con.prepareStatement("select * from squadra where nome=?");
                pt.setString(1,nomeSquadra);
                ResultSet rs = pt.executeQuery();
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
            PreparedStatement pt = con.prepareStatement("insert into squadra (nome,colori_sociali) values (?,?)");
            pt.setString(1,nomeSquadra);
            pt.setString(2,coloriSociali);
            int nRow = pt.executeUpdate();
            if (nRow == 1) {
                pt=con.prepareStatement("select * from squadra where nome=?");
                pt.setString(1,nomeSquadra);
                ResultSet rs = pt.executeQuery();
                rs.next();
                int idSquadra = rs.getInt("id");
                SquadraModel squadraModel = new SquadraModel();
                squadraModel.setIdSquadra(idSquadra);
                squadraModel.setNome(nomeSquadra);
                squadraModel.setColoriSociali(coloriSociali);
                Set<GiocatoreModel> giocatoreModelSet = new HashSet<>();
                for (GiocatoreDto giocatoreDto : squadreDiGiocatoriDTO.getListaGiocatori()) {
                    pt=con.prepareStatement("select * from giocatore where nome_cognome =?");
                    pt.setString(1,giocatoreDto.getNomeCognome());
                    ResultSet resultSetGiocatoreDuplicato = pt.executeQuery();
                    if (!resultSetGiocatoreDuplicato.next()) {
                        pt=con.prepareStatement("insert into giocatore (nome_cognome,id_squadra) values (?,?)");
                        pt.setString(1,giocatoreDto.getNomeCognome());
                        pt.setInt(2,idSquadra);
                        nRow = pt.executeUpdate();
                    }else throw new GiocatoreDuplicatoException();
                }
                pt=con.prepareStatement("select g.id id_giocatore,g.nome_cognome,numero_ammonizioni from giocatore g join squadra sq on g.id_squadra=sq.id where g.id_squadra=?");
                pt.setInt(1,idSquadra);
                rs = pt.executeQuery();
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
                PreparedStatement pt = con.prepareStatement("select * from squadra sq");
                ResultSet rs = pt.executeQuery();

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
                PreparedStatement pt = con.prepareStatement("select * from squadra sq");
                ResultSet rs = pt.executeQuery();

                while (rs.next()){
                    int id_squadra = rs.getInt("id");
                    String colorisociali = rs.getString("colori_sociali");
                    String nome = rs.getString("nome");
                    SquadraModel squadraModel = new SquadraModel();
                    squadraModel.setNome(nome);
                    squadraModel.setColoriSociali(colorisociali);
                    squadraModel.setIdSquadra(id_squadra);

                    PreparedStatement ptGiocatori = con.prepareStatement("select g.id,g.nome_cognome from giocatore g join squadra sq on g.id_squadra=sq.id where g.id=?");
                    ptGiocatori.setInt(1,id_squadra);
                    ResultSet rsGiocatori = ptGiocatori.executeQuery();
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
            String nomeCognome = giocatoreDto.getNomeCognome();
            PreparedStatement pt = con.prepareStatement("insert into giocatore (id_squadra,nome_cognome) values (?,?)");
            pt.setInt(1,id);
            pt.setString(2,nomeCognome);
            pt.executeUpdate();
            pt=con.prepareStatement("select * from squadra where id=?");
            pt.setInt(1,id);
            ResultSet rs = pt.executeQuery();
            SquadraModel squadraModel = new SquadraModel();
            while (rs.next()){
                String colorisociali = rs.getString("colori_sociali");
                String nome = rs.getString("nome");

                squadraModel.setNome(nome);
                squadraModel.setColoriSociali(colorisociali);
                squadraModel.setIdSquadra(id);

                PreparedStatement ptGiocatori = con.prepareStatement("select g.id,g.nome_cognome from giocatore g join squadra sq on g.id_squadra=sq.id where g.id_squadra=?");
                ptGiocatori.setInt(1,id);
                ResultSet rsGiocatori = ptGiocatori.executeQuery();
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
            PreparedStatement pt = con.prepareStatement("select t.id from tifoseria t join squadra sq on sq.id=t.id_squadra  where sq.id=?");
            pt.setInt(1,idSquadra);
            String nomeTifoseria = tifoseriaDTO.getNomeTifoseria();
            int idTifoseria;
            ResultSet rs = pt.executeQuery();
            if (rs.next()){
                idTifoseria = rs.getInt("id");
                pt=con.prepareStatement("UPDATE tifoseria SET nome_tifoseria =? where id=?");
                pt.setString(1,nomeTifoseria);
                pt.setInt(2,idTifoseria);
                pt.executeUpdate();
            }else {
                pt = con.prepareStatement("insert into tifoseria (id_squadra,nome_tifoseria) values (?,?)");
                pt.setInt(1,idSquadra);
                pt.setString(2,nomeTifoseria);
                pt.executeUpdate();
                pt=con.prepareStatement("select t.id from tifoseria t join squadra sq on sq.id=t.id_squadra  where sq.id=?");
                pt.setInt(1,idSquadra);
                rs= pt.executeQuery();
                rs.next();
                idTifoseria=rs.getInt("id");
            }
            pt = con.prepareStatement("select * from squadra where id=?");
            pt.setInt(1,idSquadra);
            rs = pt.executeQuery();
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
                PreparedStatement ptGiocatori = con.prepareStatement("select g.id,g.nome_cognome from giocatore g join squadra sq on g.id_squadra=sq.id where g.id_squadra=?");
                ptGiocatori.setInt(1,idSquadra);
                ResultSet rsGiocatori = ptGiocatori.executeQuery();
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
            PreparedStatement pt = con.prepareStatement("delete from giocatore where id_squadra=?");
            pt.setInt(1,idSquadra);
            pt.executeUpdate();
            pt =con.prepareStatement("delete from tifoseria where id_squadra = ?");
            pt.setInt(1,idSquadra);
            pt.executeUpdate();
            pt = con.prepareStatement("delete from squadra where id=?");
            pt.setInt(1,idSquadra);
            int nRow= pt.executeUpdate();
            if (nRow==0){
                throw new SquadraNonPresenteException();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
