package com.grupo5.cebancburger.ddbbrepo.tables;

public class OrderLineDrinkTable {

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

}
