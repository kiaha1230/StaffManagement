package com.team3.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "PAYROLL")
public class Payroll {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	@Column(name = "STAFF_ID")
	private Integer staffId;
	@Column(name = "DATETIME")
	private Date datetime;
	@Column(name = "GROSS_SAL")
	private Double grossSal;
	@Column(name = "NET_SAL")
	private Double netSal;
	@Column(name = "BONUS")
	private Double bonus;
	@Column(name = "LEAVE_DATE")
	private Integer leaveDate;
	@Column(name = "ALLOWANCE")
	private Double allowance;
	@Column(name = "NET_PAY")
	private Double netPay;
	@Transient
	private String staffName;
	@Transient
	private Pager pager;
	@Transient
	private Double fromNetPay;
	@Transient
	private Double toNetPay;
	@Transient
	private Integer month;
	@Transient
	private Integer year;
	@Transient
	private String staffCode;
	
	

	public String getStaffCode() {
		return staffCode;
	}

	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public Double getFromNetPay() {
		return fromNetPay;
	}

	public void setFromNetPay(Double fromNetPay) {
		this.fromNetPay = fromNetPay;
	}

	public Double getToNetPay() {
		return toNetPay;
	}

	public void setToNetPay(Double toNetPay) {
		this.toNetPay = toNetPay;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public Double getGrossSal() {
		return grossSal;
	}

	public void setGrossSal(Double grossSal) {
		this.grossSal = grossSal;
	}

	public Double getNetSal() {
		return netSal;
	}

	public void setNetSal(Double netSal) {
		this.netSal = netSal;
	}

	public Double getBonus() {
		return bonus;
	}

	public void setBonus(Double bonus) {
		this.bonus = bonus;
	}

	public Integer getLeaveDate() {
		return leaveDate;
	}

	public void setLeaveDate(Integer leaveDate) {
		this.leaveDate = leaveDate;
	}

	public Double getAllowance() {
		return allowance;
	}

	public void setAllowance(Double allowance) {
		this.allowance = allowance;
	}

	public Double getNetPay() {
		return netPay;
	}

	public void setNetPay(Double netPay) {
		this.netPay = netPay;
	}

	public Payroll() {
		super();
	}

}
