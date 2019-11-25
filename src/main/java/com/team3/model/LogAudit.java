package com.team3.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "LOG_AUDIT")
public class LogAudit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	@Column(name = "ACTION_DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date actionDatetime;
	@Column(name = "ACCOUNT_ID")
	private Integer accountId;
	@Column(name = "TABLE_NAME")
	private String tableName;
	@Column(name = "ACTION_TYPE")
	private Integer actionType;

	@Transient
	private Pager pager;
	@Transient
	private String staffName;
	@Transient
	private Date fromDate;
	@Transient
	private Date toDate;

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

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
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

	public Date getActionDatetime() {
		return actionDatetime;
	}

	public void setActionDatetime(Date actionDatetime) {
		this.actionDatetime = actionDatetime;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Integer getActionType() {
		return actionType;
	}

	public void setActionType(Integer actionType) {
		this.actionType = actionType;
	}

	public LogAudit(Integer id, Date actionDatetime, Integer accountId, String tableName, Integer actionType) {
		super();
		this.id = id;
		this.actionDatetime = actionDatetime;
		this.accountId = accountId;
		this.tableName = tableName;
		this.actionType = actionType;
	}

	public LogAudit() {
		super();
	}

}
