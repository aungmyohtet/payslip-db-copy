package com.frobom.payslip.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.frobom.payslip.entity.Holiday;

public class HolidayRowMapper implements RowMapper<Holiday>{

    @Override
    public Holiday mapRow(ResultSet rs, int arg1) throws SQLException {
        Holiday holiday = new Holiday();
        holiday.setCreatedDate(rs.getDate("created"));
        holiday.setDay(rs.getDate("day"));
        holiday.setDeleted(rs.getString("deleted"));
        holiday.setHoildayName(rs.getString("holiday_name"));
        holiday.setModifiedDate(rs.getDate("modified"));
        holiday.setCompanyId(rs.getInt("company_id"));
        holiday.setId(rs.getInt("id"));
        return holiday;
    }

    
}
