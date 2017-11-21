package com.frobom.payslip.dbcopy.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.frobom.payslip.dbcopy.entity.Grade;

public class GradeRowMapper implements RowMapper<Grade> {

    @Override
    public Grade mapRow(ResultSet rs, int rowNum) throws SQLException {
        Grade grade = new Grade();
        grade.setId(rs.getInt("id"));
        grade.setCreatedDate(rs.getDate("created"));
        grade.setDeleted(rs.getString("deleted"));
        grade.setModifiedDate(rs.getDate("modified"));
        grade.setName(rs.getString("grade_name"));
        grade.setCompanyId(rs.getInt("company_id"));
        grade.setId(rs.getInt("id"));
        return grade;
    }

}
