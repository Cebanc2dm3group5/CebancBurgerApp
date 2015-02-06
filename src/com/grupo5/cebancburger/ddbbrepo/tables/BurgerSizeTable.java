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
		// TODO Auto-generated method stub

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
				arrBurgerSizes.get(0).add(description);

				double price = c.getInt(2);
				arrBurgerSizes.get(0).add(Double.toString(price));

			} while (c.moveToNext());
		}
		return arrBurgerSizes;
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

	public void edit(BurgerSize burgerSize, Activity activity) {
		ContentValues reg = burgerSize.getContentValue(activity);
		SQLiteDatabase db = DDBBSQLite.getDDBB(Options.getDDBBName(), activity);
		db.update("BurgerSize", reg, "BurgerSizeID=" + burgerSize.getId(), null);
	}

}
