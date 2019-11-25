package com.team3.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team3.customModel.AccountCustom;

@Entity
@Table(name = "ACCOUNT")
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	@Column(name = "USERNAME")
	private String username;
	@Column(name = "PASSWORD")
	private String password;
	@Column(name = "CREATE_DATE")
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	@Column(name = "STAFF_ID")
	private Integer staffId;
	@Column(name = "ACCOUNT_ROLE")
	private Integer accountRole;

	@Transient
	@Temporal(TemporalType.TIMESTAMP)
	private Date fromCreateDate;
	@Transient
	@Temporal(TemporalType.TIMESTAMP)
	private Date toCreateDate;
	@Transient
	private String staffName;
	@Transient
	private Pager pager;
	@Transient
	private String staffCode;

	public String getStaffCode() {
		return staffCode;
	}

	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}

	public Account(Integer id, String username, String password, Date createDate, Integer staffId, Integer accountRole,
			Date fromCreateDate, Date toCreateDate, String staffName, Pager pager) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.createDate = createDate;
		this.staffId = staffId;
		this.accountRole = accountRole;
		this.fromCreateDate = fromCreateDate;
		this.toCreateDate = toCreateDate;
		this.staffName = staffName;
		this.pager = pager;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
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

	public Date getFromCreateDate() {
		return fromCreateDate;
	}

	public void setFromCreateDate(Date fromCreateDate) {
		this.fromCreateDate = fromCreateDate;
	}

	public Date getToCreateDate() {
		return toCreateDate;
	}

	public void setToCreateDate(Date toCreateDate) {
		this.toCreateDate = toCreateDate;
	}

	public Integer getAccountRole() {
		return accountRole;
	}

	public void setAccountRole(Integer accountRole) {
		this.accountRole = accountRole;
	}

	public Account() {
		this.createDate = new Date();
	}

}
