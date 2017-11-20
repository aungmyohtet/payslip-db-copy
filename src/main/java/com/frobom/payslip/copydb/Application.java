package com.frobom.payslip.copydb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        if (companyCount <= 0) {
            
        } else {
            String kyuyoCompaniesSql = "select * from companies";
            companies = kyuyoJdbcTemplate.query(kyuyoCompaniesSql, new CompanyRowMapper());
            for (Company company : companies) {
                System.out.println(company.getName());
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
