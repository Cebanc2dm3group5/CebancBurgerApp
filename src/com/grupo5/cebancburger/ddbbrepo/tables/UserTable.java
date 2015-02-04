package com.grupo5.cebancburger.ddbbrepo.tables;

public class UserTable {
	String sqlCreate = "CREATE TABLE User (UserID INTEGER NOT NULL, "
			+ "UserName VARCHAR(20), " + "Password VARCHAR(20), "
			+ "Admin BOOLEAN)";
	String sqlUpdate = "DROP TABLE IF EXISTS User";

	public UserTable() {}

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
