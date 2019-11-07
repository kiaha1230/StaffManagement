package com.team3.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SALGRADE")
public class Salgrade {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	@Column(name = "GRADE")
	private Integer salGrade;
	@Column(name = "LOSAL")
	private Double losal;
	@Column(name = "HISAL")
	private Double hisal;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSalGrade() {
		return salGrade;
	}

	public void setSalGrade(Integer salGrade) {
		this.salGrade = salGrade;
	}

	public Double getLosal() {
		return losal;
	}

	public void setLosal(Double losal) {
		this.losal = losal;
	}

	public Double getHisal() {
		return hisal;
	}

	public void setHisal(Double hisal) {
		this.hisal = hisal;
	}

	public Salgrade(Integer id, Integer salGrade, Double losal, Double hisal) {
		super();
		this.id = id;
		this.salGrade = salGrade;
		this.losal = losal;
		this.hisal = hisal;
	}

}
