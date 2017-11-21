package com.frobom.payslip.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Attendance {

    private int id;

    private float beingLate;

    private float leaveEarly;

    private float overtime;

    private float holidayWork;

    private String absence;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date checkIn;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date checkOut;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date modifiedDate;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date createdDate;

    private String deleted;

    private String updated;

    private int companyId;

    private int staffId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getBeingLate() {
        return beingLate;
    }

    public void setBeingLate(float beingLate) {
        this.beingLate = beingLate;
    }

    public float getLeaveEarly() {
        return leaveEarly;
    }

    public void setLeaveEarly(float leaveEarly) {
        this.leaveEarly = leaveEarly;
    }

    public float getOvertime() {
        return overtime;
    }

    public void setOvertime(float overtime) {
        this.overtime = overtime;
    }

    public float getHolidayWork() {
        return holidayWork;
    }

    public void setHolidayWork(float holidayWork) {
        this.holidayWork = holidayWork;
    }

    public String getAbsence() {
        return absence;
    }

    public void setAbsence(String absence) {
        this.absence = absence;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
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

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }
}
