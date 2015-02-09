package com.grupo5.cebancburger.ddbbrepo.tables;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.grupo5.cebancburger.config.Options;
import com.grupo5.cebancburger.ddbbrepo.DDBBSQLite;
import com.grupo5.cebancburger.interfaces.DDBBObjectTable;
import com.grupo5.cebancburger.model.BurgerSize;

public class BurgerSizeTable implements DDBBObjectTable {
	String sqlCreate = "CREATE TABLE BurgerSize (BurgerSizeID INTEGER NOT NULL, "
			+ "Description VARCHAR(20), " + "Price DECIMAL(4,2))";
	String sqlUpdate = "DROP TABLE IF EXISTS BurgerSize";

	public BurgerSizeTable() {
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
		this.insert(new BurgerSize("Whooper", 5.00), activity);
		this.insert(new BurgerSize("Normal", 4.50), activity);
	}
	
	public void initData(Activity activity, SQLiteDatabase db) {
		this.insert(new BurgerSize("Whooper", 5.00), activity, db);
		this.insert(new BurgerSize("Normal", 4.50), activity, db);
	}
	
	public static ArrayList<ArrayList<String>> getBurgerSizes(Activity activity) {
		ArrayList<ArrayList<String>> arrBurgerSizes = new ArrayList<ArrayList<String>>();
		// id
		arrBurgerSizes.add(new ArrayList<String>());
		// description
		arrBurgerSizes.add(new ArrayList<String>());
		// price
		arrBurgerSizes.add(new ArrayList<String>());
		String query = "SELECT * FROM BurgerSize";
		SQLiteDatabase db = DDBBSQLite.getDDBB(Options.getDDBBName(), activity);
		Cursor c = db.rawQuery(query, null);
		if (c.moveToFirst()) {
			do {
				int burgerMeatID = c.getInt(0);
				arrBurgerSizes.get(0).add(Integer.toString(burgerMeatID));

				String description = c.getString(1);
				arrBurgerSizes.get(1).add(description);

				double price = c.getInt(2);
				arrBurgerSizes.get(2).add(Double.toString(price));

			} while (c.moveToNext());
		}
		return arrBurgerSizes;
	}
	
	public static BurgerSize getBurgerSize(Activity activity, int id) {
		BurgerSize size = null;
		String query = "SELECT * FROM BurgerSize WHERE BurgerSizeID=" + id;
		SQLiteDatabase db = DDBBSQLite.getDDBB(Options.getDDBBName(), activity);
		Cursor c = db.rawQuery(query, null);
		if (c.moveToFirst()) {
			int sizeID = c.getInt(0);
			String description = c.getString(1);
			double price = c.getDouble(2);
			size = new BurgerSize(sizeID, description, price);
		}
		return size;
	}

	@Override
	public void delete(Activity activity, int id) {
		SQLiteDatabase db = DDBBSQLite.getDDBB(Options.getDDBBName(), activity);
		db.delete("BurgerSize", "BurgerSizeID=" + id, null);
	}

	public void insert(BurgerSize burgerSize, Activity activity) {
		ContentValues nuevoRegistro = burgerSize.getContentValue(activity);
		SQLiteDatabase db = DDBBSQLite.getDDBB(Options.getDDBBName(), activity);
		db.insert("BurgerSize", null, nuevoRegistro);
	}
	
	public void insert(BurgerSize burgerSize, Activity activity, SQLiteDatabase db) {
		ContentValues nuevoRegistro = burgerSize.getContentValue(activity, db);
//		SQLiteDatabase db = DDBBSQLite.getDDBB(Options.getDDBBName(), activity);
		db.insert("BurgerSize", null, nuevoRegistro);
	}

	public void edit(BurgerSize burgerSize, Activity activity) {
		ContentValues reg = burgerSize.getContentValueForEdit(activity,burgerSize.getId());
		SQLiteDatabase db = DDBBSQLite.getDDBB(Options.getDDBBName(), activity);
		db.update("BurgerSize", reg, "BurgerSizeID=" + burgerSize.getId(), null);
	}

}
