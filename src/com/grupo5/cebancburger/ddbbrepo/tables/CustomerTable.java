package com.grupo5.cebancburger.ddbbrepo.tables;

public class CustomerTable {
	String sqlCreate = "CREATE TABLE Customer (CustomerID INTEGER NOT NULL, "
			+ "Name VARCHAR(20), " + "Address VARCHAR(20), "
			+ "IDChar VARCHAR(1))" + "Phone VARCHAR(15))";
	String sqlUpdate = "DROP TABLE IF EXISTS Customer";

	public CustomerTable() {
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
