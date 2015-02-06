package com.grupo5.cebancburger.model;

import java.io.Serializable;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.grupo5.cebancburger.config.Options;
import com.grupo5.cebancburger.ddbbrepo.DDBBSQLite;
import com.grupo5.cebancburger.ddbbrepo.tables.UserTable;
import com.grupo5.cebancburger.interfaces.DDBBObject;

@SuppressWarnings("serial")
public class User implements Serializable, DDBBObject{
	private int userID = -1;
	private String username, password;
	private boolean admin;


	public User(String username, String password, boolean admin) {
		this.setUsername(username);
		this.setPassword(password);
		this.setAdmin(admin);
	}
	
	public User(int id, Activity activity){
		User user = UserTable.getUser(activity, id);
		this.setUserID(user.getUserID());
		this.setUsername(user.getUsername());
		this.setPassword(user.getPassword());
		this.setAdmin(user.isAdmin());
	}

	public int getUserID() {
		return this.userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAdmin() {
		return this.admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public void save(Activity activity) {
		UserTable ut = new UserTable();
		if (this.userID != -1) {
			ut.insert(this, activity);
		} else {
			ut.edit(this, activity);
		}
	}
	
	public void delete(Activity activity){
		UserTable ut = new UserTable();
		ut.delete(activity, this.getUserID());
	}
	
	

	public ContentValues getContentValue(Activity activity) {
		ContentValues nr = new ContentValues();
		nr.put("UserID", getNextID(activity));
		nr.put("UserName", this.getUsername());
		nr.put("Password", this.getPassword());
		nr.put("Admin", this.isAdmin());
		return nr;
	}

	private static int getNextID(Activity activity) {
		String query = "SELECT UserID FROM User ORDER BY UserID DESC LIMIT 1";
		int lastID = 0;
		SQLiteDatabase db = DDBBSQLite.getDDBB(Options.getDDBBName(), activity);
		Cursor c = db.rawQuery(query, null);
		if (c.moveToFirst()) {
			lastID = c.getInt(0);
		}
		return lastID + 1;
	}

	

}
