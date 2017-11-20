package com.frobom.payslip.copydb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    public void run(String... arg0) throws Exception {
        System.out.println("Started Application...");
        testKyuyo();
        testPayslip();
        System.exit(0);
    }

    private void testKyuyo() throws SQLException {
        Connection kyuyoConnection = kyuyoDataSource.getConnection();
        PreparedStatement statement = kyuyoConnection.prepareStatement("select * from staffs where username = ?");
        statement.setString(1, "0018");
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            String name = resultSet.getString("name");
            System.out.println(name);
        }
    }
    
    private void testPayslip() throws SQLException {
        Connection kyuyoConnection = payslipDataSource.getConnection();
        PreparedStatement statement = kyuyoConnection.prepareStatement("select * from staffs where username = ?");
        statement.setString(1, "0018");
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            String name = resultSet.getString("name");
            System.out.println(name);
        }
    }

    @Autowired
    @Qualifier(value = "kyuyoDataSource")
    public DataSource kyuyoDataSource;

    @Autowired
    @Qualifier(value = "payslipDataSource")
    public DataSource payslipDataSource;
    

}
