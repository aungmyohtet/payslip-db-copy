package com.frobom.payslip.dbcopy;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.frobom.payslip.dbcopy.repository.CompanyRepository;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource("classpath:config.properties")
public class PayslipDatabaseConfig {

    @Value("${payslip.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${payslip.datasource.username}")
    private String user;

    @Value("${payslip.datasource.password}")
    private String password;

    @Value("${payslip.datasource.url}")
    private String url;

    @Primary
    @Bean(name = "payslipDataSource")
    public DataSource payslipDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        dataSource.setJdbcUrl(url);
        dataSource.setMaximumPoolSize(30);
        dataSource.setMinimumIdle(30);
        return dataSource;
    }

    @Bean(name = "payslipJdbcTemplate")
    public JdbcTemplate  payslipJdbcTemplate() {
        return new JdbcTemplate(payslipDataSource());
    }

    @Bean(name = "payslipCompanyRepository") 
    public CompanyRepository payslipCompanyRepository() {
        return new CompanyRepository(payslipJdbcTemplate());
    }
}
