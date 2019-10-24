package com.team3.model;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "ATTENDANCE")
public class Attendance {
	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Integer id;
	@Column(name = "STAFF_ID")
	private Integer staffId;
	@Column(name = "ATTENDANCE_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date attendanceDate;
	@Column(name = "CHECK_IN_TIME")
	private Time checkInTime;
	@Column(name = "CHECK_OUT_TIME")
	private Time checkOutTime;

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

	public Time getCheckInTime() {
		return checkInTime;
	}

	public void setCheckInTime(Time checkInTime) {
		this.checkInTime = checkInTime;
	}

	public Time getCheckOutTime() {
		return checkOutTime;
	}

	public void setCheckOutTime(Time checkOutTime) {
		this.checkOutTime = checkOutTime;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public Attendance(Integer id, Integer staffId, Date attendanceDate, Time checkInTime, Time checkOutTime) {
		super();
		this.id = id;
		this.staffId = staffId;
		this.attendanceDate = attendanceDate;
		this.checkInTime = checkInTime;
		this.checkOutTime = checkOutTime;
	}

	public Attendance() {
		super();
	}

}
