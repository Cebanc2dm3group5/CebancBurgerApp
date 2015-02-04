package com.grupo5.cebancburger.ddbbrepo.tables;

public class UserTable {
	String sqlCreate = "CREATE TABLE BurgerType (BurgerTypedID INTEGER NOT NULL, "
			+ "Description VARCHAR(20), " + "Price DECIMAL(4,2)";
	String sqlUpdate = "DROP TABLE IF EXISTS BurgerType";
	
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