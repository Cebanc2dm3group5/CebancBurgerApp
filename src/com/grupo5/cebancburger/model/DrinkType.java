package com.grupo5.cebancburger.model;

import java.util.Date;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.grupo5.cebancburger.config.Options;
import com.grupo5.cebancburger.ddbbrepo.DDBBSQLite;
import com.grupo5.cebancburger.ddbbrepo.tables.DrinkTypeTable;
import com.grupo5.cebancburger.interfaces.DDBBObject;

public class DrinkType implements DDBBObject {
	String description;
	double price;
	int id;

	public DrinkType(int id, String description, double price) {
		setId(id);
		setDescription(description);
		setPrice(price);
	}
	
	public DrinkType(String description, double price) {
		setDescription(description);
		setPrice(price);
	}
	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public void save(Activity activity) {
		DrinkTypeTable dtt = new DrinkTypeTable();
		if (this.id == -1) {
			dtt.insert(this, activity);
		} else {
			dtt.edit(this, activity);
		}
	}

	@Override
	public void delete(Activity activity) {
		DrinkTypeTable dtt = new DrinkTypeTable();
		dtt.delete(activity, this.getId());
	}

	@Override
	public ContentValues getContentValue(Activity activity) {
		ContentValues nr = new ContentValues();
		nr.put("DrinkTypeID", getNextID(activity));
		nr.put("Description", this.getDescription());
		nr.put("Price", this.getPrice());
		return nr;
	}
	
	public ContentValues getContentValueForEdit(Activity activity, int id){
		ContentValues nr = new ContentValues();
		nr.put("DrinkTypeID", id);
		nr.put("Description", this.getDescription());
		nr.put("Price", this.getPrice());
		return nr;
	}

	private static int getNextID(Activity activity) {
		String query = "SELECT DrinkTypeID FROM DrinkType ORDER BY DrinkTypeID DESC LIMIT 1";
		int lastID = 0;
		SQLiteDatabase db = DDBBSQLite.getDDBB(Options.getDDBBName(), activity);
		Cursor c = db.rawQuery(query, null);
		if (c.moveToFirst()) {
			lastID = c.getInt(0);
		}
		return lastID + 1;
	}
}
