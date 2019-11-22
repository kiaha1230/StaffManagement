package com.team3.model;

import java.sql.Time;
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
@Table(name = "ATTENDANCE")
public class Attendance {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "STAFF_ID")
	private Integer staffId;
	@Column(name = "ATTENDANCE_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date attendanceDate;
	@Column(name = "CHECK_IN_TIME")
	private Time checkinTime;
	@Column(name = "CHECK_OUT_TIME")
	private Time checkoutTime;
	@Transient
	private String staffName;
	@Transient
	private Date fromAttendanceDate;
	@Transient
	private Date toAttendanceDate;
	@Transient
	private Pager pager;

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public Date getFromAttendanceDate() {
		return fromAttendanceDate;
	}

	public void setFromAttendanceDate(Date fromAttendanceDate) {
		this.fromAttendanceDate = fromAttendanceDate;
	}

	public Date getToAttendanceDate() {
		return toAttendanceDate;
	}

	public void setToAttendanceDate(Date toAttendanceDate) {
		this.toAttendanceDate = toAttendanceDate;
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

	public Date getAttendanceDate() {
		return attendanceDate;
	}

	public void setAttendanceDate(Date attendanceDate) {
		this.attendanceDate = attendanceDate;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public Attendance() {
		super();
	}

	public Time getCheckinTime() {
		return checkinTime;
	}

	public void setCheckinTime(Time checkinTime) {
		this.checkinTime = checkinTime;
	}

	public Time getCheckoutTime() {
		return checkoutTime;
	}

	public void setCheckoutTime(Time checkoutTime) {
		this.checkoutTime = checkoutTime;
	}

}
