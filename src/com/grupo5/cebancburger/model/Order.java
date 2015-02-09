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
	private int orderID, userID;
	private Date date;
	private java.sql.Time time;

	public Order() {
		alBurger = new ArrayList<Burger>();
		alBebida = new ArrayList<Drink>();
	}

	public Order(Customer cliente, ArrayList<Burger> alBurger,
			ArrayList<Drink> alBebida, int orderID, int userID, Date date,
			java.sql.Time time2) {
		super();
		this.cliente = cliente;
		this.alBurger = alBurger;
		this.alBebida = alBebida;
		this.orderID = orderID;
		this.userID = userID;
		this.date = date;
		this.time = time2;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public java.sql.Time getTime() {
		return time;
	}

	public void setTime(java.sql.Time time) {
		this.time = time;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
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
		burger.setOrderID(this.orderID);
		alBurger.add(burger);

	}

	public ArrayList<Burger> getBurger() {

		return alBurger;

	}

	public void setBebida(Drink bebida) {
		bebida.setOrderID(this.orderID);
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
		if (this.orderID == -1) {
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
	public ContentValues getContentValue(Activity activity) {
		ContentValues nr = new ContentValues();
		nr.put("OrderID", getNextID(activity));
		nr.put("UserID", this.getUserID());
		nr.put("CustomerID", this.getCliente().getId());
		Date date = new Date();
		nr.put("Date", date.getDate());
		nr.put("Time", date.getTime());
		nr.put("Price", this.getFinalPrecio());
		return nr;
	}
	public ContentValues getContentValueForEdit(Activity activity, int id){
		ContentValues nr = new ContentValues();
		nr.put("OrderID", id);
		nr.put("UserID", this.getUserID());
		nr.put("CustomerID", this.getCliente().getId());
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
