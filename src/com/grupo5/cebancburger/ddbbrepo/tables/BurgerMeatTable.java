package com.grupo5.cebancburger.ddbbrepo.tables;

import java.io.Serializable;
import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.grupo5.cebancburger.config.Options;
import com.grupo5.cebancburger.ddbbrepo.DDBBSQLite;
import com.grupo5.cebancburger.interfaces.DDBBObjectTable;
import com.grupo5.cebancburger.model.BurgerMeat;

@SuppressWarnings("serial")
public class BurgerMeatTable implements Serializable, DDBBObjectTable {
	String sqlCreate = "CREATE TABLE BurgerMeat (BurgerMeatID INTEGER NOT NULL, "
			+ "Description VARCHAR(20), " + "Price DECIMAL(4,2))";
	String sqlUpdate = "DROP TABLE IF EXISTS BurgerMeat";

	public BurgerMeatTable() {
	}

	public String getSqlCreate() {
		return this.sqlCreate;
	}

	public void setSqlCreate(String sqlCreate) {
		this.sqlCreate = sqlCreate;
	}

	public String getSqlUpdate() {
		return this.sqlUpdate;
	}

	public void setSqlUpdate(String sqlUpdate) {
		this.sqlUpdate = sqlUpdate;
	}

	@Override
	public void initData(Activity activity) {
		this.insert(new BurgerMeat("Buey", 1.5), activity);
		this.insert(new BurgerMeat("Pollo", 0.8), activity);
		this.insert(new BurgerMeat("Ternera", 1), activity);
	}
	
	public void initData(Activity activity, SQLiteDatabase db) {
		this.insert(new BurgerMeat("Buey", 1.5), activity, db);
		this.insert(new BurgerMeat("Pollo", 0.8), activity, db);
		this.insert(new BurgerMeat("Ternera", 1), activity, db);
	}

	@Override
	public void delete(Activity activity, int id) {
		SQLiteDatabase db = DDBBSQLite.getDDBB(Options.getDDBBName(), activity);
		db.delete("BurgerMeat", "BurgerMeatID=" + id, null);
	}

	public static ArrayList<ArrayList<String>> getBurgerMeats(Activity activity) {
		ArrayList<ArrayList<String>> arrBurgerMeats = new ArrayList<ArrayList<String>>();
		// id
		arrBurgerMeats.add(new ArrayList<String>());
		// description
		arrBurgerMeats.add(new ArrayList<String>());
		// price
		arrBurgerMeats.add(new ArrayList<String>());
		String query = "SELECT * FROM BurgerMeat";
		SQLiteDatabase db = DDBBSQLite.getDDBB(Options.getDDBBName(), activity);
		Cursor c = db.rawQuery(query, null);
		if (c.moveToFirst()) {
			do {
				int burgerMeatID = c.getInt(0);
				arrBurgerMeats.get(0).add(Integer.toString(burgerMeatID));

				String description = c.getString(1);
				arrBurgerMeats.get(1).add(description);

				double price = c.getDouble(2);
				arrBurgerMeats.get(2).add(Double.toString(price));

			} while (c.moveToNext());
		}
		return arrBurgerMeats;
	}

	public static BurgerMeat getBurgerMeat(Activity activity, int id) {
		BurgerMeat meat = null;
		String query = "SELECT * FROM BurgerMeat WHERE BurgerMeatID=" + id;
		SQLiteDatabase db = DDBBSQLite.getDDBB(Options.getDDBBName(), activity);
		Cursor c = db.rawQuery(query, null);
		if (c.moveToFirst()) {
			int meatID = c.getInt(0);
			String description = c.getString(1);
			double price = c.getDouble(2);
			meat = new BurgerMeat(meatID, description, price);
		}
		return meat;
	}

	public void insert(BurgerMeat burgerMeat, Activity activity) {
		ContentValues nuevoRegistro = burgerMeat.getContentValue(activity);
		SQLiteDatabase db = DDBBSQLite.getDDBB(Options.getDDBBName(), activity);
		db.insert("BurgerMeat", null, nuevoRegistro);
	}
	
	public void insert(BurgerMeat burgerMeat, Activity activity, SQLiteDatabase db) {
		ContentValues nuevoRegistro = burgerMeat.getContentValue(activity, db);
//		SQLiteDatabase db = DDBBSQLite.getDDBB(Options.getDDBBName(), activity);
		db.insert("BurgerMeat", null, nuevoRegistro);
	}

	public void edit(BurgerMeat burgerMeat, Activity activity) {
		ContentValues reg = burgerMeat.getContentValueForEdit(activity,burgerMeat.getId());
		SQLiteDatabase db = DDBBSQLite.getDDBB(Options.getDDBBName(), activity);
		db.update("BurgerMeat", reg, "BurgerMeatID=" + burgerMeat.getId(), null);
	}

}
