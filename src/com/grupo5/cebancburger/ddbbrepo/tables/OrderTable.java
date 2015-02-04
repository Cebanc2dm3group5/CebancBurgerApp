package com.grupo5.cebancburger.ddbbrepo.tables;

public class OrderTable {

	String sqlCreate = "CREATE TABLE Order (OrderID INTEGER NOT NULL, "
			+ "UserID INTEGER NOT NULL, " + "CustomerID INTEGER NOT NULL, "
			+ "Date DATE, " + "Time TIME, " + "Price DECIMAL(4,2), "
			+ "PRIMARY KEY (CustomerID), "
			+ "FOREIGN KEY(UserID) REFERENCES User(UserID), "
			+ "FOREIGN KEY(CustomerID) REFERENCES Customer(CustomerID))";
	String sqlUpdate = "DROP TABLE IF EXISTS Order";

	public OrderTable() {
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
