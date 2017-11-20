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

import com.frobom.payslip.entity.Company;
import com.frobom.payslip.entity.Staff;
import com.frobom.payslip.rowmapper.CompanyRowMapper;
import com.frobom.payslip.rowmapper.StaffRowMapper;

@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    public void run(String... arg0) throws Exception {
        System.out.println("Started Application...");
        copyCompanies();
        System.exit(0);
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
                payslipJdbcTemplate.update(insertCompanySql, new Object[] {company.getId(),
                        company.getCompanyName(), company.getCreatedDate(), 
                        company.getDeleted(), company.getEmail(), company.getModifiedDate(), 
                        company.getName()});
            }
        } else {
          Calendar startCalendar = Calendar.getInstance();
          startCalendar.add(Calendar.DAY_OF_MONTH, -1);
          Calendar endCalendar = Calendar.getInstance();
          endCalendar.add(Calendar.DAY_OF_MONTH, 1);
          String kyuyoCompaniesSql = "select * from companies where modified between  ? and ?";
          companies = kyuyoJdbcTemplate.query(kyuyoCompaniesSql, new Object[] {startCalendar.getTime(), endCalendar.getTime()}, new CompanyRowMapper());
          System.out.println("After modified date " + companies.size());
          for (Company company : companies) {
              System.out.println(company.getName());
              Company companyFromPayslipDb = payslipJdbcTemplate.queryForObject("select * from companies where id = ?", 
                      new Object[] {company.getId()}, new CompanyRowMapper());
              // update
              if (companyFromPayslipDb != null) {
                  payslipJdbcTemplate.update(companyUpdateSql, new Object[] {company.getCompanyName(), company.getCreatedDate(),
                          company.getDeleted(), company.getEmail(), company.getModifiedDate(), company.getName(), company.getId()});
              } else {
                  payslipJdbcTemplate.update(insertCompanySql, new Object[] {company.getId(),
                          company.getCompanyName(), company.getCreatedDate(), 
                          company.getDeleted(), company.getEmail(), company.getModifiedDate(), 
                          company.getName()});
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
