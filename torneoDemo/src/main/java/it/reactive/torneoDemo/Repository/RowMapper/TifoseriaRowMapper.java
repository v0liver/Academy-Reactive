package it.reactive.torneoDemo.Repository.RowMapper;

import it.reactive.torneoDemo.model.TifoseriaModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TifoseriaRowMapper implements RowMapper<TifoseriaModel> {
    @Override
    public TifoseriaModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        TifoseriaModel tifoseriaModel = new TifoseriaModel();
        tifoseriaModel.setIdTifoseria(rs.getInt("id"));
        tifoseriaModel.setNomeTifoseria(rs.getString("nome_tifoseria"));
        return tifoseriaModel;
    }
}
