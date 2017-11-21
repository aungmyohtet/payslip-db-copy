package com.frobom.payslip.copydb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import com.frobom.payslip.entity.Attendance;
import com.frobom.payslip.entity.Company;
import com.frobom.payslip.entity.Grade;
import com.frobom.payslip.entity.Holiday;
import com.frobom.payslip.entity.Master;
import com.frobom.payslip.entity.Position;
import com.frobom.payslip.entity.Staff;
import com.frobom.payslip.rowmapper.AttendanceRowMapper;
import com.frobom.payslip.rowmapper.CompanyRowMapper;
import com.frobom.payslip.rowmapper.GradeRowMapper;
import com.frobom.payslip.rowmapper.HolidayRowMapper;
import com.frobom.payslip.rowmapper.MasterRowMapper;
import com.frobom.payslip.rowmapper.PositionRowMapper;
import com.frobom.payslip.rowmapper.StaffRowMapper;

@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    public void run(String... arg0) throws Exception {
        System.out.println("Started Application...");
        copyCompanies();
        copyHolidays();
        copyGrades();
        copyPositions();
        copyStaffs();
        copyMasters();
        copyAttendances();
        System.exit(0);
    }

    public void copyMasters() {
        System.out.println("Copying masters ...");
        String payslipCompanyRowCountSql = "select count(id) from masters";
        int masterCount = payslipJdbcTemplate.queryForObject(payslipCompanyRowCountSql, Integer.class);
        System.out.println(masterCount);
        List<Master> masters = new ArrayList<>();
        String insertMasterSql = "insert into masters (id, username, password, name, company_id , created, modified, deleted) "
                + "values (?, ?, ?, ?, ?, ?, ?, ?)";
        String masterUpdateSql = "update masters set username = ?, "
                + "password = ?, name = ?, company_id = ?, "
                + "created = ?, modified = ? , deleted = ? "
                + "where id = ?";

        if (masterCount <= 0) {
            String kyuyoMastersSql = "select * from masters";
            masters = kyuyoJdbcTemplate.query(kyuyoMastersSql, new MasterRowMapper());
            for (Master master : masters) {
                try {
                    payslipJdbcTemplate.update(insertMasterSql, new Object[] {master.getId(),
                            master.getUsername(), master.getPassword(), master.getName(), master.getCompanyId(), 
                            master.getCreatedDate(), master.getModifiedDate(), master.getDeleted()});
                } catch(Exception ex) {
                    //
                }
                
            }
        } else {
          Calendar startCalendar = Calendar.getInstance();
          startCalendar.add(Calendar.DAY_OF_MONTH, -1);
          Calendar endCalendar = Calendar.getInstance();
          endCalendar.add(Calendar.DAY_OF_MONTH, 1);
          String kyuyoMastersSql = "select * from masters where (modified between  ? and ?) or (created between ? and ?)";
          masters = kyuyoJdbcTemplate.query(kyuyoMastersSql, new Object[] {startCalendar.getTime(), endCalendar.getTime(),
                  startCalendar.getTime(), endCalendar.getTime()}, new MasterRowMapper());
          for (Master master : masters) {
              Master masterFromPayslipDb = null;
              try {
                  masterFromPayslipDb = payslipJdbcTemplate.queryForObject("select * from masters where id = ?", 
                          new Object[] {master.getId()}, new MasterRowMapper());
              } catch (Exception ex) {
                  // no result
              }
              // update
              if (masterFromPayslipDb != null) {
                  try {
                      payslipJdbcTemplate.update(masterUpdateSql, new Object[] {master.getUsername(), master.getPassword(), 
                              master.getName(), master.getCompanyId(), master.getCreatedDate(), master.getModifiedDate(),
                              master.getDeleted(), master.getId()});
                  } catch (Exception ex) {
                      
                  }
                  
              } else {
                  try {
                      payslipJdbcTemplate.update(insertMasterSql, new Object[] {master.getId(),
                              master.getUsername(), master.getPassword(), master.getName(), master.getCompanyId(), 
                              master.getCreatedDate(), master.getModifiedDate(), master.getDeleted()});
                  } catch(Exception ex) {
                      
                  }
                  
              }
          }
        }
    }

    public void copyAttendances() {
        String payslipAttendanceRowCountSql = "select count(id) from attendances";
        int attendanceRowCount = payslipJdbcTemplate.queryForObject(payslipAttendanceRowCountSql, Integer.class);
        System.out.println(attendanceRowCount);
        List<Attendance> attendances = new ArrayList<>();
        String insertSql = "insert into attendances (id, absence, being_late, check_in, check_out , created, deleted, "
                + "holiday_work, leave_early, modified, overtime, updated, company_id, staff_id) "
                + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String updateSql = "update attendances set absence = ?, "
                + "being_late = ?, check_in = ?, check_out = ?, "
                + "created = ?, deleted = ?, holiday_work = ?, leave_early = ?, modified = ?, "
                + "overtime = ?, updated = ?, company_id, staff_id = ? "
                + "where id = ?";

        if (attendanceRowCount <= 0) {
            Calendar start = Calendar.getInstance();
            start.add(Calendar.MONTH, -3);
            Calendar end = Calendar.getInstance();
            end.add(Calendar.DAY_OF_MONTH, 1);
            String kyuyoAttendancesSql = "select * from attendances where created between ? and ?";
            attendances = kyuyoJdbcTemplate.query(kyuyoAttendancesSql, new Object[] {start.getTime(), end.getTime()}, new AttendanceRowMapper());
            for (Attendance attendance : attendances) {
                try {
                    payslipJdbcTemplate.update(insertSql, new Object[] {attendance.getId(), attendance.getAbsence(),
                            attendance.getBeingLate(), attendance.getCheckIn(), attendance.getCheckOut(),
                            attendance.getCreatedDate(), attendance.getDeleted(), attendance.getHolidayWork(),
                            attendance.getLeaveEarly(), attendance.getModifiedDate(), 
                            attendance.getOvertime(), attendance.getUpdated(), attendance.getCompanyId(), attendance.getStaffId()});
                } catch(Exception ex) {
                    
                }
               
            }
        } else {
          Calendar startCalendar = Calendar.getInstance();
          startCalendar.add(Calendar.MONTH, -1);
          Calendar endCalendar = Calendar.getInstance();
          endCalendar.add(Calendar.DAY_OF_MONTH, 1);
          String kyuyoAttendancesSql = "select * from attendances where (modified between  ? and ?) or (created between ? and ?)";
          attendances = kyuyoJdbcTemplate.query(kyuyoAttendancesSql, new Object[] {startCalendar.getTime(), endCalendar.getTime(),
                  startCalendar.getTime(), endCalendar.getTime()}, new AttendanceRowMapper());
          for (Attendance attendance : attendances) {
              Attendance attendanceFromPayslipDb = null;
              try {
                  attendanceFromPayslipDb = payslipJdbcTemplate.queryForObject("select * from attendances where id = ?", 
                          new Object[] {attendance.getId()}, new AttendanceRowMapper());
              } catch (Exception ex) {
                  // no result
              }
              // update
              if (attendanceFromPayslipDb != null) {
                    try {
                        payslipJdbcTemplate.update(updateSql, new Object[] {attendance.getAbsence(),
                                attendance.getBeingLate(), attendance.getCheckIn(), attendance.getCheckOut(),
                                attendance.getCreatedDate(), attendance.getDeleted(), attendance.getHolidayWork(),
                                attendance.getLeaveEarly(), attendance.getModifiedDate(), 
                                attendance.getOvertime(), attendance.getUpdated(), attendance.getCompanyId(),
                                attendance.getStaffId(), attendance.getId()});
                    } catch (Exception ex) {
                       
                    }
                 
              } else {
                    try {
                        payslipJdbcTemplate.update(insertSql, new Object[] {attendance.getId(), attendance.getAbsence(),
                                attendance.getBeingLate(), attendance.getCheckIn(), attendance.getCheckOut(),
                                attendance.getCreatedDate(), attendance.getDeleted(), attendance.getHolidayWork(),
                                attendance.getLeaveEarly(), attendance.getModifiedDate(), 
                                attendance.getOvertime(), attendance.getUpdated(), attendance.getCompanyId(), attendance.getStaffId()});
                    } catch (Exception ex) {

                    }
                 
              }
          }
        }
    }

    public void copyStaffs() {
        String payslipStaffRowCountSql = "select count(id) from staffs";
        int staffRowCount = payslipJdbcTemplate.queryForObject(payslipStaffRowCountSql, Integer.class);
        System.out.println(staffRowCount);
        List<Staff> staffs = new ArrayList<>();
        String insertSql = "insert into staffs (id, casual_days, created, deleted, modified , "
                + "name, paid_holidays, password, retire_date, transporation_expenses, "
                + "username, company_id, grade_id, position_id) "
                + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String updateSql = "update staffs set casual_days = ?, "
                + "created = ?, deleted = ?, modified = ?, "
                + "name = ?, paid_holidays = ?, password = ?, retire_date = ?, transporation_expenses = ?, "
                + "username = ?, company_id = ?, grade_id, position_id = ? "
                + "where id = ?";

        if (staffRowCount <= 0) {
            String kyuyoAttendancesSql = "select * from staffs";
            staffs = kyuyoJdbcTemplate.query(kyuyoAttendancesSql, new StaffRowMapper());
            for (Staff staff : staffs) {
                try {
                    payslipJdbcTemplate.update(insertSql, new Object[] {staff.getId(), staff.getCasualDay(),
                            staff.getCreatedDate(), staff.getDeleted(), staff.getModifiedDate(),
                            staff.getName(), staff.getPaidHoliday(), staff.getPassword(), staff.getRetireDate(),
                            staff.getTransportationFee(), staff.getUsername(), staff.getCompanyId(), staff.getGradeId(),
                            staff.getPositionId()});
                } catch(Exception ex) {
                    
                }
                
            }
        } else {
          Calendar startCalendar = Calendar.getInstance();
          startCalendar.add(Calendar.DAY_OF_MONTH, -1);
          Calendar endCalendar = Calendar.getInstance();
          endCalendar.add(Calendar.DAY_OF_MONTH, 1);
          String kyuyoStaffSql = "select * from staffs where (modified between  ? and ?) or (created between ? and ?)";
          staffs = kyuyoJdbcTemplate.query(kyuyoStaffSql, new Object[] {startCalendar.getTime(), endCalendar.getTime(),
                  startCalendar.getTime(), endCalendar.getTime()}, new StaffRowMapper());
          for (Staff staff : staffs) {
              Staff staffFromDb = null;
              try {
                  staffFromDb = payslipJdbcTemplate.queryForObject("select * from staffs where id = ?", 
                          new Object[] {staff.getId()}, new StaffRowMapper());
              } catch (Exception ex) {
                  // no result
              }
              // update
              if (staffFromDb != null) {
                    try {
                        payslipJdbcTemplate.update(updateSql, new Object[] {staff.getCasualDay(),
                                staff.getCreatedDate(), staff.getDeleted(), staff.getModifiedDate(),
                                staff.getName(), staff.getPaidHoliday(), staff.getPassword(), staff.getRetireDate(),
                                staff.getTransportationFee(), staff.getUsername(), staff.getCompanyId(), staff.getGradeId(),
                                staff.getPositionId(), staff.getId()});
                    } catch (Exception ex) {

                    }
                 
              } else {
                    try {
                        payslipJdbcTemplate.update(insertSql, new Object[] {staff.getId(), staff.getCasualDay(),
                                staff.getCreatedDate(), staff.getDeleted(), staff.getModifiedDate(),
                                staff.getName(), staff.getPaidHoliday(), staff.getPassword(), staff.getRetireDate(),
                                staff.getTransportationFee(), staff.getUsername(), staff.getCompanyId(), staff.getGradeId(),
                                staff.getPositionId()});
                    } catch (Exception ex) {

                    }
                 
              }
          }
        }
    }

    public void copyCompanies() {
        System.out.println("Copying companies ...");
        String payslipCompanyRowCountSql = "select count(id) from companies";
        int companyCount = payslipJdbcTemplate.queryForObject(payslipCompanyRowCountSql, Integer.class);
        System.out.println(companyCount);
        List<Company> companies = new ArrayList<>();
        String insertCompanySql = "insert into companies (id, company_name, created, deleted, mail, modified , name) "
                + "values (?, ?, ?, ?, ?, ?, ?)";
        String companyUpdateSql = "update companies set company_name = ?, "
                + "created = ?, deleted = ?, mail = ?, modified = ?, name = ? where id = ?";

        if (companyCount <= 0) {
            String kyuyoCompaniesSql = "select * from companies";
            companies = kyuyoJdbcTemplate.query(kyuyoCompaniesSql, new CompanyRowMapper());
            for (Company company : companies) {
                try {
                    payslipJdbcTemplate.update(insertCompanySql, new Object[] {company.getId(),
                            company.getCompanyName(), company.getCreatedDate(), 
                            company.getDeleted(), company.getEmail(), company.getModifiedDate(), 
                            company.getName()});
                } catch(Exception ex) {
                    
                }
               
            }
        } else {
          Calendar startCalendar = Calendar.getInstance();
          startCalendar.add(Calendar.DAY_OF_MONTH, -1);
          Calendar endCalendar = Calendar.getInstance();
          endCalendar.add(Calendar.DAY_OF_MONTH, 1);
          String kyuyoCompaniesSql = "select * from companies where (modified between  ? and ?) or (created between ? and ?)";
          companies = kyuyoJdbcTemplate.query(kyuyoCompaniesSql, new Object[] {startCalendar.getTime(), endCalendar.getTime(),
                  startCalendar.getTime(), endCalendar.getTime()}, new CompanyRowMapper());
          for (Company company : companies) {
              System.out.println(company.getName());
              Company companyFromPayslipDb = null;
              try {
                  companyFromPayslipDb = payslipJdbcTemplate.queryForObject("select * from companies where id = ?", 
                          new Object[] {company.getId()}, new CompanyRowMapper());
              } catch (Exception ex) {
                  // no result
              }
              // update
              if (companyFromPayslipDb != null) {
                    try {
                        payslipJdbcTemplate.update(companyUpdateSql, new Object[] {company.getCompanyName(), company.getCreatedDate(),
                                company.getDeleted(), company.getEmail(), company.getModifiedDate(), company.getName(), company.getId()});
                    } catch (Exception ex) {

                    }
                  
              } else {
                    try {
                        payslipJdbcTemplate.update(insertCompanySql, new Object[] {company.getId(),
                                company.getCompanyName(), company.getCreatedDate(), 
                                company.getDeleted(), company.getEmail(), company.getModifiedDate(), 
                                company.getName()});
                    } catch (Exception ex) {

                    }
                 
              }
          }
        }
    }

    public void copyHolidays(){
        System.out.println("Copying holidays ...");
        String payslipHolidayRowCountSql = "select count(id) from holidays";
        int holidayCount = payslipJdbcTemplate.queryForObject(payslipHolidayRowCountSql, Integer.class);
        System.out.println(holidayCount);
        List<Holiday> holidays = new ArrayList<>();
        String insertHolidaySql = "insert into holidays (id, created, day, deleted, holiday_name, modified , company_id) "
                + "values (?, ?, ?, ?, ?, ?, ?)";
        String holidayUpdateSql = "update holidays set created = ?, "
                + "day = ?, deleted = ?, holiday_name = ?, modified = ?, company_id = ? where id = ?";

        if (holidayCount <= 0) {
            String kyuyoHolidaysSql = "select * from holidays";
            holidays = kyuyoJdbcTemplate.query(kyuyoHolidaysSql, new HolidayRowMapper());
            for (Holiday holiday : holidays) {
                try {
                    payslipJdbcTemplate.update(insertHolidaySql, new Object[] {holiday.getId(),
                            holiday.getCreatedDate(), holiday.getDay(), 
                            holiday.getDeleted(), holiday.getHoildayName(), holiday.getModifiedDate(), 
                            holiday.getCompanyId()});
                } catch(Exception ex) {
                    
                }
               
            }
        } else {
          Calendar startCalendar = Calendar.getInstance();
          startCalendar.add(Calendar.DAY_OF_MONTH, -1);
          Calendar endCalendar = Calendar.getInstance();
          endCalendar.add(Calendar.DAY_OF_MONTH, 1);
          String kyuyoHolidaySql = "select * from holidays where (modified between  ? and ?) or (created between ? and ?)";
          holidays = kyuyoJdbcTemplate.query(kyuyoHolidaySql, new Object[] {startCalendar.getTime(), endCalendar.getTime(),
                  startCalendar.getTime(), endCalendar.getTime()}, new HolidayRowMapper());
          for (Holiday holiday : holidays) {
              System.out.println(holiday.getHoildayName());
              Holiday holidayFromPayslipDb = null;
              try {
                  holidayFromPayslipDb = payslipJdbcTemplate.queryForObject("select * from holidays where id = ?", 
                          new Object[] {holiday.getId()}, new HolidayRowMapper());
              } catch (Exception ex) {
                  // no result
              }
              // update
              if (holidayFromPayslipDb != null) {
                    try {
                        payslipJdbcTemplate.update(holidayUpdateSql, new Object[] {holiday.getCreatedDate(), holiday.getDay(),
                                holiday.getDeleted(), holiday.getHoildayName(), holiday.getModifiedDate(), holiday.getCompanyId(), holiday.getId()});
                    } catch (Exception ex) {

                    }
                 
              } else {
                    try {
                        payslipJdbcTemplate.update(insertHolidaySql, new Object[] {holiday.getId(),
                                holiday.getCreatedDate(), holiday.getDay(), holiday.getDeleted(), holiday.getHoildayName(),
                                holiday.getModifiedDate(), holiday.getCompanyId()});
                    } catch (Exception ex) {

                    }
                 
              }
          }
        }
    }

    public void copyGrades(){
        System.out.println("Copying grades ...");
        String payslipHolidayRowCountSql = "select count(id) from grades";
        int gradeCount = payslipJdbcTemplate.queryForObject(payslipHolidayRowCountSql, Integer.class);
        System.out.println(gradeCount);
        List<Grade> grades = new ArrayList<>();
        String insertGradeSql = "insert into grades (id, created, deleted, modified, grade_name, company_id) "
                + "values (?, ?, ?, ?, ?, ?)";
        String gradeUpdateSql = "update grades set created = ?, "
                + "deleted = ?, modified = ?, grade_name = ?, company_id = ? where id = ?";

        if (gradeCount <= 0) {
            String kyuyoGradesSql = "select * from grades";
            grades = kyuyoJdbcTemplate.query(kyuyoGradesSql, new GradeRowMapper());
            for (Grade grade : grades) {
                try {
                    payslipJdbcTemplate.update(insertGradeSql, new Object[] {grade.getId(),
                            grade.getCreatedDate(), grade.getDeleted(), 
                            grade.getModifiedDate(), grade.getName(), 
                            grade.getCompanyId()});
                } catch(Exception ex) {
                    
                }
                
            }
        } else {
          Calendar startCalendar = Calendar.getInstance();
          startCalendar.add(Calendar.DAY_OF_MONTH, -1);
          Calendar endCalendar = Calendar.getInstance();
          endCalendar.add(Calendar.DAY_OF_MONTH, 1);
          String kyuyoGradeSql = "select * from grades where (modified between  ? and ?) or (created between ? and ?)";
          grades = kyuyoJdbcTemplate.query(kyuyoGradeSql, new Object[] {startCalendar.getTime(), endCalendar.getTime(),
                  startCalendar.getTime(), endCalendar.getTime()}, new GradeRowMapper());
          for (Grade grade : grades) {
              System.out.println(grade.getName());
              Grade gradeFromPayslipDb = null;
              try {
                  gradeFromPayslipDb = payslipJdbcTemplate.queryForObject("select * from grades where id = ?", 
                          new Object[] {grade.getId()}, new GradeRowMapper());
              } catch (Exception ex) {
                  // no result
              }
              // update
              if (gradeFromPayslipDb != null) {
                    try {
                        payslipJdbcTemplate.update(gradeUpdateSql, new Object[] {grade.getCreatedDate(), grade.getDeleted(),
                                grade.getModifiedDate(), grade.getName(), grade.getCompanyId(), grade.getId()});
                    } catch (Exception ex) {

                    }
                 
              } else {
                    try {
                        payslipJdbcTemplate.update(insertGradeSql, new Object[] {grade.getId(),
                                grade.getCreatedDate(), grade.getDeleted(),
                                grade.getModifiedDate(), grade.getName(), grade.getCompanyId()});
                    } catch (Exception ex) {

                    }
                  
              }
          }
        }
    }

    public void copyPositions(){
        System.out.println("Copying position ...");
        String payslipPositionRowCountSql = "select count(id) from positions";
        int positionCount = payslipJdbcTemplate.queryForObject(payslipPositionRowCountSql, Integer.class);
        System.out.println(positionCount);
        List<Position> positions = new ArrayList<>();
        String insertPositionSql = "insert into positions (id, created, deleted, modified , position_name, company_id) "
                + "values (?, ?, ?, ?, ?, ?)";
        String positionUpdateSql = "update positions set created = ?, "
                + "deleted = ?, modified = ?, position_name = ?, company_id = ? where id = ?";

        if (positionCount <= 0) {
            String kyuyoPositionsSql = "select * from positions";
            positions = kyuyoJdbcTemplate.query(kyuyoPositionsSql, new PositionRowMapper());
            for (Position position : positions) {
                try {
                    payslipJdbcTemplate.update(insertPositionSql, new Object[] {position.getId(),
                            position.getCreatedDate(), position.getDeleted(), position.getModifiedDate(), position.getName(),
                            position.getCompanyId()});
                } catch(Exception ex) {
                    
                }
               
            }
        } else {
          Calendar startCalendar = Calendar.getInstance();
          startCalendar.add(Calendar.DAY_OF_MONTH, -1);
          Calendar endCalendar = Calendar.getInstance();
          endCalendar.add(Calendar.DAY_OF_MONTH, 1);
          String kyuyoPostionsSql = "select * from positions where (modified between  ? and ?) or (created between ? and ?)";
          positions = kyuyoJdbcTemplate.query(kyuyoPostionsSql, new Object[] {startCalendar.getTime(), endCalendar.getTime(),
                  startCalendar.getTime(), endCalendar.getTime()}, new PositionRowMapper());
          for (Position position : positions) {
              System.out.println(position.getName());
              Position positionFromPayslipDb = null;
              try {
                  positionFromPayslipDb = payslipJdbcTemplate.queryForObject("select * from positions where id = ?", 
                          new Object[] {position.getId()}, new PositionRowMapper());
              } catch (Exception ex) {
                  // no result
              }
              // update
              if (positionFromPayslipDb != null) {
                    try {
                        payslipJdbcTemplate.update(positionUpdateSql, new Object[] {position.getCreatedDate(), position.getDeleted(), position.getModifiedDate(), position.getName(),
                                position.getCompanyId(), position.getId()});
                    } catch (Exception ex) {

                    }
                  
              } else {
                    try {
                        payslipJdbcTemplate.update(insertPositionSql, new Object[] {position.getId(),
                                position.getCreatedDate(), position.getDeleted(), position.getModifiedDate(), position.getName(),
                                position.getCompanyId()});
                    } catch (Exception ex) {

                    }
                  
              }
          }
        }
    }

    @Autowired
    @Qualifier(value = "kyuyoDataSource")
    public DataSource kyuyoDataSource;

    @Autowired
    @Qualifier(value = "payslipDataSource")
    public DataSource payslipDataSource;

    @Autowired
    @Qualifier(value = "kyuyoJdbcTemplate")
    public JdbcTemplate  kyuyoJdbcTemplate;

    @Autowired
    @Qualifier(value = "payslipJdbcTemplate")
    public JdbcTemplate  payslipJdbcTemplate;
    

}
