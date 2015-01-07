package com.grupo5.cebancburger.viewmodels;

public class Card {
	private String line1;
	private String line2;
	private String price;

	public Card(String line1, String line2, double price) {
		this.line1 = line1;
		this.line2 = line2;
		this.price = Double.toString(price);
	}

	public String getLine1() {
		return line1;
	}

	public String getLine2() {
		return line2;
	}

	public String getPrice() {
		return price + " €";
	}

}
