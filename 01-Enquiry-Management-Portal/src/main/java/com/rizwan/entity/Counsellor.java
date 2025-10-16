package com.rizwan.entity;

import java.time.LocalDate;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Counsellor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer councilerId;
	String counciler_name;
	@Column(unique = true)
	String emailId;
	String passwd;
	String phoneNo;
	@CreationTimestamp
	LocalDate createdDtae;
	@UpdateTimestamp
	LocalDate uptaedDtae;
	public Integer getCouncilerId() {
		return councilerId;
	}
	public void setCouncilerId(Integer councilerId) {
		this.councilerId = councilerId;
	}
	public String getCounciler_name() {
		return counciler_name;
	}
	public void setCounciler_name(String counciler_name) {
		this.counciler_name = counciler_name;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public LocalDate getCreatedDtae() {
		return createdDtae;
	}
	public void setCreatedDtae(LocalDate createdDtae) {
		this.createdDtae = createdDtae;
	}
	public LocalDate getUptaedDtae() {
		return uptaedDtae;
	}
	public void setUptaedDtae(LocalDate uptaedDtae) {
		this.uptaedDtae = uptaedDtae;
	}
	
}
