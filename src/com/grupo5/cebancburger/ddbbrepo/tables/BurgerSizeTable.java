package com.grupo5.cebancburger.ddbbrepo.tables;

import android.app.Activity;

import com.grupo5.cebancburger.interfaces.DDBBObjectTable;

public class BurgerSizeTable implements DDBBObjectTable {
	String sqlCreate = "CREATE TABLE BurgerSize (BurgerSizeID INTEGER NOT NULL, "
			+ "Description VARCHAR(20), " + "Price DECIMAL(4,2))";
	String sqlUpdate = "DROP TABLE IF EXISTS BurgerSize";

	public BurgerSizeTable() {}

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
