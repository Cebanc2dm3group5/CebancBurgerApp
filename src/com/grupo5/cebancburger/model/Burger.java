package com.grupo5.cebancburger.model;

import java.io.Serializable;
import java.math.BigDecimal;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.grupo5.cebancburger.config.Options;
import com.grupo5.cebancburger.ddbbrepo.DDBBSQLite;
import com.grupo5.cebancburger.ddbbrepo.tables.OrderLineBurgerTable;
import com.grupo5.cebancburger.interfaces.DDBBObject;

@SuppressWarnings("serial")
public class Burger implements Serializable, DDBBObject {

//	private String tamano, tipo_carne, tipo_burger;
	private int orderID;
	private BurgerSize size;
	private BurgerMeat meat;
	private BurgerType type;
	private int cantidad;
	private double precio;
	private int burgerLineID = -1;

//	public Burger(String tamano, String tipo_carne, String tipo_burger,
//			int cantidad) {
//		this.tamano = tamano;
//		this.tipo_carne = tipo_carne;
//		this.tipo_burger = tipo_burger;
//		this.cantidad = cantidad;
//		setPrecio();
//	}

	public Burger(BurgerSize size, BurgerMeat meat, BurgerType type, int cantidad) {
		setSize(size);
		setMeat(meat);
		setType(type);
		setCantidad(cantidad);
		setPrecio();
	}

	public int getBurgerLineID() {
		return burgerLineID;
	}

	public void setBurgerLineID(int burgerLineID) {
		this.burgerLineID = burgerLineID;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public BurgerSize getSize() {
		return size;
	}

	public void setSize(BurgerSize size) {
		this.size = size;
	}

	public BurgerMeat getMeat() {
		return meat;
	}

	public void setMeat(BurgerMeat meat) {
		this.meat = meat;
	}

	public BurgerType getType() {
		return type;
	}

	public void setType(BurgerType type) {
		this.type = type;
	}

//	public void setTamano(String tamano) {
//		this.tamano = tamano;
//	}

	public String getTamano() {
		return size.getDescription();
	}

//	public void setTipoCarne(String tipo_carne) {
//		this.tipo_carne = tipo_carne;
//	}

	public String getTipoCarne() {
		return meat.getDescription();
	}

//	public void setTipoBurger(String tipo_burger) {
//		this.tipo_burger = tipo_burger;
//	}

	public String getTipoBurger() {
		return type.getDescription();
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setPrecio() {
		this.precio = (this.size.getPrice() + this.meat.getPrice() + this.type
				.getPrice()) * this.cantidad;
	}

	public double getPrecio() {
		return Double.valueOf(truncateDecimal(this.precio, 2).toString());
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

	public ContentValues getContentValue(Activity activity) {
		ContentValues nr = new ContentValues();
		nr.put("OrderLineBurgerID", getNextID(activity));
		nr.put("BurgerTypeID", this.getType().getId());
		nr.put("BurgerMeatID", this.getMeat().getId());
		nr.put("BurgerSizeID", this.getSize().getId());
		nr.put("OrderID", this.getOrderID());
		nr.put("Amount", this.getCantidad());
		return nr;
	}
	public ContentValues getContentValueForEdit(Activity activity, int id){
		ContentValues nr = new ContentValues();
		nr.put("OrderLineBurgerID", id);
		nr.put("BurgerTypeID", this.getType().getId());
		nr.put("BurgerMeatID", this.getMeat().getId());
		nr.put("BurgerSize", this.getSize().getId());
		nr.put("OrderID", this.getOrderID());
		nr.put("Amount", this.getCantidad());
		return nr;
	}

	private static int getNextID(Activity activity) {
		String query = "SELECT OrderLineBurgerID FROM OrderLineBurger ORDER BY OrderLineBurgerID DESC LIMIT 1";
		int lastID = 0;
		SQLiteDatabase db = DDBBSQLite.getDDBB(Options.getDDBBName(), activity);
		Cursor c = db.rawQuery(query, null);
		if (c.moveToFirst()) {
			lastID = c.getInt(0);
		}
		return lastID + 1;
	}

	@Override
	public void save(Activity activity) {
		OrderLineBurgerTable olbt = new OrderLineBurgerTable();
		if (this.burgerLineID == -1) {
			olbt.insert(this, activity);
		} else {
			olbt.edit(this, activity);
		}
	}

	@Override
	public void delete(Activity activity) {
		OrderLineBurgerTable t = new OrderLineBurgerTable();
		t.delete(activity, this.getOrderID());
	}

}
