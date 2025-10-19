package com.rizwan.entities;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "User_Master")
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	private String uName;
	private String email;
	private String pwd;
	private String updatePwd;
	private Long phoneNo;
	@ManyToOne
	@JoinColumn(name="country_id")
	private CountryEntity country;
	@ManyToOne
	@JoinColumn(name="state_id")
	private StateEntity state;
	@ManyToOne
	@JoinColumn(name="city_id")
	private CitiesEntity city;
	
	@CreationTimestamp
	@Column(name="creted_Date", updatable = false)
	private LocalDate cratedDate;
	@UpdateTimestamp
	@Column(name="uptated_Date", insertable  = false)
	private LocalDate updateDate;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getUpdatePwd() {
		return updatePwd;
	}
	public void setUpdatePwd(String updatePwd) {
		this.updatePwd = updatePwd;
	}
	public Long getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(Long phoneNo) {
		this.phoneNo = phoneNo;
	}
	public CountryEntity getCountry() {
		return country;
	}
	public void setCountry(CountryEntity country) {
		this.country = country;
	}
	public StateEntity getState() {
		return state;
	}
	public void setState(StateEntity state) {
		this.state = state;
	}
	public CitiesEntity getCity() {
		return city;
	}
	public void setCity(CitiesEntity city) {
		this.city = city;
	}
	public LocalDate getCratedDate() {
		return cratedDate;
	}
	public void setCratedDate(LocalDate cratedDate) {
		this.cratedDate = cratedDate;
	}
	public LocalDate getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(LocalDate updateDate) {
		this.updateDate = updateDate;
	}
	

}
