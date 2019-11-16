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
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;

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
	@CreationTimestamp
	private Date attendanceDate;
	@Column(name = "CHECK_IN_TIME")
	private Time checkInTime;
	@Column(name = "CHECK_OUT_TIME")
	private Time checkOutTime;
	@Transient
	private String staffName;
	@Transient
	private Date fromAttendanceDate;
	@Transient
	private Date toAttendanceDate;


	

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

	public Attendance(Integer id, Integer staffId, Date attendanceDate, Time checkInTime, Time checkOutTime,
			String staffName, Date fromAttendanceDate, Date toAttendanceDate) {
		super();
		this.id = id;
		this.staffId = staffId;
		this.attendanceDate = attendanceDate;
		this.checkInTime = checkInTime;
		this.checkOutTime = checkOutTime;
		this.staffName = staffName;
		this.fromAttendanceDate = fromAttendanceDate;
		this.toAttendanceDate = toAttendanceDate;
	}

	public Attendance(Integer id, Integer staffId, Date attendanceDate, Time checkInTime, Time checkOutTime,
			String staffName) {
		super();
		this.id = id;
		this.staffId = staffId;
		this.attendanceDate = attendanceDate;
		this.checkInTime = checkInTime;
		this.checkOutTime = checkOutTime;
		this.staffName = staffName;
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
