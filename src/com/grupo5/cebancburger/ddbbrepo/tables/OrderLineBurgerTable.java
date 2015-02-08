package com.grupo5.cebancburger.ddbbrepo.tables;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.grupo5.cebancburger.config.Options;
import com.grupo5.cebancburger.ddbbrepo.DDBBSQLite;
import com.grupo5.cebancburger.interfaces.DDBBObjectTable;
import com.grupo5.cebancburger.model.Burger;

public class OrderLineBurgerTable implements DDBBObjectTable {

	String sqlCreate = "CREATE TABLE OrderLineBurger (OrderLineBurgerID INTEGER NOT NULL, "
			+ "BurgerTypeID INTEGER NOT NULL, "
			+ "BurgerMeatID INTEGER NOT NULL, "
			+ "BurgerSizeID INTEGER NOT NULL, "
			+ "OrderID INTEGER NOT NULL, "
			+ "Amount INTEGER, "
			+ "PRIMARY KEY (OrderLineBurgerID), "
			+ "FOREIGN KEY(OrderID) REFERENCES Orders(OrderID), "
			+ "FOREIGN KEY(BurgerTypeID) REFERENCES BurgerType(BurgerTypeID), "
			+ "FOREIGN KEY(BurgerMeatID) REFERENCES BurgerMeat(BurgerMeatID), "
			+ "FOREIGN KEY(BurgerSizeID) REFERENCES BurgerSize(BurgerSizeID))";
	String sqlUpdate = "DROP TABLE IF EXISTS OrderLineBurger";

	public OrderLineBurgerTable() {
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
	
	public void insert(Burger burger, Activity activity) {
		ContentValues nuevoRegistro = burger.getContentValue(activity);
		SQLiteDatabase db = DDBBSQLite.getDDBB(Options.getDDBBName(), activity);
		db.insert("OrderLineBurger", null, nuevoRegistro);
	}
	
	public static ArrayList<Burger> getOrderBurgers(Activity activity, int orderID) {
		ArrayList<Burger> arrBurgers = new ArrayList<Burger>();
		String query = "SELECT * FROM OrderLineBurger WHERE OrderID="+orderID;
		SQLiteDatabase db = DDBBSQLite.getDDBB(Options.getDDBBName(), activity);
		Cursor c = db.rawQuery(query, null);
		if (c.moveToFirst()) {
			do {
				int id = c.getInt(0);
				int typeID = c.getInt(1);
				int meatID = c.getInt(2);
				int sizeID = c.getInt(3);
				int amount = c.getInt(5);
				Burger b = new Burger(BurgerSizeTable.getBurgerSize(activity, sizeID), BurgerMeatTable.getBurgerMeat(activity, meatID), BurgerTypeTable.getBurgerType(activity, typeID), amount);
				b.setBurgerLineID(id);
				b.setOrderID(orderID);
				arrBurgers.add(b);
			} while (c.moveToNext());
		}
		return arrBurgers;
	}

	@Override
	public void initData(Activity activity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Activity activity, int id) {
		SQLiteDatabase db = DDBBSQLite.getDDBB(Options.getDDBBName(), activity);
		db.delete("OrderLineBurger", "OrderLineBurgerID="+id, null);			
	}

	public void edit(Burger burger, Activity activity) {
		// TODO Auto-generated method stub
		
	}

}
