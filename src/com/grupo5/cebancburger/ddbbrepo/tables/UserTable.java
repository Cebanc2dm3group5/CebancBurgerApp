package com.grupo5.cebancburger.ddbbrepo.tables;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.grupo5.cebancburger.ddbbrepo.DDBBSQLite;
import com.grupo5.cebancburger.model.User;

public class UserTable {
	
	String sqlCreate = "CREATE TABLE User (UserID INTEGER NOT NULL PRIMARY KEY, "
			+ "UserName VARCHAR(20), " + "Password VARCHAR(20), "
			+ "Admin BOOLEAN)";
	String sqlUpdate = "DROP TABLE IF EXISTS User";
	
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
	
	
	public void insert(User user, Activity activity){
		ContentValues nuevoRegistro = user.getContentValue(activity);
		SQLiteDatabase db = DDBBSQLite.getDDBB("DDBBBurgerApp", activity);
		db.insert("User", null, nuevoRegistro);
	}
	

}
