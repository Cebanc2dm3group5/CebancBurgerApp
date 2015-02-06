package com.grupo5.cebancburger.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.grupo5.cebancburger.config.Options;
import com.grupo5.cebancburger.ddbbrepo.DDBBSQLite;
import com.grupo5.cebancburger.ddbbrepo.tables.OrderTable;
import com.grupo5.cebancburger.interfaces.DDBBObject;

public class Order implements Serializable, DDBBObject {

	private static final long serialVersionUID = 1L;
	private Customer cliente = null;
	private ArrayList<Burger> alBurger = null;
	private ArrayList<Drink> alBebida = null;
	private String regalo = "";
	private double precio;
	private int orderID, userID, customerID;

	public Order() {
		alBurger = new ArrayList<Burger>();
		alBebida = new ArrayList<Drink>();
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public int getOrderID() {
		return this.orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public void setCliente(Customer cliente) {

		this.cliente = cliente;

	}

	public Customer getCliente() {

		return cliente;

	}

	public void setBurger(Burger burger) {

		alBurger.add(burger);

	}

	public ArrayList<Burger> getBurger() {

		return alBurger;

	}

	public void setBebida(Drink bebida) {

		alBebida.add(bebida);

	}

	public ArrayList<Drink> getBebida() {

		return alBebida;

	}

	public String getRegalo() {

		if (getPrecio() > 15 && getPrecio() <= 25)
			regalo = "Peluche de android";
		else if (getPrecio() > 25)
			regalo = "Peluche de android y vale para comer en Cebanc";
		else
			regalo = "Ninguno";
		return regalo;

	}

	public double getPrecio() {
		double price = 0;
		for (int i = 0; i < alBurger.size(); i++) {
			price += alBurger.get(i).getPrecio();
		}

		for (int i = 0; i < alBebida.size(); i++) {
			price += alBebida.get(i).getPrecio();
		}

		return Double.valueOf(truncateDecimal(price, 2).toString());
	}

	public void setFinalPrice() {
		this.precio = getPrecio();
	}

	public double getFinalPrecio() {
		return this.precio;
	}

	private static BigDecimal truncateDecimal(double x, int numberofDecimals) {
		if (x > 0) {
			return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals,
					BigDecimal.ROUND_FLOOR);
		} else {
			return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals,
					BigDecimal.ROUND_CEILING);
		}
	}

	@Override
	public void save(Activity activity) {
		OrderTable ot = new OrderTable();
		if (this.orderID != -1) {
			ot.insert(this, activity);
		} else {
			ot.edit(this, activity);
		}
	}

	@Override
	public void delete(Activity activity) {
		OrderTable ot = new OrderTable();
		ot.delete(activity, this.getOrderID());
	}

	@SuppressWarnings("deprecation")
	@Override
	public ContentValues getContentValue(Activity activity) {
		ContentValues nr = new ContentValues();
		nr.put("OrderID", getNextID(activity));
		nr.put("UserID", this.getUserID());
		nr.put("CustomerID", this.getCustomerID());
		Date date = new Date();
		nr.put("Date", date.getDate());
		nr.put("Time", date.getTime());
		nr.put("Price", this.getFinalPrecio());
		return nr;
	}
	
	
	private static int getNextID(Activity activity) {
		String query = "SELECT OrderID FROM Orders ORDER BY OrderID DESC LIMIT 1";
		int lastID = 0;
		SQLiteDatabase db = DDBBSQLite.getDDBB(Options.getDDBBName(), activity);
		Cursor c = db.rawQuery(query, null);
		if (c.moveToFirst()) {
			lastID = c.getInt(0);
		}
		return lastID + 1;
	}

}
