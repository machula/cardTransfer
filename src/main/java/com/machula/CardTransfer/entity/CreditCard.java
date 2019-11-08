package com.machula.CardTransfer.entity;

public class CreditCard {
	private int id;
	private String cardNumber;
	private String expDate;
	private int cvc2;

	public CreditCard() {
	}

	public CreditCard(int id, String cardNumber, String expDate, int cvc2) {
		this.id = id;
		this.cardNumber = cardNumber;
		this.expDate = expDate;
		this.cvc2 = cvc2;
	}

	public CreditCard(String cardNumber, String expDate, int cvc2) {
		this.cardNumber = cardNumber;
		this.expDate = expDate;
		this.cvc2 = cvc2;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	public int getCvc2() {
		return cvc2;
	}

	public void setCvc2(int cvc2) {
		this.cvc2 = cvc2;
	}

}
