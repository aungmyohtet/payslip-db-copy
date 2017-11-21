package com.frobom.payslip.dbcopy.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.frobom.payslip.dbcopy.entity.Company;

public class CompanyRowMapper implements RowMapper<Company> {

    @Override
    public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
        Company company = new Company();
        company.setId(rs.getInt("id"));
        company.setCompanyName(rs.getString("company_name"));
        company.setCreatedDate(rs.getDate("created"));
        company.setDeleted(rs.getString("deleted"));
        company.setEmail(rs.getString("mail"));
        company.setModifiedDate(rs.getDate("modified"));
        company.setName(rs.getString("name"));
        return company;
    }

}
