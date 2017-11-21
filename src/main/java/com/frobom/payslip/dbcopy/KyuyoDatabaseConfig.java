package com.frobom.payslip.dbcopy;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.frobom.payslip.dbcopy.repository.CompanyRepository;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource("classpath:config.properties")
public class KyuyoDatabaseConfig {

    @Value("${kyuyo.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${kyuyo.datasource.username}")
    private String user;

    @Value("${kyuyo.datasource.password}")
    private String password;

    @Value("${kyuyo.datasource.url}")
    private String url;

    @Bean(name = "kyuyoDataSource")
    public DataSource kyuyoDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        dataSource.setJdbcUrl(url);
        dataSource.setMaximumPoolSize(30);
        dataSource.setMinimumIdle(30);
        return dataSource;
    }

    @Bean(name = "kyuyoJdbcTemplate")
    public JdbcTemplate  kyuyoJdbcTemplate() {
        return new JdbcTemplate(kyuyoDataSource());
    }

    @Bean(name = "kyuyoCompanyRepository") 
    public CompanyRepository kyuyoCompanyRepository() {
        return new CompanyRepository(kyuyoJdbcTemplate());
    }
}
