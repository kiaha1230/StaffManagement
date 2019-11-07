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

@Entity
@Table(name = "LOG_DETAIL")
public class LogDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	@Column(name = "LOG_AUDIT_ID")
	private Integer logAuditId;
	@Column(name = "COLUMN_NAME")
	private String columnName;
	@Column(name = "OLD_VALUE")
	private String oldValue;
	@Column(name = "NEW_VALUE")
	private String newValue;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLogAuditId() {
		return logAuditId;
	}

	public void setLogAuditId(Integer logAuditId) {
		this.logAuditId = logAuditId;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getOldValue() {
		return oldValue;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	public LogDetail(Integer id, Integer logAuditId, String columnName, String oldValue, String newValue) {
		super();
		this.id = id;
		this.logAuditId = logAuditId;
		this.columnName = columnName;
		this.oldValue = oldValue;
		this.newValue = newValue;
	}

	public LogDetail() {
		super();
	}

}
