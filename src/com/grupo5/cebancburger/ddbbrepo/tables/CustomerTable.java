package com.grupo5.cebancburger.ddbbrepo.tables;

import android.app.Activity;

import com.grupo5.cebancburger.interfaces.DDBBObjectTable;

public class CustomerTable implements DDBBObjectTable {
	String sqlCreate = "CREATE TABLE Customer (CustomerID INTEGER NOT NULL PRIMARY KEY, "
			+ "Name VARCHAR(20), " + "Address VARCHAR(20), "
			+ "IDChar VARCHAR(1), " + "Phone VARCHAR(15))";
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

	@Override
	public void initData(Activity activity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Activity activity, int id) {
		// TODO Auto-generated method stub
		
	}

}
