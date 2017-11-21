package com.frobom.payslip.dbcopy.entity;

import java.util.Date;

public class BasicSalary {

    private int id;

    private int basicSalary;

    private int positionAllowance;

    private Integer dutyAllowance;

    private Date modifiedDate;

    private Date createdDate;

    private String deleted;

    private int companyId;

    private int staffId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public void setBasicSalary(int basicSalary) {
        this.basicSalary = basicSalary;
    }

    public void setPositionAllowance(int positionAllowance) {
        this.positionAllowance = positionAllowance;
    }

    public Integer getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(Integer basicSalary) {
        this.basicSalary = basicSalary;
    }

    public Integer getPositionAllowance() {
        return positionAllowance;
    }

    public void setPositionAllowance(Integer positionAllowance) {
        this.positionAllowance = positionAllowance;
    }

    public Integer getDutyAllowance() {
        return dutyAllowance;
    }

    public void setDutyAllowance(Integer dutyAllowance) {
        this.dutyAllowance = dutyAllowance;
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

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public int getStaff() {
        return staffId;
    }

    public void setStaff(int staffId) {
        this.staffId = staffId;
    }

}
