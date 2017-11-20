package com.frobom.payslip.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Staff {

    protected int id;

    private String username;

    private String password;

    private String confirmedPassword;

    private String name;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date modifiedDate;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date createdDate;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date retireDate;

    private String deleted;

    private int paidHoliday;

    private int casualDay;

    private int transportationFee;

    private int companyId;

    private int gradeId;

    private int positionId;

    public Date getRetireDate() {
        return retireDate;
    }

    public void setRetireDate(Date retireDate) {
        this.retireDate = retireDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public int getPaidHoliday() {
        return paidHoliday;
    }

    public void setPaidHoliday(int paidHoliday) {
        this.paidHoliday = paidHoliday;
    }

    public int getCasualDay() {
        return casualDay;
    }

    public void setCasualDay(int casualDay) {
        this.casualDay = casualDay;
    }

    public int getTransportationFee() {
        return transportationFee;
    }

    public void setTransportationFee(int transportationFee) {
        this.transportationFee = transportationFee;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    public void setId(int id) {
        this.id = id;
    }

}
