package com.grupo5.cebancburger.ddbbrepo.tables;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.grupo5.cebancburger.config.Options;
import com.grupo5.cebancburger.ddbbrepo.DDBBSQLite;
import com.grupo5.cebancburger.interfaces.DDBBObjectTable;
import com.grupo5.cebancburger.model.Order;

public class OrderTable implements DDBBObjectTable {

	String sqlCreate = "CREATE TABLE Orders (OrderID INTEGER NOT NULL, "
			+ "UserID INTEGER NOT NULL, " + "CustomerID INTEGER NOT NULL, "
			+ "Date DATE, " + "Time TIME, " + "Price DECIMAL(4,2), "
			+ "PRIMARY KEY (OrderID), "
			+ "FOREIGN KEY(UserID) REFERENCES User(UserID), "
			+ "FOREIGN KEY(CustomerID) REFERENCES Customer(CustomerID))";
	String sqlUpdate = "DROP TABLE IF EXISTS Orders";

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

	@Override
	public void initData(Activity activity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Activity activity, int id) {
		SQLiteDatabase db = DDBBSQLite.getDDBB(Options.getDDBBName(), activity);
		db.delete("Orders", "OrderID="+id, null);		
	}

	public void insert(Order order, Activity activity) {
		ContentValues nuevoRegistro = order.getContentValue(activity);
		SQLiteDatabase db = DDBBSQLite.getDDBB(Options.getDDBBName(), activity);
		db.insert("Orders", null, nuevoRegistro);		
	}

	public void edit(Order order, Activity activity) {
		ContentValues reg = order.getContentValue(activity);
		SQLiteDatabase db = DDBBSQLite.getDDBB(Options.getDDBBName(), activity);
		db.update("Orders", reg, "OrderID=" + order.getOrderID(), null);
		
	}
	
	
//	public static ArrayList<User> getAllOrders(Activity activity) {
//		ArrayList<User> arrUsers = new ArrayList<User>();
//		String query = "SELECT * FROM Orders";
//		SQLiteDatabase db = DDBBSQLite.getDDBB(Options.getDDBBName(), activity);
//		Cursor c = db.rawQuery(query, null);
//		if (c.moveToFirst()) {
//			do {
//				int userId = c.getInt(0);
//				String username = c.getString(1);
//				String password = c.getString(2);
//				int isAdminInt = c.getInt(3);
//				boolean isAdmin = false;
//				if (isAdminInt == 1) {
//					isAdmin = true;
//				}
//				User u = new User(username, password, isAdmin);
//				u.setUserID(userId);
//				arrUsers.add(u);
//			} while (c.moveToNext());
//		}
//		return arrUsers;
//	}
//	
//	public static ArrayList<User> getOrders(Activity activity, String condition) {
//		ArrayList<User> arrUsers = new ArrayList<User>();
//		String query = "SELECT * FROM Orders WHERE "+condition;
//		SQLiteDatabase db = DDBBSQLite.getDDBB(Options.getDDBBName(), activity);
//		Cursor c = db.rawQuery(query, null);
//		if (c.moveToFirst()) {
//			do {
//				int userId = c.getInt(0);
//				String username = c.getString(1);
//				String password = c.getString(2);
//				int isAdminInt = c.getInt(3);
//				boolean isAdmin = false;
//				if (isAdminInt == 1) {
//					isAdmin = true;
//				}
//				User u = new User(username, password, isAdmin);
//				u.setUserID(userId);
//				arrUsers.add(u);
//			} while (c.moveToNext());
//		}
//		return arrUsers;
//	}
//
//	public static User getOrder(Activity activity, int id) {
//		User user = null;
//		String query = "SELECT * FROM Orders WHERE UserID=" + id;
//		SQLiteDatabase db = DDBBSQLite.getDDBB(Options.getDDBBName(), activity);
//		Cursor c = db.rawQuery(query, null);
//		if (c.moveToFirst()) {
//
//			int userId = c.getInt(0);
//			String username = c.getString(1);
//			String password = c.getString(2);
//			int isAdminInt = c.getInt(3);
//			boolean isAdmin = false;
//			if (isAdminInt == 1) {
//				isAdmin = true;
//			}
//			user = new User(username, password, isAdmin);
//			user.setUserID(userId);
//		}
//		return user;
//	}

}
