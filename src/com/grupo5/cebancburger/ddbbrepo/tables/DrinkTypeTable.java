package com.grupo5.cebancburger.ddbbrepo.tables;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.grupo5.cebancburger.config.Options;
import com.grupo5.cebancburger.ddbbrepo.DDBBSQLite;
import com.grupo5.cebancburger.interfaces.DDBBObjectTable;
import com.grupo5.cebancburger.model.DrinkType;

public class DrinkTypeTable implements DDBBObjectTable {
	String sqlCreate = "CREATE TABLE DrinkType (DrinkTypeID INTEGER NOT NULL, "
			+ "Description VARCHAR(20), " + "Price DECIMAL(4,2))";
	String sqlUpdate = "DROP TABLE IF EXISTS DrinkType";

	public DrinkTypeTable() {
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

	public void insert(DrinkType drinkType, Activity activity) {
		ContentValues nuevoRegistro = drinkType.getContentValue(activity);
		SQLiteDatabase db = DDBBSQLite.getDDBB(Options.getDDBBName(), activity);
		db.insert("DrinkType", null, nuevoRegistro);
	}

	public void edit(DrinkType drinkType, Activity activity) {
		ContentValues reg = drinkType.getContentValue(activity);
		SQLiteDatabase db = DDBBSQLite.getDDBB(Options.getDDBBName(), activity);
		db.update("DrinkType", reg, "DrinkTypeID=" + drinkType.getId(), null);

	}

	public static ArrayList<ArrayList<String>> getDrinkTypes(Activity activity) {
		ArrayList<ArrayList<String>> arrDrinkTypes = new ArrayList<ArrayList<String>>();
		// id
		arrDrinkTypes.add(new ArrayList<String>());
		// description
		arrDrinkTypes.add(new ArrayList<String>());
		// price
		arrDrinkTypes.add(new ArrayList<String>());
		String query = "SELECT * FROM DrinkType";
		SQLiteDatabase db = DDBBSQLite.getDDBB(Options.getDDBBName(), activity);
		Cursor c = db.rawQuery(query, null);
		if (c.moveToFirst()) {
			do {
				int drinkTypeID = c.getInt(0);
				arrDrinkTypes.get(0).add(Integer.toString(drinkTypeID));

				String description = c.getString(1);
				arrDrinkTypes.get(0).add(description);

				double price = c.getInt(2);
				arrDrinkTypes.get(0).add(Double.toString(price));

			} while (c.moveToNext());
		}
		return arrDrinkTypes;
	}
	
	public static DrinkType getDrinkType(Activity activity, int id) {
		DrinkType type = null;
		String query = "SELECT * FROM DrinkType WHERE DrinkTypeID=" + id;
		SQLiteDatabase db = DDBBSQLite.getDDBB(Options.getDDBBName(), activity);
		Cursor c = db.rawQuery(query, null);
		if (c.moveToFirst()) {
			int typeID = c.getInt(0);
			String description = c.getString(1);
			double price = c.getDouble(2);
			type = new DrinkType(typeID, description, price);
		}
		return type;
	}

	@Override
	public void initData(Activity activity) {
		this.insert(new DrinkType("Cola", 1.5), activity);
		this.insert(new DrinkType("Limón", 1.5), activity);
		this.insert(new DrinkType("Naranja", 1.5), activity);
		this.insert(new DrinkType("Nestea", 1.5), activity);
		this.insert(new DrinkType("Cerveza", 1.5), activity);
		this.insert(new DrinkType("Agua", 1), activity);
	}

	@Override
	public void delete(Activity activity, int id) {
		SQLiteDatabase db = DDBBSQLite.getDDBB(Options.getDDBBName(), activity);
		db.delete("DrinkType", "DrinkTypeID=" + id, null);

	}

}
