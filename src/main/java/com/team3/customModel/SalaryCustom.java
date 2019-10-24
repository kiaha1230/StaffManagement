package com.team3.customModel;

public class SalaryCustom {
	private Integer id;
	private Integer staffId;
	private String staffName;
	private Double grossSalary;
	private Double tax;
	private Double insurance;
	private Double netSalary;
	private Double fromGrossSalary;
	private Double toGrossSalary;
	private Double fromNetSalary;
	private Double toNetSalary;
	private Integer SalGrade;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
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

	public SalaryCustom() {
		super();
	}

	public Integer getSalGrade() {
		return SalGrade;
	}

	public void setSalGrade(Integer salGrade) {
		SalGrade = salGrade;
	}

	public SalaryCustom(Integer id, Integer staffId, String staffName, Double grossSalary, Double tax, Double insurance,
			Double netSalary, Double fromGrossSalary, Double toGrossSalary, Double fromNetSalary, Double toNetSalary,
			Integer salGrade) {
		super();
		this.id = id;
		this.staffId = staffId;
		this.staffName = staffName;
		this.grossSalary = grossSalary;
		this.tax = tax;
		this.insurance = insurance;
		this.netSalary = netSalary;
		this.fromGrossSalary = fromGrossSalary;
		this.toGrossSalary = toGrossSalary;
		this.fromNetSalary = fromNetSalary;
		this.toNetSalary = toNetSalary;
		SalGrade = salGrade;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

}
