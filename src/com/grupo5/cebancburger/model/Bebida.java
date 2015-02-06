package com.grupo5.cebancburger.model;

import java.io.Serializable;
import java.math.BigDecimal;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.grupo5.cebancburger.ddbbrepo.DDBBSQLite;
import com.grupo5.cebancburger.interfaces.DDBBObject;

@SuppressWarnings("serial")
public class Bebida implements Serializable, DDBBObject {

	private int tipo;
	private int cantidad;
	private double precio;

	public Bebida(int tipo, int cantidad, double precio) {
		setTipo(tipo);
		setCantidad(cantidad);
		setPrecio(precio);
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public int getTipo() {
		return tipo;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setPrecio(double precio) {
		this.precio = precio * this.cantidad;
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

	@Override
	public void save(Activity activity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Activity activity) {
		// TODO Auto-generated method stub

	}

	@Override
	public ContentValues getContentValue(Activity activity) {
		ContentValues nr = new ContentValues();
		nr.put("OrderLineDrinkID", getNextID(activity));
		nr.put("DrinkTypeID", getNextID(activity));
		return nr;
	}
	
	private static int getNextID(Activity activity) {
		String query = "SELECT OrderLineDrinkID FROM OrderLineDrink ORDER BY OrderLineDrinkID DESC LIMIT 1";
		int lastID = 0;
		SQLiteDatabase db = DDBBSQLite.getDDBB(Options.getDDBBName(), activity);
		Cursor c = db.rawQuery(query, null);
		if (c.moveToFirst()) {
			lastID = c.getInt(0);
		}
		return lastID + 1;
	}

}
