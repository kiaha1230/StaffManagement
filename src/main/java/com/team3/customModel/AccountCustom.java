package com.team3.customModel;

import java.util.Date;

public class AccountCustom {
	private Integer id;
	private String username;
	private String password;
	private Date createDate;
	private Date fromDate;
	private Date toDate;
	private Integer staffId;
	private Boolean accountRole;
	private String staffName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public Boolean getAccountRole() {
		return accountRole;
	}

	public void setAccountRole(Boolean accountRole) {
		this.accountRole = accountRole;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public AccountCustom(Integer id, String username, String password, Date createDate, Date fromDate, Date toDate,
			Integer staffId, Boolean accountRole, String staffName) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.createDate = createDate;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.staffId = staffId;
		this.accountRole = accountRole;
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

	public AccountCustom() {

	}

}
