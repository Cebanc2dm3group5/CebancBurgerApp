package com.grupo5.cebancburger.ddbbrepo.tables;

import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.grupo5.cebancburger.config.Options;
import com.grupo5.cebancburger.ddbbrepo.DDBBSQLite;
import com.grupo5.cebancburger.interfaces.DDBBObjectTable;
import com.grupo5.cebancburger.model.Drink;

public class OrderLineDrinkTable implements DDBBObjectTable {

	String sqlCreate = "CREATE TABLE OrderLineDrink (OrderLineDrinkID INTEGER NOT NULL, "
			+ "DrinkTypeID INTEGER NOT NULL, "
			+ "OrderID INTEGER NOT NULL, "
			+ "Amount INTEGER, "
			+ "PRIMARY KEY (OrderLineDrinkID), "
			+ "FOREIGN KEY(OrderID) REFERENCES Orders(OrderID), "
			+ "FOREIGN KEY(DrinkTypeID) REFERENCES DrinkType(DrinkTypeID)) ";
	String sqlUpdate = "DROP TABLE IF EXISTS OrderLineDrink";

	public OrderLineDrinkTable() {
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

	public void insert(Drink drink, Activity activity) {
		// TODO Auto-generated method stub
		
	}

	public void edit(Drink drink, Activity activity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initData(Activity activity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Activity activity, int id) {
		
	}
	
	public static ArrayList<Drink> getOrderDrinks(Activity activity, int orderID) {
		ArrayList<Drink> arrDrinks = new ArrayList<Drink>();
		String query = "SELECT * FROM OrderLineDrink WHERE OrderID="+orderID;
		SQLiteDatabase db = DDBBSQLite.getDDBB(Options.getDDBBName(), activity);
		Cursor c = db.rawQuery(query, null);
		if (c.moveToFirst()) {
			do {
				int id = c.getInt(0);
				int typeID = c.getInt(1);
				int amount = c.getInt(5);
				Drink d = new Drink(DrinkTypeTable.getDrinkType(activity, typeID), amount);
				d.setDrinkLineID(id);
				d.setOrderID(orderID);
				arrDrinks.add(d);
			} while (c.moveToNext());
		}
		return arrDrinks;
	}

}
