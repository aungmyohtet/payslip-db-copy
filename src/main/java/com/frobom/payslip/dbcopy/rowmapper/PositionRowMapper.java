package com.frobom.payslip.dbcopy.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.frobom.payslip.dbcopy.entity.Position;

public class PositionRowMapper implements RowMapper<Position>{

    @Override
    public Position mapRow(ResultSet rs, int arg1) throws SQLException {
        Position position = new Position();
        position.setCreatedDate(rs.getDate("created"));
        position.setDeleted(rs.getString("deleted"));
        position.setModifiedDate(rs.getDate("modified"));
        position.setName(rs.getString("position_name"));
        position.setCompanyId(rs.getInt("company_id"));
        position.setId(rs.getInt("id"));
        return position;
    }

}
