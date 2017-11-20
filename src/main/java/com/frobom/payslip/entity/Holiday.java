package com.frobom.payslip.entity;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

public class Holiday {

    protected int id;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date day;

    private int companyId;

    private String hoildayName;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date modifiedDate;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date createdDate;

    private String deleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getHoildayName() {
        return hoildayName;
    }

    public void setHoildayName(String hoildayName) {
        this.hoildayName = hoildayName;
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
}
