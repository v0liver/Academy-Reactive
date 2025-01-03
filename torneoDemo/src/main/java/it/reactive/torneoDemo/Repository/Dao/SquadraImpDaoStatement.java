package it.reactive.torneoDemo.Repository.Dao;


import it.reactive.torneoDemo.DTO.giocatore.GiocatoreDto;
import it.reactive.torneoDemo.DTO.squadra.SquadraDTO;
import it.reactive.torneoDemo.DTO.squadra.SquadreDiGiocatoriDTO;
import it.reactive.torneoDemo.Utility.Costanti;
import it.reactive.torneoDemo.exception.GiocatoreDuplicatoException;
import it.reactive.torneoDemo.exception.SquadraDuplicataException;
import it.reactive.torneoDemo.model.GiocatoreModel;
import it.reactive.torneoDemo.model.SquadraModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
//@Profile(Costanti.TORNEO_DAO_JDBC_STATEMENT)
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
                    String query2 = "insert into giocatore (nome_cognome,id_squadra) values ('" + giocatoreDto.getNomeCognome() + "','" + idSquadra + "')";
                     st = con.createStatement();
                     nRow = st.executeUpdate(query2);
                     if (nRow==squadreDiGiocatoriDTO.getListaGiocatori().size()){
                         rs = st.executeQuery("select g.id id_giocatore,g.nome_cognome,numero_ammonizioni from giocatore g join squadra sq on g.id_squadra=sq.id where g.id_squadra='" + idSquadra + "'");
                         while (rs.next()){
                             int id_giocatore = rs.getInt("id_giocatore");
                             String nome_cognome = rs.getString("nome_cognome");
                             int numero_ammonizioni = rs.getInt("numero_ammonizioni");
                             GiocatoreModel giocatoreModel = new GiocatoreModel();
                             giocatoreModel.setIdGiocatore(id_giocatore);
                             giocatoreModel.setNomeCognome(nome_cognome);
                             //giocatoreModel.setSquadraModel(squadraModel);

                            giocatoreModelSet.add(giocatoreModel);
                         }
                     }else {
                         throw new GiocatoreDuplicatoException();
                     }
                }
                squadraModel.setGiocatori(giocatoreModelSet);
                return squadraModel;
            } else {
                throw new SquadraDuplicataException();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
