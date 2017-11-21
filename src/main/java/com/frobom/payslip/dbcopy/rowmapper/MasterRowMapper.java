package com.frobom.payslip.dbcopy.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.frobom.payslip.dbcopy.entity.Master;

public class MasterRowMapper implements RowMapper<Master>{

    @Override
    public Master mapRow(ResultSet rs, int arg1) throws SQLException {
        Master master = new Master();
        master.setCreatedDate(rs.getDate("created"));
        master.setDeleted(rs.getString("deleted"));
        master.setModifiedDate(rs.getDate("modified"));
        master.setName(rs.getString("name"));
        master.setPassword(rs.getString("password"));
        master.setUsername(rs.getString("username"));
        master.setCompanyId(rs.getInt("company_id"));
        master.setId(rs.getInt("id"));
        return master;
    }
    
    

}
