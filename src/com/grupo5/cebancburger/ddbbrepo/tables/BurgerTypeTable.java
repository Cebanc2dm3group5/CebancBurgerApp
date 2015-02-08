package com.grupo5.cebancburger.ddbbrepo.tables;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.grupo5.cebancburger.config.Options;
import com.grupo5.cebancburger.ddbbrepo.DDBBSQLite;
import com.grupo5.cebancburger.interfaces.DDBBObjectTable;
import com.grupo5.cebancburger.model.BurgerType;

public class BurgerTypeTable implements DDBBObjectTable {
	String sqlCreate = "CREATE TABLE BurgerType (BurgerTypeID INTEGER NOT NULL, "
			+ "Description VARCHAR(20), " + "Price DECIMAL(4,2))";
	String sqlUpdate = "DROP TABLE IF EXISTS BurgerType";

	public BurgerTypeTable() {}

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
		this.insert(new BurgerType("Clásica", 1), activity);
		this.insert(new BurgerType("Clásica con queso", 1.2), activity);
		this.insert(new BurgerType("Doble con queso", 2.5), activity);
		this.insert(new BurgerType("Vegetal", 1), activity);
		this.insert(new BurgerType("Especial", 2), activity);
	}

	@Override
	public void delete(Activity activity, int id) {
		SQLiteDatabase db = DDBBSQLite.getDDBB(Options.getDDBBName(), activity);
		db.delete("BurgerType", "BurgerTypeID=" + id, null);		
	}

	public void insert(BurgerType burgerType, Activity activity) {
		ContentValues nuevoRegistro = burgerType.getContentValue(activity);
		SQLiteDatabase db = DDBBSQLite.getDDBB(Options.getDDBBName(), activity);
		db.insert("BurgerType", null, nuevoRegistro);		
	}

	public void edit(BurgerType burgerType, Activity activity) {
		ContentValues reg = burgerType.getContentValueForEdit(activity,burgerType.getId());
		SQLiteDatabase db = DDBBSQLite.getDDBB(Options.getDDBBName(), activity);
		db.update("BurgerType", reg, "BurgerTypeID=" + burgerType.getId(), null);
		
	}
	
	
	public static ArrayList<ArrayList<String>> getBurgerTypes(Activity activity) {
		ArrayList<ArrayList<String>> arrBurgerTypes = new ArrayList<ArrayList<String>>();
		// id
		arrBurgerTypes.add(new ArrayList<String>());
		// description
		arrBurgerTypes.add(new ArrayList<String>());
		// price
		arrBurgerTypes.add(new ArrayList<String>());
		String query = "SELECT * FROM BurgerType";
		SQLiteDatabase db = DDBBSQLite.getDDBB(Options.getDDBBName(), activity);
		Cursor c = db.rawQuery(query, null);
		if (c.moveToFirst()) {
			do {
				int drinkTypeID = c.getInt(0);
				arrBurgerTypes.get(0).add(Integer.toString(drinkTypeID));

				String description = c.getString(1);
				arrBurgerTypes.get(1).add(description);

				double price = c.getInt(2);
				arrBurgerTypes.get(2).add(Double.toString(price));

			} while (c.moveToNext());
		}
		return arrBurgerTypes;
	}
	
	public static BurgerType getBurgerType(Activity activity, int id) {
		BurgerType type = null;
		String query = "SELECT * FROM BurgerType WHERE BurgerTypeID=" + id;
		SQLiteDatabase db = DDBBSQLite.getDDBB(Options.getDDBBName(), activity);
		Cursor c = db.rawQuery(query, null);
		if (c.moveToFirst()) {
			int typeID = c.getInt(0);
			String description = c.getString(1);
			double price = c.getDouble(2);
			type = new BurgerType(typeID, description, price);
		}
		return type;
	}

}
