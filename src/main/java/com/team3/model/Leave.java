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
	private Date leaveDate;
	@Column(name = "REASON")
	private String reason;
	@Column(name = "STATUS")
	private Boolean status;
	@Transient
	private String staffName;
	@Transient
	private Date fromLeaveDate;
	@Transient
	private Date toLeaveDate;

	public Date getLeaveDate() {
		return leaveDate;
	}

	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
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

	public Leave(Integer id, Integer staffId, Date leaveDate, String reason, Boolean status, String staffName,
			Date fromLeaveDate, Date toLeaveDate) {
		super();
		this.id = id;
		this.staffId = staffId;
		this.leaveDate = leaveDate;
		this.reason = reason;
		this.status = status;
		this.staffName = staffName;
		this.fromLeaveDate = fromLeaveDate;
		this.toLeaveDate = toLeaveDate;
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
