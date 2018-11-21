package com.hibernate.banking.beans;

import javax.persistence.*;

import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name="banking")
public class Beans 
{
	private long accountNumber;
	private String name;
	private long aadharCard;
	private long mobile;
	private float amount;
	
	@Column(name="accountNumber")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getAadharCard() {
		return aadharCard;
	}
	public void setAadharCard(long aadharCard) {
		this.aadharCard = aadharCard;
	}
	public long getMobile() {
		return mobile;
	}
	public void setMobile(long mobile) {
		this.mobile = mobile;
	}
	@ColumnDefault("0")
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}	
	public Beans(long aadharCard,float amount,long mobile,String name) {
		this.aadharCard=aadharCard;
		this.amount=amount;
		this.mobile=mobile;
		this.name=name;
	}
	public Beans() {
		
	}
}
