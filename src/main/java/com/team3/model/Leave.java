package com.team3.model;

import java.util.Date;

import javax.management.loading.PrivateClassLoader;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "LEAVE")
public class Leave {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "STAFF_ID")
	private Integer staffId;
	@Column(name = "DATE")
	private Date date;
	@Column(name = "REASON")
	private String reason;
	@Column(name = "STATUS")
	private Boolean status;
	@Transient
	private String staffName;
	@Transient
	private Date fromDate;
	@Transient
	private Date toDate;

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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public Leave(Integer id, Integer staffId, Date date, String reason, Boolean status, String staffName) {
		super();
		this.id = id;
		this.staffId = staffId;
		this.date = date;
		this.reason = reason;
		this.status = status;
		this.staffName = staffName;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Leave(Integer id, Integer staffId, Date date, String reason, Boolean status, String staffName, Date fromDate,
			Date toDate) {
		super();
		this.id = id;
		this.staffId = staffId;
		this.date = date;
		this.reason = reason;
		this.status = status;
		this.staffName = staffName;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}

	public Leave() {
		super();
	}

}
