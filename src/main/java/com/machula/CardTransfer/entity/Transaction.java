package com.machula.CardTransfer.entity;

import java.sql.Date;

public class Transaction {
	private CreditCard creditCard;
	private int amount;
	private int fee;
	private String resipientCard;
	private Date date;
	
	public Transaction(CreditCard creditCard, int amount, String resipientCard) {
		this.creditCard = creditCard;
		this.fee = (int) (amount*0.1 + 10);
		this.amount = amount;
		this.resipientCard = resipientCard;
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getFee() {
		return fee;
	}

	public String getResipientCard() {
		return resipientCard;
	}

	public void setResipientCard(String resipientCard) {
		this.resipientCard = resipientCard;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}
