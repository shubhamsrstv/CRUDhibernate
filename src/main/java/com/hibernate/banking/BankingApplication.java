package com.hibernate.banking;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hibernate.banking.beans.Beans;
import com.hibernate.banking.dao.Dao;

@SpringBootApplication
public class BankingApplication {
	
	static Beans beans = new Beans();
	static Dao dao = new Dao();
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		SpringApplication.run(BankingApplication.class, args);
		System.out.println("Enter the choice : ");
		System.out.println("1. To create account.");
		System.out.println("2. To deposit amount.");
		System.out.println("3. To withdraw amount.");
		System.out.println("4. To transfer amount between accounts.");
		System.out.println("5. To delete account.");
		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();
		switch(choice)
		{
		case 1:{
			createAccount();
			break;
		}
		case 2:{
			depositAmount();
			break;
		}
		case 3:{
			withdrawAmount();
			break;
		}
		case 4:{
			transferAmount();
			break;
		}
		case 5:{
			deleteAccount();
			break;
		}
		default:{
			System.out.println("Entered wrong number.");
		}
		}
	}
	
	@SuppressWarnings("resource")
	private static void createAccount() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter customer details : ");
		System.out.println("Name : ");
		beans.setName(sc.nextLine());
		System.out.println("Aadhar Card : ");
		beans.setAadharCard(sc.nextLong());
		System.out.println("Mobile number : ");
		beans.setMobile(sc.nextLong());
		Beans result = dao.findAadhar(beans.getAadharCard());
		if(result==null) {
		dao.createCustomer(beans);
		}
		else {
			System.out.println("Aadhar card is not unique");
		}
	}
	
	@SuppressWarnings("resource")
	private static void depositAmount() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter deposit details : ");
		System.out.println("Account Number : ");
		beans.setAccountNumber(sc.nextLong());
		System.out.println("Amount : ");
		float amount = sc.nextFloat();
		
		Beans result = dao.find(beans.getAccountNumber());
		if(result!=null)
		{
		if(amount<=0)
		{
			System.out.println("Wrong amount");
		}
		else {
			beans.setAmount(amount);
			
			dao.depositAmount(beans.getAccountNumber(), beans.getAmount());
		}
		}
		else
		{
			System.out.println("Account number not valid!");
		}
	}
	
	@SuppressWarnings("resource")
	private static void withdrawAmount() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter withdraw details : ");
		System.out.println("Account Number : ");
		beans.setAccountNumber(sc.nextLong());
		System.out.println("Amount : ");
		float amount = sc.nextFloat();
		
		Beans result = dao.find(beans.getAccountNumber());
		if(result!=null)
		{
		if(amount<=0)
		{
			System.out.println("Wrong amount");
		}
		else {
			if(amount>result.getAmount())
			{
				System.out.println("Transaction not possible");
			}
			else {
				beans.setAmount(amount);
				
				dao.withdrawAmount(beans.getAccountNumber(), beans.getAmount());
			}
			
		}
		}
		else
		{
			System.out.println("Account number not valid!");
		}
	}
	
	@SuppressWarnings("resource")
	private static void deleteAccount() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter deleting details : ");
		System.out.println("Account Number : ");
		beans.setAccountNumber(sc.nextLong());
		
		Beans result = dao.find(beans.getAccountNumber());
		if(result!=null)
		{
		dao.deleteAccount(beans.getAccountNumber());
		}
		else
		{
			System.out.println("Account number not valid!");
		}
	}
	
	@SuppressWarnings("resource")
	private static void transferAmount() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter transfer details : ");
		System.out.println("First Account Number : ");
		Long account1 = sc.nextLong();
		System.out.println("Second Account Number : ");
		Long account2 = sc.nextLong();
		System.out.println("Amount : ");
		float amount = sc.nextFloat();
		
		Beans result = dao.find(account1);
		if(result!=null)
		{
			Beans result2 = dao.find(account2);
			if(result2!=null) {
		if(amount<=0)
		{
			System.out.println("Wrong amount");
		}
		else {
			if(amount>result.getAmount())
			{
				System.out.println("Transaction not possible");
			}
			else {
				beans.setAmount(amount);
				
				dao.transferAmount(account1, account2, beans.getAmount());
			}
			
		}}
			else
			{
				System.out.println("Second Account number not valid!");
			}
		}
		else
		{
			System.out.println("First Account number not valid!");
		}
	}
}
