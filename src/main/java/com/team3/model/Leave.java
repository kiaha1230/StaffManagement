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
	@Column(name = "FROM_DATE")
	private Date fromDate;
	@Column(name = "TO_DATE")
	private Date toDate;
	@Column(name = "REASON")
	private String reason;
	@Column(name = "STATUS")
	private Boolean status;
	@Column(name = "ACCEPT")
	private Integer accept;
	@Transient
	private String staffName;
	@Transient
	private Date fromLeaveDate;
	@Transient
	private Date toLeaveDate;
	@Transient
	private String staffCode;

	public String getStaffCode() {
		return staffCode;
	}

	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}

	@Transient
	private Pager pager;

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

	public Integer getAccept() {
		return accept;
	}

	public void setAccept(Integer accept) {
		this.accept = accept;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
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

	public Date getFromLeaveDate() {
		return fromLeaveDate;
	}

	public void setFromLeaveDate(Date fromLeaveDate) {
		this.fromLeaveDate = fromLeaveDate;
	}

	public Date getToLeaveDate() {
		return toLeaveDate;
	}

	public void setToLeaveDate(Date toLeaveDate) {
		this.toLeaveDate = toLeaveDate;
	}

	public Leave() {
		super();
	}

}
