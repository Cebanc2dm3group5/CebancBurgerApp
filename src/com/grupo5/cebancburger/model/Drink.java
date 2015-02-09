package com.grupo5.cebancburger.model;

import java.io.Serializable;
import java.math.BigDecimal;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.grupo5.cebancburger.config.Options;
import com.grupo5.cebancburger.ddbbrepo.DDBBSQLite;
import com.grupo5.cebancburger.ddbbrepo.tables.OrderLineDrinkTable;
import com.grupo5.cebancburger.interfaces.DDBBObject;

@SuppressWarnings("serial")
public class Drink implements Serializable, DDBBObject {

	private DrinkType type;
	private int orderID;
	private int tipo;
	private int cantidad;
	private double precio;
	private int drinkLineID;

	public Drink(int tipo, int cantidad, double precio) {
		setTipo(tipo);
		setCantidad(cantidad);
	}

	public Drink(DrinkType type, int cantidad) {
		setType(type);
		setCantidad(cantidad);
		setPrecio();
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public DrinkType getType() {
		return type;
	}

	public void setType(DrinkType type) {
		this.type = type;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return type.getDescription();
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setPrecio() {
		this.precio = this.type.getPrice() * this.cantidad;
	}

	public double getPrecio() {
		return Double.valueOf(truncateDecimal(this.precio, 2).toString());
	}

	public int getDrinkLineID() {
		return drinkLineID;
	}

	public void setDrinkLineID(int drinkLineID) {
		this.drinkLineID = drinkLineID;
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

	public void save(Activity activity) {
		OrderLineDrinkTable oldt = new OrderLineDrinkTable();
		if (this.drinkLineID == -1) {
			oldt.insert(this, activity);
		} else {
			oldt.edit(this, activity);
		}
	}

	@Override
	public void delete(Activity activity) {
		// TODO Auto-generated method stub

	}

	public ContentValues getContentValue(Activity activity) {
		ContentValues nr = new ContentValues();
		nr.put("OrderLineDrinkID", getNextID(activity));
		nr.put("OrderID", this.getOrderID());
		nr.put("Amount", this.getCantidad());
		return nr;
	}
	
	public ContentValues getContentValueForEdit(Activity activity, int id){
		ContentValues nr = new ContentValues();
		nr.put("OrderLineDrinkID", id);
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

}
