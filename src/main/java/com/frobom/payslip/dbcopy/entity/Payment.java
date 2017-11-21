package com.frobom.payslip.dbcopy.entity;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

public class Payment{

    private int id;

    private Date payDate;

    private int bonus;

    private int adjustment;

    private int incomeTaxRepay;

    private int severencePay;

    private int remainingDay;

    private int carryingPreviousExtraAmount;

    private int carryingNextExtraAmount;

    private int socialInsurancePreminum;

    private int incomeTax;

    private int employeeDormitoryFee;

    private int taxable;

    private int nontaxable;

    private int totalPayment;

    private int totalReductionOfPayment;

    private int totalDeduction;

    private int totalSalary;

    private int confirmFlag;

    private int basicSalary;

    private String gradeName;

    private String positionName;

    private int positionAllowance;

    private int dutyAllowance;

    private int loan;

    private int attendance;

    private int absence;

    private int takeHoliday;

    private int caualLeave;

    private int specialLeave;

    private double beingLate;

    private double earlyReturn;

    private double overTime;

    private double holidayWork;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date modifiedDate;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date createdDate;

    private String deleted;

    private int companyId;

    private int staffId;

    private double rateByHour;

    private int absenceReduce;

    private double totalRate;

    private double currentRate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCurrentRate(double currentRate) {
        this.currentRate = currentRate;
    }

    public int getAbsenceReduce() {
        return absenceReduce;
    }

    public void setAbsenceReduce(int absenceReduce) {
        this.absenceReduce = absenceReduce;
    }

    public double getTotalRate() {
        return totalRate;
    }

    public void setTotalRate(double totalRate) {
        this.totalRate = totalRate;
    }

    public double getCurrentRate() {
        return currentRate;
    }

    public int getTotalReductionOfPayment() {
        return totalReductionOfPayment;
    }

    public void setTotalReductionOfPayment(int totalReductionOfPayment) {
        this.totalReductionOfPayment = totalReductionOfPayment;
    }

    public int getTotalDeduction() {
        return totalDeduction;
    }

    public void setTotalDeduction(int totalDeduction) {
        this.totalDeduction = totalDeduction;
    }

    public int getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(int totalSalary) {
        this.totalSalary = totalSalary;
    }

    public int getCarryingPreviousExtraAmount() {
        return carryingPreviousExtraAmount;
    }

    public void setCarryingPreviousExtraAmount(int carryingPreviousExtraAmount) {
        this.carryingPreviousExtraAmount = carryingPreviousExtraAmount;
    }

    public int getCarryingNextExtraAmount() {
        return carryingNextExtraAmount;
    }

    public void setCarryingNextExtraAmount(int carryingNextExtraAmount) {
        this.carryingNextExtraAmount = carryingNextExtraAmount;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public int getAdjustment() {
        return adjustment;
    }

    public void setAdjustment(int adjustment) {
        this.adjustment = adjustment;
    }

    public int getIncomeTaxRepay() {
        return incomeTaxRepay;
    }

    public void setIncomeTaxRepay(int incomeTaxRepay) {
        this.incomeTaxRepay = incomeTaxRepay;
    }

    public int getSeverencePay() {
        return severencePay;
    }

    public void setSeverencePay(int severencePay) {
        this.severencePay = severencePay;
    }

    public int getRemainingDay() {
        return remainingDay;
    }

    public void setRemainingDay(int remainingDay) {
        this.remainingDay = remainingDay;
    }

    public int getSocialInsurancePreminum() {
        return socialInsurancePreminum;
    }

    public void setSocialInsurancePreminum(int socialInsurancePreminum) {
        this.socialInsurancePreminum = socialInsurancePreminum;
    }

    public int getIncomeTax() {
        return incomeTax;
    }

    public void setIncomeTax(int incomeTax) {
        this.incomeTax = incomeTax;
    }

    public int getEmployeeDormitoryFee() {
        return employeeDormitoryFee;
    }

    public void setEmployeeDormitoryFee(int employeeDormitoryFee) {
        this.employeeDormitoryFee = employeeDormitoryFee;
    }

    public int getTaxable() {
        return taxable;
    }

    public void setTaxable(int taxable) {
        this.taxable = taxable;
    }

    public int getNontaxable() {
        return nontaxable;
    }

    public void setNontaxable(int nontaxable) {
        this.nontaxable = nontaxable;
    }

    public int getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(int totalPayment) {
        this.totalPayment = totalPayment;
    }

    public int getConfirmFlag() {
        return confirmFlag;
    }

    public void setConfirmFlag(int confirmFlag) {
        this.confirmFlag = confirmFlag;
    }

    public int getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(int basicSalary) {
        this.basicSalary = basicSalary;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public int getPositionAllowance() {
        return positionAllowance;
    }

    public void setPositionAllowance(int positionAllowance) {
        this.positionAllowance = positionAllowance;
    }

    public int getDutyAllowance() {
        return dutyAllowance;
    }

    public void setDutyAllowance(int dutyAllowance) {
        this.dutyAllowance = dutyAllowance;
    }

    public int getLoan() {
        return loan;
    }

    public void setLoan(int loan) {
        this.loan = loan;
    }

    public int getAttendance() {
        return attendance;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }

    public int getAbsence() {
        return absence;
    }

    public void setAbsence(int absence) {
        this.absence = absence;
    }

    public int getTakeHoliday() {
        return takeHoliday;
    }

    public void setTakeHoliday(int takeHoliday) {
        this.takeHoliday = takeHoliday;
    }

    public int getCaualLeave() {
        return caualLeave;
    }

    public void setCaualLeave(int caualLeave) {
        this.caualLeave = caualLeave;
    }

    public int getSpecialLeave() {
        return specialLeave;
    }

    public void setSpecialLeave(int specialLeave) {
        this.specialLeave = specialLeave;
    }

    public double getBeingLate() {
        return beingLate;
    }

    public void setBeingLate(double beingLate) {
        this.beingLate = beingLate;
    }

    public double getEarlyReturn() {
        return earlyReturn;
    }

    public void setEarlyReturn(double earlyReturn) {
        this.earlyReturn = earlyReturn;
    }

    public double getOverTime() {
        return overTime;
    }

    public void setOverTime(double overTime) {
        this.overTime = overTime;
    }

    public double getHolidayWork() {
        return holidayWork;
    }

    public void setHolidayWork(double holidayWork) {
        this.holidayWork = holidayWork;
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

    public double getRateByHour() {
        return rateByHour;
    }

    public void setRateByHour(double rateByHour) {
        this.rateByHour = rateByHour;
    }

}
