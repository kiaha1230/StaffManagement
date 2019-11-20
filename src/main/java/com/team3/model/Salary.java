package com.team3.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "SALARY")
public class Salary {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	@Column(name = "STAFF_ID")
	private Integer staffId;
	@Column(name = "GROSS_SALARY")
	private Double grossSalary;
	@Column(name = "TAX")
	private Double tax;
	@Column(name = "INSURANCE")
	private Double insurance;
	@Column(name = "NET_SALARY")
	private Double netSalary;
	@Transient
	private String staffName;
	@Transient
	private Double fromGrossSalary;
	@Transient
	private Double toGrossSalary;
	@Transient
	private Double fromNetSalary;
	@Transient
	private Double toNetSalary;
	@Transient
	private Pager pager;

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public Salary(Integer id, Integer staffId, Double grossSalary, Double tax, Double insurance, Double netSalary,
			String staffName, Double fromGrossSalary, Double toGrossSalary, Double fromNetSalary, Double toNetSalary) {
		super();
		this.id = id;
		this.staffId = staffId;
		this.grossSalary = grossSalary;
		this.tax = tax;
		this.insurance = insurance;
		this.netSalary = netSalary;
		this.staffName = staffName;
		this.fromGrossSalary = fromGrossSalary;
		this.toGrossSalary = toGrossSalary;
		this.fromNetSalary = fromNetSalary;
		this.toNetSalary = toNetSalary;
	}

	public Double getFromGrossSalary() {
		return fromGrossSalary;
	}

	public void setFromGrossSalary(Double fromGrossSalary) {
		this.fromGrossSalary = fromGrossSalary;
	}

	public Double getToGrossSalary() {
		return toGrossSalary;
	}

	public void setToGrossSalary(Double toGrossSalary) {
		this.toGrossSalary = toGrossSalary;
	}

	public Double getFromNetSalary() {
		return fromNetSalary;
	}

	public void setFromNetSalary(Double fromNetSalary) {
		this.fromNetSalary = fromNetSalary;
	}

	public Double getToNetSalary() {
		return toNetSalary;
	}

	public void setToNetSalary(Double toNetSalary) {
		this.toNetSalary = toNetSalary;
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

	public Double getGrossSalary() {
		return grossSalary;
	}

	public void setGrossSalary(Double grossSalary) {
		this.grossSalary = grossSalary;
	}

	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}

	public Double getInsurance() {
		return insurance;
	}

	public void setInsurance(Double insurance) {
		this.insurance = insurance;
	}

	public Double getNetSalary() {
		return netSalary;
	}

	public void setNetSalary(Double netSalary) {
		this.netSalary = netSalary;
	}

	public Salary(Integer id, Integer staffId, Double grossSalary, Double tax, Double insurance, Double netSalary) {
		super();
		this.id = id;
		this.staffId = staffId;
		this.grossSalary = grossSalary;
		this.tax = tax;
		this.insurance = insurance;
		this.netSalary = netSalary;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public Salary() {
		super();
	}

}
