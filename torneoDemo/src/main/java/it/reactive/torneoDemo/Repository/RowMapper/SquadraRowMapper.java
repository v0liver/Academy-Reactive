package it.reactive.torneoDemo.Repository.RowMapper;

import it.reactive.torneoDemo.model.SquadraModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SquadraRowMapper implements RowMapper<SquadraModel> {
    @Override
    public SquadraModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        SquadraModel squadraModel = new SquadraModel();
        squadraModel.setIdSquadra(rs.getInt("id"));
        squadraModel.setNome(rs.getString("nome"));
        squadraModel.setColoriSociali(rs.getString("colori_sociali"));
        return squadraModel;
    }
}
