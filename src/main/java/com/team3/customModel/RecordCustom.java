package com.team3.customModel;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

public class RecordCustom {
	private Integer id;
	private Boolean type;
	private String reason;
	private Date createDate;
	private String staffName;

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

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public RecordCustom(Integer id, Boolean type, String reason, Date createDate, String staffName) {
		super();
		this.id = id;
		this.type = type;
		this.reason = reason;
		this.createDate = createDate;
		this.staffName = staffName;
	}

	public RecordCustom() {
		super();
	}

}
