package com.frobom.payslip.dbcopy.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Loan {

    private int id;

    private Integer totalLoan;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date dataOfBorrowingLoan;

    private Integer deductionAmount;

    private Integer remainingLoan;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date modifiedDate;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date createdDate;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date reducedDate = null;

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

    public Date getReducedDate() {
        return reducedDate;
    }

    public void setReducedDate(Date reducedDate) {
        this.reducedDate = reducedDate;
    }

    public Integer getTotalLoan() {
        return totalLoan;
    }

    public void setTotalLoan(Integer totalLoan) {
        this.totalLoan = totalLoan;
    }

    public Integer getDeductionAmount() {
        return deductionAmount;
    }

    public void setDeductionAmount(Integer deductionAmount) {
        this.deductionAmount = deductionAmount;
    }

    public Integer getRemainingLoan() {
        return remainingLoan;
    }

    public void setRemainingLoan(Integer remainingLoan) {
        this.remainingLoan = remainingLoan;
    }

    public Date getDataOfBorrowingLoan() {
        return dataOfBorrowingLoan;
    }

    public void setDataOfBorrowingLoan(Date dataOfBorrowingLoan) {
        this.dataOfBorrowingLoan = dataOfBorrowingLoan;
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

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

}
