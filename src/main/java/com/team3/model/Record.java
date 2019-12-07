package com.team3.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "RECORD")
public class Record {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	@Column(name = "TYPE")
	private Boolean type;
	@Column(name = "REASON")
	private String reason;
	@Column(name = "CREATE_DATE")
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	@Column(name = "STAFF_ID")
	private Integer staffId;
	@Transient
	private String staffName;
	@Transient
	private Date fromCreateDate;
	@Transient
	private Date toCreateDate;
	@Column(name = "BONUS")
	private Double bonus;
	@Transient
	private Pager pager;
	@Transient
	private String staffCode;

	public String getStaffCode() {
		return staffCode;
	}

	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public Double getBonus() {
		return bonus;
	}

	public void setBonus(Double bonus) {
		this.bonus = bonus;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public Record(Integer id, Boolean type, String reason, Date createDate, Integer staffId, String staffName,
			Date fromCreateDate, Date toCreateDate) {
		super();
		this.id = id;
		this.type = type;
		this.reason = reason;
		this.createDate = createDate;
		this.staffId = staffId;
		this.staffName = staffName;
		this.fromCreateDate = fromCreateDate;
		this.toCreateDate = toCreateDate;
	}

	public Date getFromCreateDate() {
		return fromCreateDate;
	}

	public void setFromCreateDate(Date fromCreateDate) {
		this.fromCreateDate = fromCreateDate;
	}

	public Date getToCreateDate() {
		return toCreateDate;
	}

	public void setToCreateDate(Date toCreateDate) {
		this.toCreateDate = toCreateDate;
	}

	public Boolean getType() {
		return type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean isType() {
		return type;
	}

	public void setType(Boolean type) {
		this.type = type;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Record(Integer id, Boolean type, String reason, Date createDate, Integer staffId) {
		super();
		this.id = id;
		this.type = type;
		this.reason = reason;
		this.createDate = createDate;
		this.staffId = staffId;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public Record() {
		super();
	}

}
