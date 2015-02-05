package com.grupo5.cebancburger.ddbbrepo.tables;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.grupo5.cebancburger.ddbbrepo.DDBBSQLite;
import com.grupo5.cebancburger.interfaces.DDBBObjectTable;
import com.grupo5.cebancburger.model.Options;
import com.grupo5.cebancburger.model.User;

public class UserTable implements DDBBObjectTable {

	String sqlCreate = "CREATE TABLE User (UserID INTEGER NOT NULL PRIMARY KEY, "
			+ "UserName VARCHAR(20), "
			+ "Password VARCHAR(20), "
			+ "Admin BOOLEAN)";
	String sqlUpdate = "DROP TABLE IF EXISTS User";
	User userInit = new User("admin", "admin", true);

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

	public void insert(User user, Activity activity) {
		ContentValues nuevoRegistro = user.getContentValue(activity);
		SQLiteDatabase db = DDBBSQLite.getDDBB(Options.getDDBBName(), activity);
		db.insert("User", null, nuevoRegistro);
	}

	public void edit(User user, Activity activity) {
		ContentValues reg = user.getContentValue(activity);
		SQLiteDatabase db = DDBBSQLite.getDDBB(Options.getDDBBName(), activity);
		db.update("User", reg, "UserID='" + user.getUserID() + "'", null);

	}

	public static ArrayList<User> getAllUsers(Activity activity) {
		ArrayList<User> arrUsers = new ArrayList<User>();
		String query = "SELECT * FROM User";
		SQLiteDatabase db = DDBBSQLite.getDDBB(Options.getDDBBName(), activity);
		Cursor c = db.rawQuery(query, null);
		if (c.moveToFirst()) {
			do {
				int userId = c.getInt(0);
				String username = c.getString(1);
				String password = c.getString(2);
				int isAdminInt = c.getInt(3);
				boolean isAdmin = false;
				if (isAdminInt == 1) {
					isAdmin = true;
				}
				User u = new User(username, password, isAdmin);
				u.setUserID(userId);
				arrUsers.add(u);
			} while (c.moveToNext());
		}
		return arrUsers;
	}

	public static User getUser(Activity activity, int id) {
		User user = null;
		String query = "SELECT * FROM User WHERE UserID=" + id;
		SQLiteDatabase db = DDBBSQLite.getDDBB(Options.getDDBBName(), activity);
		Cursor c = db.rawQuery(query, null);
		if (c.moveToFirst()) {

			int userId = c.getInt(0);
			String username = c.getString(1);
			String password = c.getString(2);
			int isAdminInt = c.getInt(3);
			boolean isAdmin = false;
			if (isAdminInt == 1) {
				isAdmin = true;
			}
			user = new User(username, password, isAdmin);
			user.setUserID(userId);
		}
		return user;
	}

	public void initData(Activity activity) {
		this.insert(this.userInit, activity);
	}

	public void delete(Activity activity, int id) {
		SQLiteDatabase db = DDBBSQLite.getDDBB(Options.getDDBBName(), activity);
		db.delete("User", "UserID="+id, null);
	}

}
