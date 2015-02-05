package com.grupo5.cebancburger.ddbbrepo.tables;

import android.app.Activity;

import com.grupo5.cebancburger.interfaces.DDBBObjectTable;

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

	@Override
	public void initData(Activity activity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Activity activity, int id) {
		// TODO Auto-generated method stub
		
	}

}
