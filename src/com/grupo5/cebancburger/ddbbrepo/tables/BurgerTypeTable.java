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
		// TODO Auto-generated method stub
		
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
		ContentValues reg = burgerType.getContentValue(activity);
		SQLiteDatabase db = DDBBSQLite.getDDBB(Options.getDDBBName(), activity);
		db.update("BurgerType", reg, "BurgerTypeID=" + burgerType.getId(), null);
		
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

}
