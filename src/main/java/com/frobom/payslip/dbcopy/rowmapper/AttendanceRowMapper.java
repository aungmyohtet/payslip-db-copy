package com.frobom.payslip.dbcopy.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.frobom.payslip.dbcopy.entity.Attendance;

public class AttendanceRowMapper implements RowMapper<Attendance>{

    @Override
    public Attendance mapRow(ResultSet rs, int arg1) throws SQLException {
        Attendance attendance = new Attendance();
        attendance.setAbsence(rs.getString("absence"));
        attendance.setBeingLate(rs.getFloat("being_late"));
        attendance.setCheckIn(rs.getDate("check_in"));
        attendance.setCheckOut(rs.getDate("check_out"));
        attendance.setCreatedDate(rs.getDate("created"));
        attendance.setDeleted(rs.getString("deleted"));
        attendance.setHolidayWork(rs.getFloat("holiday_work"));
        attendance.setLeaveEarly(rs.getFloat("leave_early"));
        attendance.setModifiedDate(rs.getDate("modified"));;
        attendance.setOvertime(rs.getFloat("overtime"));
        attendance.setUpdated(rs.getString("updated"));
        attendance.setCompanyId(rs.getInt("company_id"));
        attendance.setStaffId(rs.getInt("staff_id"));
        attendance.setId(rs.getInt("id"));
        return attendance;
    }

    
}
