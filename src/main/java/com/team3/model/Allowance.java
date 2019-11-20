package com.team3.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "ALLOWANCE")
public class Allowance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	@Column(name = "STAFF_ID")
	private Integer staffId;
	@Column(name = "TRAVEL_ALLOWANCE")
	private Double travelAllowance;
	@Column(name = "DEVICE_ALLOWANCE")
	private Double deviceAllowance;
	@Column(name = "MEAL_ALLOWANCE")
	private Double mealAllowance;
	@Transient
	private String staffName;
	@Transient
	private Double fromTravel;
	@Transient
	private Double toTravel;
	@Transient
	private Double fromDevice;
	@Transient
	private Double toDevice;
	@Transient
	private Double fromMeal;
	@Transient
	private Double toMeal;
	@Transient
	private Pager pager;
	
	

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

	public Double getTravelAllowance() {
		return travelAllowance;
	}

	public void setTravelAllowance(Double travelAllowance) {
		this.travelAllowance = travelAllowance;
	}

	public Double getDeviceAllowance() {
		return deviceAllowance;
	}

	public void setDeviceAllowance(Double deviceAllowance) {
		this.deviceAllowance = deviceAllowance;
	}

	public Double getMealAllowance() {
		return mealAllowance;
	}

	public void setMealAllowance(Double mealAllowance) {
		this.mealAllowance = mealAllowance;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public Allowance(Integer id, Integer staffId, Double travelAllowance, Double deviceAllowance, Double mealAllowance,
			String staffName) {
		super();
		this.id = id;
		this.staffId = staffId;
		this.travelAllowance = travelAllowance;
		this.deviceAllowance = deviceAllowance;
		this.mealAllowance = mealAllowance;
		this.staffName = staffName;
	}

	public Allowance() {
		super();
	}

	public Double getFromTravel() {
		return fromTravel;
	}

	public void setFromTravel(Double fromTravel) {
		this.fromTravel = fromTravel;
	}

	public Double getToTravel() {
		return toTravel;
	}

	public void setToTravel(Double toTravel) {
		this.toTravel = toTravel;
	}

	public Double getFromDevice() {
		return fromDevice;
	}

	public void setFromDevice(Double fromDevice) {
		this.fromDevice = fromDevice;
	}

	public Double getToDevice() {
		return toDevice;
	}

	public void setToDevice(Double toDevice) {
		this.toDevice = toDevice;
	}

	public Double getFromMeal() {
		return fromMeal;
	}

	public void setFromMeal(Double fromMeal) {
		this.fromMeal = fromMeal;
	}

	public Double getToMeal() {
		return toMeal;
	}

	public void setToMeal(Double toMeal) {
		this.toMeal = toMeal;
	}

	public Allowance(Integer id, Integer staffId, Double travelAllowance, Double deviceAllowance, Double mealAllowance,
			String staffName, Double fromTravel, Double toTravel, Double fromDevice, Double toDevice, Double fromMeal,
			Double toMeal) {
		super();
		this.id = id;
		this.staffId = staffId;
		this.travelAllowance = travelAllowance;
		this.deviceAllowance = deviceAllowance;
		this.mealAllowance = mealAllowance;
		this.staffName = staffName;
		this.fromTravel = fromTravel;
		this.toTravel = toTravel;
		this.fromDevice = fromDevice;
		this.toDevice = toDevice;
		this.fromMeal = fromMeal;
		this.toMeal = toMeal;
	}

}
