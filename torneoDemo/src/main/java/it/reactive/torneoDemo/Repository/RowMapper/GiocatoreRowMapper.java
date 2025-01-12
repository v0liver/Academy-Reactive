package it.reactive.torneoDemo.Repository.RowMapper;

import it.reactive.torneoDemo.model.GiocatoreModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GiocatoreRowMapper implements RowMapper<GiocatoreModel> {
    @Override
    public GiocatoreModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        GiocatoreModel giocatoreModel = new GiocatoreModel();
        giocatoreModel.setIdGiocatore(rs.getInt("id"));
        giocatoreModel.setNumeroAmmonizioni(rs.getInt("numero_ammonizioni"));
        giocatoreModel.setNomeCognome(rs.getString("nome_cognome"));
        return giocatoreModel;
    }
}
