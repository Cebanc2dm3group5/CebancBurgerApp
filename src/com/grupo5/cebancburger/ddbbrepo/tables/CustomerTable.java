package com.grupo5.cebancburger.ddbbrepo.tables;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.grupo5.cebancburger.config.Options;
import com.grupo5.cebancburger.ddbbrepo.DDBBSQLite;
import com.grupo5.cebancburger.interfaces.DDBBObjectTable;
import com.grupo5.cebancburger.model.Customer;
import com.grupo5.cebancburger.model.User;

public class CustomerTable implements DDBBObjectTable {
	String sqlCreate = "CREATE TABLE Customer (CustomerID INTEGER NOT NULL PRIMARY KEY, "
			+ "Name VARCHAR(20), " + "Address VARCHAR(20), "
			+ "IDChar VARCHAR(1), " + "Phone VARCHAR(15))";
	String sqlUpdate = "DROP TABLE IF EXISTS Customer";
	Customer customerInit = new Customer("prueba","prueba","54654665",'A');
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
		this.insert(this.customerInit, activity);
	}

	@Override
	public void delete(Activity activity, int id) {
		SQLiteDatabase db = DDBBSQLite.getDDBB(Options.getDDBBName(), activity);
		db.delete("Customer", "CustomerID="+id, null);		
	}
	
	
	public static Customer getCustomer(Activity activity, int id) {
		Customer cust = null;
		String query = "SELECT * FROM Customer WHERE CustomerID=" + id;
		SQLiteDatabase db = DDBBSQLite.getDDBB(Options.getDDBBName(), activity);
		Cursor c = db.rawQuery(query, null);
		if (c.moveToFirst()) {

			int customerID = c.getInt(0);
			String name = c.getString(1);
			String address = c.getString(2);
			char idchar = c.getString(3).charAt(0);
			String phone = c.getString(4);
			cust = new Customer(name, address, phone, customerID, idchar);

		}
		return cust;
		
	}
	
	public static ArrayList<Customer> getAllCustomers(Activity activity) {
		ArrayList<Customer> arrCustomers = new ArrayList<Customer>();
		String query = "SELECT * FROM Customer";
		SQLiteDatabase db = DDBBSQLite.getDDBB(Options.getDDBBName(), activity);
		Cursor c = db.rawQuery(query, null);
		if (c.moveToFirst()) {
			do {
				int customerID = c.getInt(0);
				String name = c.getString(1);
				String address = c.getString(2);
				char idchar = c.getString(3).charAt(0);
				String phone = c.getString(4);
				Customer cust = new Customer(name, address, phone, customerID, idchar);
				arrCustomers.add(cust);
			} while (c.moveToNext());
		}
		return arrCustomers;
	}
	
	
	public static ArrayList<Customer> getCustomers(Activity activity, String condition) {
		ArrayList<Customer> arrCustomers = new ArrayList<Customer>();
		String query = "SELECT * FROM Customer WHERE " + condition;
		SQLiteDatabase db = DDBBSQLite.getDDBB(Options.getDDBBName(), activity);
		Cursor c = db.rawQuery(query, null);
		if (c.moveToFirst()) {
			do {
				int customerID = c.getInt(0);
				String name = c.getString(1);
				String address = c.getString(2);
				char idchar = c.getString(3).charAt(0);
				String phone = c.getString(4);
				Customer cust = new Customer(name, address, phone, customerID, idchar);
				arrCustomers.add(cust);
			} while (c.moveToNext());
		}
		return arrCustomers;
	}
	
	public void insert(Customer customer, Activity activity) {
		ContentValues nuevoRegistro = customer.getContentValue(activity);
		SQLiteDatabase db = DDBBSQLite.getDDBB(Options.getDDBBName(), activity);
		db.insert("Customer", null, nuevoRegistro);
	}
	
	public void edit(Customer customer, Activity activity) {
		ContentValues reg = customer.getContentValueForEdit(activity,customer.getId());
		SQLiteDatabase db = DDBBSQLite.getDDBB(Options.getDDBBName(), activity);
		db.update("Customer", reg, "CustomerID=" + customer.getId(), null);

	}


}
