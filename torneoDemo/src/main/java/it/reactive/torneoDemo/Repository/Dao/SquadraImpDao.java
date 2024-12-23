package it.reactive.torneoDemo.Repository.Dao;


import it.reactive.torneoDemo.DTO.squadra.SquadraDTO;
import it.reactive.torneoDemo.exception.SquadraDuplicataException;
import it.reactive.torneoDemo.model.SquadraModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class SquadraImpDao implements SquadraDao {
    @Autowired
    Connection con;

    @Override
    public SquadraModel salvaSquadra(SquadraDTO squadraDTO) {
        String nomeSquadra = squadraDTO.getNome();
        String coloriSociali = squadraDTO.getColoriSociali();
        String query = "insert into squadra (nome,colori_sociali) values ('"+nomeSquadra+"','"+coloriSociali+"')";
        try {
            Statement st = con.createStatement();
            int nRow = st.executeUpdate(query);
            if (nRow==1){
                ResultSet rs = st.executeQuery("select * from squadra where nome='"+nomeSquadra+"'");
                    rs.next();
                    int idSquadra = rs.getInt("id");
                    SquadraModel squadraModel = new SquadraModel();
                    squadraModel.setIdSquadra(idSquadra);
                    squadraModel.setNome(nomeSquadra);
                    squadraModel.setColoriSociali(coloriSociali);
                    return squadraModel;
            }else {
                throw new SquadraDuplicataException();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }
}
