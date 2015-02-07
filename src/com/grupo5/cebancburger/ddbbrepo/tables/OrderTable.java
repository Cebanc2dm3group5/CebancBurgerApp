package com.grupo5.cebancburger.ddbbrepo.tables;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.grupo5.cebancburger.config.Options;
import com.grupo5.cebancburger.ddbbrepo.DDBBSQLite;
import com.grupo5.cebancburger.interfaces.DDBBObjectTable;
import com.grupo5.cebancburger.model.Burger;
import com.grupo5.cebancburger.model.Customer;
import com.grupo5.cebancburger.model.Drink;
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
		
		for (int i=0; i<order.getBurger().size(); i++){
			order.getBurger().get(i).save(activity);
		}
		for (int i=0; i<order.getBebida().size(); i++){
			order.getBebida().get(i).save(activity);
		}
	}

	public void edit(Order order, Activity activity) {
		ContentValues reg = order.getContentValue(activity);
		SQLiteDatabase db = DDBBSQLite.getDDBB(Options.getDDBBName(), activity);
		db.update("Orders", reg, "OrderID=" + order.getOrderID(), null);
		
	}
	
	
	public static ArrayList<Order> getAllOrders(Activity activity) {
		ArrayList<Order> arrOrders = new ArrayList<Order>();
		String query = "SELECT * FROM Orders";
		SQLiteDatabase db = DDBBSQLite.getDDBB(Options.getDDBBName(), activity);
		Cursor c = db.rawQuery(query, null);
		if (c.moveToFirst()) {
			do {
				int orderId = c.getInt(0);
				int userId = c.getInt(1);
				int customerId = c.getInt(2);
				Date date = new Date(c.getInt(3));
				Time time = new Time(c.getInt(4));
				
				Customer cust = CustomerTable.getCustomer(activity, customerId);
				ArrayList<Burger> alBurger = OrderLineBurgerTable.getOrderBurgers(activity, orderId);
				ArrayList<Drink> alBebida = OrderLineDrinkTable.getOrderDrinks(activity, orderId);
				Order order = new Order(cust, alBurger, alBebida, orderId, userId, date, time);
				order.setUserID(userId);
				arrOrders.add(order);
			} while (c.moveToNext());
		}
		return arrOrders;
	}
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
	public static Order getOrder(Activity activity, int id) {
		Order order = null;
		String query = "SELECT * FROM Orders WHERE OrderID=" + id;
		SQLiteDatabase db = DDBBSQLite.getDDBB(Options.getDDBBName(), activity);
		Cursor c = db.rawQuery(query, null);
		if (c.moveToFirst()) {

			int orderId = c.getInt(0);
			int userId = c.getInt(1);
			int customerId = c.getInt(2);
			Date date = new Date(c.getInt(3));
			Time time = new Time(c.getInt(4));
			
			Customer cust = CustomerTable.getCustomer(activity, customerId);
			ArrayList<Burger> alBurger = OrderLineBurgerTable.getOrderBurgers(activity, orderId);
			ArrayList<Drink> alBebida = OrderLineDrinkTable.getOrderDrinks(activity, orderId);
			order = new Order(cust, alBurger, alBebida, orderId, userId, date, time);
			order.setUserID(userId);
		}
		return order;
	}

}
