package com.frobom.payslip.dbcopy.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.frobom.payslip.dbcopy.entity.Staff;

public class StaffRowMapper implements RowMapper<Staff> {

    public Staff mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Staff staff = new Staff();
        staff.setCasualDay(resultSet.getInt("casual_days"));
        staff.setCreatedDate(resultSet.getDate("created"));
        staff.setDeleted(resultSet.getString("deleted"));
        staff.setModifiedDate(resultSet.getDate("modified"));
        staff.setName(resultSet.getString("name"));
        staff.setPaidHoliday(resultSet.getInt("paid_holidays"));
        staff.setPassword(resultSet.getString("password"));
        staff.setRetireDate(resultSet.getDate("retire_date"));
        staff.setTransportationFee(resultSet.getInt("transporation_expenses"));
        staff.setUsername(resultSet.getString("username"));
        staff.setCompanyId(resultSet.getInt("company_id"));
        staff.setGradeId(resultSet.getInt("grade_id"));
        staff.setPositionId(resultSet.getInt("position_id"));
        staff.setId(resultSet.getInt("id"));
        return staff;
    }

}
