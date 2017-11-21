package com.frobom.payslip.dbcopy.repository;

import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.frobom.payslip.dbcopy.entity.Company;
import com.frobom.payslip.dbcopy.rowmapper.CompanyRowMapper;

public class CompanyRepository {

    private JdbcTemplate jdbcTemplate;
    
    public CompanyRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(Company company) {
        String sql = "insert into companies (id, company_name, created, deleted, mail, modified , name) "
                + "values (?, ?, ?, ?, ?, ?, ?)";
        try {
            this.jdbcTemplate.update(sql, new Object[] {company.getId(),
                    company.getCompanyName(), company.getCreatedDate(), 
                    company.getDeleted(), company.getEmail(), company.getModifiedDate(), 
                    company.getName()});
        } catch(Exception ex) {
            // ignore
        }
    }

    public void update(Company company) {
        String sql = "update companies set company_name = ?, "
                + "created = ?, deleted = ?, mail = ?, modified = ?, name = ? where id = ?";
        try {
            this.jdbcTemplate.update(sql, new Object[] {company.getCompanyName(), company.getCreatedDate(),
                    company.getDeleted(), company.getEmail(), company.getModifiedDate(), company.getName(), company.getId()});
        } catch (Exception ex) {
            // ignore
        }
    }

    public List<Company> findAll() {
        String sql = "select * from companies";
        List<Company> companies = this.jdbcTemplate.query(sql, new CompanyRowMapper());
        return companies;
    }

    public List<Company> findBetweenCreatedOrModifiedDates(Date startDate, Date endDate) {
        String sql = "select * from companies where (modified between  ? and ?) or (created between ? and ?)";
        List<Company> companies = this.jdbcTemplate.query(sql, new Object[] {startDate, endDate,
                startDate, endDate}, new CompanyRowMapper());
        return companies;
    }

    public Integer getRowCount() {
        String sql = "select count(id) from companies";
        int companyCount = this.jdbcTemplate.queryForObject(sql, Integer.class);
        return companyCount;
    }

    public Company findById(int id) {
        String sql = "select * from companies where id = ?";
        Company company = null;
        try {
            company = this.jdbcTemplate.queryForObject(sql, 
                    new Object[] {id}, new CompanyRowMapper());
        } catch (Exception ex) {
            // no result
        }
        return company;
    }
}
