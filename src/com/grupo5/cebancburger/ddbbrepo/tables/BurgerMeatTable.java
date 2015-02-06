package com.grupo5.cebancburger.ddbbrepo.tables;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.grupo5.cebancburger.config.Options;
import com.grupo5.cebancburger.ddbbrepo.DDBBSQLite;
import com.grupo5.cebancburger.interfaces.DDBBObjectTable;
import com.grupo5.cebancburger.model.BurgerMeat;

public class BurgerMeatTable implements DDBBObjectTable {
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
		// TODO Auto-generated method stub

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
				arrBurgerMeats.get(0).add(description);

				double price = c.getInt(2);
				arrBurgerMeats.get(0).add(Double.toString(price));

			} while (c.moveToNext());
		}
		return arrBurgerMeats;
	}

	public void insert(BurgerMeat burgerMeat, Activity activity) {
		ContentValues nuevoRegistro = burgerMeat.getContentValue(activity);
		SQLiteDatabase db = DDBBSQLite.getDDBB(Options.getDDBBName(), activity);
		db.insert("DrinkType", null, nuevoRegistro);
	}

	public void edit(BurgerMeat burgerMeat, Activity activity) {
		ContentValues reg = burgerMeat.getContentValue(activity);
		SQLiteDatabase db = DDBBSQLite.getDDBB(Options.getDDBBName(), activity);
		db.update("DrinkType", reg, "DrinkTypeID=" + burgerMeat.getId(), null);
	}

}
